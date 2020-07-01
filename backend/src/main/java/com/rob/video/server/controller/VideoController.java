package com.rob.video.server.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rob.video.server.repository.FormRepository;
import com.rob.video.server.repository.IVideoPropertyRepository;
import com.rob.video.server.repository.InstructorRepository;
import com.rob.video.server.repository.StyleRepository;
import com.rob.video.server.repository.TagRepository;
import com.rob.video.server.repository.TechniqueRepository;
import com.rob.video.server.repository.VideoCollectionRepository;
import com.rob.video.server.repository.VideoRepository;
import com.rob.video.server.repository.VideoSearchRepository;
import com.rob.video.server.exception.ResourceNotFoundException;
import com.rob.video.server.model.Form;
import com.rob.video.server.model.IVideoProperty;
import com.rob.video.server.model.Instructor;
import com.rob.video.server.model.Style;
import com.rob.video.server.model.Tag;
import com.rob.video.server.model.Technique;
import com.rob.video.server.model.Video;
import com.rob.video.server.model.VideoCollection;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private FormRepository formRepository;

	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private VideoCollectionRepository videoCollectionRepository;

	@Autowired
	private StyleRepository styleRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private TechniqueRepository techniqueRepository;

	@Autowired
	private VideoSearchRepository videoSearch;

	@Value("${video.file.store}")
	private String videoStore;
	
	@Value("${video.file.preview.store}")
	private String videoPreviewStore;

	/**
	 * 
	 * @param query
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/search")
	public List<Video> search(@RequestParam("q") String query) {
		List<Video> searchResults = null;
		try {
			searchResults = videoSearch.search(query);
		} catch (Exception ex) {

		}
		return searchResults;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("")
	public Page<Video> getVideos(Pageable pageable) {
		return videoRepository.findAllByOrderByPostedAtDesc(pageable);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{videoId}")
	public Optional<Video> getVideoById(@PathVariable Long videoId) {
		Optional<Video> video = videoRepository.findById(videoId);
		if(!video.isPresent()) {
			throw new ResourceNotFoundException("video could not be found");
		}
		return videoRepository.findById(videoId);
	}
	
	@GetMapping("/tags/popular")
	public Tag getVideosFromPopularTags(@Valid @RequestParam("q") String popularity) {
		List<Tag> tags = tagRepository.findAll();
		Tag popularTag = new Tag();
		Set<Video> videos = new HashSet<Video>();
		if (!tags.isEmpty()) {
			Collections.sort(tags, new Comparator<Tag>() {
		        @Override public int compare(Tag tag1, Tag tag2) {
		            return tag2.getVideos().size() - tag1.getVideos().size();
		        }
		    });
			//Valid values = most, second, third
			popularity = popularity.toLowerCase();
			switch (popularity) {
				case "most" : 
					popularTag = tags.get(0);
					break;
				case "second" :
					if (tags.size() > 1) {
						popularTag = tags.get(1);
					}
					break;
				case "third" :
					if (tags.size() > 2) {
						popularTag = tags.get(2);
					}
					break;
			}
		}
		return popularTag;
	}
	
	private void sortByVideoCount(List<IVideoProperty> properties) {
		Collections.sort(properties, new Comparator<IVideoProperty>() {
	        @Override public int compare(IVideoProperty prop1, IVideoProperty prop2) {
	            return prop2.getVideos().size() - prop1.getVideos().size();
	        }
	    });
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add")
	public Video createVideo(@Valid @RequestBody Video video) {
		video.setStyles((Set<Style>) linkExistingRecords(video.getStyles(), styleRepository));
		video.setForms((Set<Form>) linkExistingRecords(video.getForms(), formRepository));
		video.setInstructors((Set<Instructor>) linkExistingRecords(video.getInstructors(), instructorRepository));
		video.setTags((Set<Tag>) linkExistingRecords(video.getTags(), tagRepository));
		video.setTechniques((Set<Technique>) linkExistingRecords(video.getTechniques(), techniqueRepository));
		video.setCollection((VideoCollection) linkExistingCollection(video.getCollection()));
		String filePath = video.getFilePath().replace(videoStore, "");
		video.setFilePath(filePath);
		return videoRepository.save(video);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addAll")
	public List<Video> createVideos(@Valid @RequestBody List<Video> videos) {
		List<Video> videoResults = new ArrayList<Video>();
		for (Video video : videos) {
			video.setStyles((Set<Style>) linkExistingRecords(video.getStyles(), styleRepository));
			video.setForms((Set<Form>) linkExistingRecords(video.getForms(), formRepository));
			video.setInstructors((Set<Instructor>) linkExistingRecords(video.getInstructors(), instructorRepository));
			video.setTags((Set<Tag>) linkExistingRecords(video.getTags(), tagRepository));
			video.setTechniques((Set<Technique>) linkExistingRecords(video.getTechniques(), techniqueRepository));
			video.setCollection((VideoCollection) linkExistingCollection(video.getCollection()));
			String filePath = video.getFilePath().replace(videoStore, "");
			video.setFilePath(filePath);
			videoResults.add(videoRepository.save(video));
		}
		return videoResults;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/videosrc/{id}")
	public ResponseEntity<?> getFullVideo(@PathVariable Long id) throws MalformedURLException {
		Optional<Video> video = videoRepository.findById(id);
		if(video.isPresent()) {
			Video videoModel = video.get();
			File file = new File(videoStore + videoModel.getFilePath() + videoModel.getFileName());
			UrlResource videoFile = new UrlResource(file.toURI());
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
					.contentType(MediaTypeFactory.getMediaType(videoFile).orElse(MediaType.APPLICATION_OCTET_STREAM))
					.body(videoFile);
		} 
		return new ResponseEntity<>("Video not found", HttpStatus.NOT_FOUND);
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/videosrc/preview/{id}")
	public ResponseEntity<?> getPreviewVideo(@PathVariable Long id) throws MalformedURLException {
		Optional<Video> video = videoRepository.findById(id);
		if(video.isPresent()) {
			Video videoModel = video.get();
			File file = new File(videoStore + videoModel.getFilePath() + videoPreviewStore + videoModel.getFileName());
			UrlResource videoFile = new UrlResource(file.toURI());
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
					.contentType(MediaTypeFactory.getMediaType(videoFile).orElse(MediaType.APPLICATION_OCTET_STREAM))
					.body(videoFile);
		}
		return new ResponseEntity<>("Video not found", HttpStatus.NOT_FOUND);
	}
	
	private VideoCollection linkExistingCollection(VideoCollection collection) {
		if(collection != null) {
			IVideoProperty storedCollection = videoCollectionRepository.findByNameIgnoreCase(collection.getName());
			if(storedCollection != null) {
				return (VideoCollection) storedCollection;
			}
		}
		return collection;
	}

	@SuppressWarnings("unchecked")
	private <T> Set<T> linkExistingRecords(Set<T> properties, IVideoPropertyRepository repository) {
		if (!properties.isEmpty()) {
			Set<IVideoProperty> insertProperties = new HashSet<>();
			Set<IVideoProperty> newProperties = (Set<IVideoProperty>) properties;
			for (IVideoProperty property : newProperties) {
				IVideoProperty storedInstructor = repository.findByNameIgnoreCase(property.getName());
				if (storedInstructor == null) {
					insertProperties.add(property);
				} else {
					insertProperties.add(storedInstructor);
				}
			}
			return (Set<T>) insertProperties;
		}
		return properties;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/style")
	public Set<Video> getVideosByStyle(@Valid @RequestParam("q") String style) {
		Collection styles = Arrays.asList(style.split(","));
		return new HashSet<Video>(videoRepository.findByStyles_NameIn(styles)); 
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/technique")
	public Set<Video> getVideosByTechnique(@Valid @RequestParam("q") String technique) {
		Collection techniques = Arrays.asList(technique.split(","));
		return new HashSet<Video>(videoRepository.findByTechniques_NameIn(techniques));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/form")
	public List<Video> getVideosByForm(@Valid @RequestParam("q") String form) {
		return videoRepository.findByForms_Name(form);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/tag")
	public List<Video> getVideosByTag(@Valid @RequestParam("q") String tag) {
		return videoRepository.findByTags_Name(tag);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/instructor")
	public Set<Video> getVideosByInstructor(@Valid @RequestParam("q") String instructor) {
		Collection instructors = Arrays.asList(instructor.split(","));
		return new HashSet<Video>(videoRepository.findByInstructors_NameIn(instructors));
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/collection")
	public Set<Video> getVideosByCollection(@Valid @RequestParam("q") String collectionName) {
		return new HashSet<Video>(videoRepository.findByCollection_Name(collectionName));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{videoId}")
	public Video updateQuestion(@PathVariable Long videoId, @Valid @RequestBody Video videoRequest) {
		return videoRepository.findById(videoId).map(video -> {
			video.getComments().addAll(videoRequest.getComments());
			video.getForms().addAll(videoRequest.getForms());
			video.getInstructors().addAll(videoRequest.getInstructors());
			video.getTechniques().addAll(videoRequest.getTechniques());
			video.getStyles().addAll(videoRequest.getStyles());
			video.getTags().addAll(videoRequest.getTags());
			return videoRepository.save(video);
		}).orElseThrow(() -> new ResourceNotFoundException("Video not found with id " + videoId));
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("/{videoId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long videoId) {
		return videoRepository.findById(videoId).map(video -> {
			videoRepository.delete(video);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Video not found with id " + videoId));
	}
}
