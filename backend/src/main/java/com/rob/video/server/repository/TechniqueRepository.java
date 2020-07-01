package com.rob.video.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rob.video.server.model.Technique;

@Repository
public interface TechniqueRepository extends JpaRepository<Technique, Long>, IVideoPropertyRepository {
	
	Technique findByName(String name);
	
	//Technique findByNameIgnoreCase(String name);

}
