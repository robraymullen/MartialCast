package com.rob.video.server.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rob.video.server.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
	List<Video> findByStyles_Name(String name);
	
	List<Video> findByTechniques_Name(String name);
	
	List<Video> findByInstructors_Name(String name);
	
	List<Video> findByTags_name(String name);
	
	Optional<Video> findByName(String name);
	
	List<Video> findByForms_Name(String name);

	List<Video> findByTags_Name(String name);
	
	List<Video> findByStyles_NameIn(Collection names);
	
	Optional<Video> findById(String id);
	
	Page<Video> findAllByOrderByPostedAtDesc(Pageable pageable);

	Collection<Video> findByTechniques_NameIn(Collection techniques);

	Collection<? extends Video> findByInstructors_NameIn(Collection instructors);

	Collection<? extends Video> findByCollection_Name(String collectionName);

}
