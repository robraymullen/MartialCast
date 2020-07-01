package com.rob.video.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rob.video.server.model.Style;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long>, IVideoPropertyRepository {
	
	Style findByName(String name);

	List<Style> findAllByOrderByNameDesc();
	
	//Style findByNameIgnoreCase(String name);

}
