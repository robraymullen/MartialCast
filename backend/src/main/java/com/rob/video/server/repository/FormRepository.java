package com.rob.video.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rob.video.server.model.Form;
import com.rob.video.server.model.Video;

@Repository
public interface FormRepository extends JpaRepository<Form, Long>, IVideoPropertyRepository {
	
	Form findByName(String name);
	
	//Form findByNameIgnoreCase(String name);

}
