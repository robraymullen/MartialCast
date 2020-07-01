package com.rob.video.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rob.video.server.model.Instructor;
import com.rob.video.server.repository.InstructorRepository;

@RestController
public class InstructorController {

	@Autowired
	private InstructorRepository instructorRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/instructors")
	public List<Instructor> getInstructors() {
		return instructorRepository.findAllByOrderByNameDesc();
	}
}
