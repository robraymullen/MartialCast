package com.rob.video.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rob.video.server.model.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long>, IVideoPropertyRepository {
	
	Instructor findByName(String name);

	List<Instructor> findAllByOrderByNameDesc();
	
	//Instructor findByNameIgnoreCase(String name);

}
