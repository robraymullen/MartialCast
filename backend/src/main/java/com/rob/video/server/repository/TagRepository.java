package com.rob.video.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rob.video.server.model.Tag;
import com.rob.video.server.model.Video;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>, IVideoPropertyRepository {
	
	Tag findByName(String name);

}
