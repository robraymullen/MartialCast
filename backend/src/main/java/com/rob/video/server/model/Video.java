package com.rob.video.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TokenizerDef;
import org.springframework.beans.factory.annotation.Value;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.Parameter;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rob.video.server.model.serializer.PropertySerializer;
import com.rob.video.server.model.serializer.VideoSerializer;
import com.rob.video.server.model.serializer.CommentSerializer;
import com.rob.video.server.model.serializer.CollectionSerializer;
import com.rob.video.server.constants.VideoConstants;

@Entity
@Indexed
@Table(name = "videos")
@AnalyzerDef(
		name = "textanalyzer",
		tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
		filters = {
			@TokenFilterDef(factory = LowerCaseFilterFactory.class),
			@TokenFilterDef(factory = SnowballPorterFilterFactory.class,
				params = { @Parameter(name = "language", value = "English") })
		}
	)
public class Video implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "video_generator")
	@SequenceGenerator(name = "video_generator", sequenceName = "video_sequence", initialValue = 1)
	private Long id;

	@NotBlank
	@Field(analyzer = @Analyzer(definition = "textanalyzer"))
	@Size(max = 4000)
	private String name;

	@NotBlank
	@Size(max = 4000)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  
	private String fileName;

	@JsonIgnore
	@Transient
	private String fileLocation;
	
	/*
	 * Path to the file from the root store: video.file.store
	 */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  
	@Size(max = 10000)
	private String filePath;
	
	@Transient
	private String previewFile;

	@Size(max = 10000)
	@Field(analyzer = @Analyzer(definition = "textanalyzer"))
	private String description;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "posted_at")
	private Date postedAt = new Date();

	@JsonSerialize(using = PropertySerializer.class)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "video_instructors", joinColumns = { @JoinColumn(name = "video_id") }, inverseJoinColumns = {
			@JoinColumn(name = "instructor_id") })
	private Set<Instructor> instructors = new HashSet<>();

	@JsonSerialize(using = PropertySerializer.class)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "video_techniques", joinColumns = { @JoinColumn(name = "video_id") }, inverseJoinColumns = {
			@JoinColumn(name = "technique_id") })
	private Set<Technique> techniques = new HashSet<>();

	@JsonSerialize(using = PropertySerializer.class)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "video_styles", joinColumns = { @JoinColumn(name = "video_id") }, inverseJoinColumns = {
			@JoinColumn(name = "style_id") })
	private Set<Style> styles = new HashSet<>();

	@JsonSerialize(using = PropertySerializer.class)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "video_tags", joinColumns = { @JoinColumn(name = "video_id") }, inverseJoinColumns = {
			@JoinColumn(name = "tag_id") })
	private Set<Tag> tags = new HashSet<>();

	@JsonSerialize(using = PropertySerializer.class)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "video_forms", joinColumns = { @JoinColumn(name = "video_id") }, inverseJoinColumns = {
			@JoinColumn(name = "form_id") })
	private Set<Form> forms = new HashSet<>();

	@JsonManagedReference
	//@JsonSerialize(using = CommentSerializer.class)
	@OneToMany(mappedBy = "video")
	private List<Comment> comments = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                })
    @JoinColumn(name="collection_id")
	@JsonSerialize(using = CollectionSerializer.class)
	private VideoCollection collection;
	
	public Video() {

	}

	public Video(Video video) {
		this.id = video.getId();
		this.name = video.getName();
		this.fileName = video.getFileName();
		this.fileLocation = video.getFileLocation();
		this.postedAt = video.getPostedAt();
		this.description = video.getDescription();
		this.forms = video.getForms();
		this.comments = video.getComments();
		this.instructors = video.getInstructors();
		this.styles = video.getStyles();
		this.tags = video.getTags();
		this.techniques = video.getTechniques();
		this.collection = video.getCollection();

	}

	public Video(String name, String fileName) {
		this.name = name;
		this.fileName = fileName;
	}

	public Video(String name, String fileName, String fileLocation, String description) {
		this.name = name;
		this.fileName = fileName;
		this.fileLocation = fileLocation;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(Date postedAt) {
		this.postedAt = postedAt;
	}

	@JsonSerialize(using = PropertySerializer.class)
	public Set<Instructor> getInstructors() {
		return instructors;
	}

	public void setInstructors(Set<Instructor> instructors) {
		this.instructors = instructors;
	}

	@JsonSerialize(using = PropertySerializer.class)
	public Set<Technique> getTechniques() {
		return techniques;
	}

	public void setTechniques(Set<Technique> techniques) {
		this.techniques = techniques;
	}

	@JsonSerialize(using = PropertySerializer.class)
	public Set<Style> getStyles() {
		return styles;
	}

	public void setStyles(Set<Style> styles) {
		this.styles = styles;
	}

	@JsonSerialize(using = PropertySerializer.class)
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@JsonSerialize(using = PropertySerializer.class)
	public Set<Form> getForms() {
		return forms;
	}

	public void setForms(Set<Form> forms) {
		this.forms = forms;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@JsonSerialize(using = CommentSerializer.class)
	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@JsonIgnore
	public String getFileLocation() {
		return this.fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	@JsonIgnore
	public String getPreviewFile() {
		return this.previewFile;
	}

	public void setPreviewFile(String previewFile) {
		this.previewFile = previewFile;
	}
	
	@PostLoad
    public void setFilePaths(){
		VideoConstants constants = new VideoConstants();
		//For now preview files are always .mp4
		String fileName = this.getFileName();
		if (fileName != null && !fileName.isEmpty()) {
			String[] splitFile = fileName.split("\\.");
			splitFile[splitFile.length-1] = "mp4";
			fileName = String.join(".", splitFile);
	        this.setPreviewFile(constants.getVideoPreviewPath() + fileName);
	        this.setFileLocation(constants.getVideoPath()+this.getFileName());
		}
    }
	
	@JsonSerialize(using = CollectionSerializer.class)
	public VideoCollection getCollection() {
		return collection;
	}

	public void setCollection(VideoCollection collection) {
		this.collection = collection;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", name=" + name + ", fileName=" + fileName + ", fileLocation=" + fileLocation
				+ ", filePath=" + filePath + ", previewFile=" + previewFile + ", description=" + description
				+ ", postedAt=" + postedAt + ", instructors=" + instructors + ", techniques=" + techniques + ", styles="
				+ styles + ", tags=" + tags + ", forms=" + forms + ", comments=" + comments + ", collection="
				+ collection + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collection == null) ? 0 : collection.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fileLocation == null) ? 0 : fileLocation.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((forms == null) ? 0 : forms.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instructors == null) ? 0 : instructors.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((postedAt == null) ? 0 : postedAt.hashCode());
		result = prime * result + ((previewFile == null) ? 0 : previewFile.hashCode());
		result = prime * result + ((styles == null) ? 0 : styles.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		result = prime * result + ((techniques == null) ? 0 : techniques.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (collection == null) {
			if (other.collection != null)
				return false;
		} else if (!collection.equals(other.collection))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fileLocation == null) {
			if (other.fileLocation != null)
				return false;
		} else if (!fileLocation.equals(other.fileLocation))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (forms == null) {
			if (other.forms != null)
				return false;
		} else if (!forms.equals(other.forms))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instructors == null) {
			if (other.instructors != null)
				return false;
		} else if (!instructors.equals(other.instructors))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (postedAt == null) {
			if (other.postedAt != null)
				return false;
		} else if (!postedAt.equals(other.postedAt))
			return false;
		if (previewFile == null) {
			if (other.previewFile != null)
				return false;
		} else if (!previewFile.equals(other.previewFile))
			return false;
		if (styles == null) {
			if (other.styles != null)
				return false;
		} else if (!styles.equals(other.styles))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		if (techniques == null) {
			if (other.techniques != null)
				return false;
		} else if (!techniques.equals(other.techniques))
			return false;
		return true;
	}
}
