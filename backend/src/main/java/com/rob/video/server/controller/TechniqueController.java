package com.rob.video.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rob.video.server.model.Technique;
import com.rob.video.server.repository.TechniqueRepository;

@RestController
public class TechniqueController {

	@Autowired
	private TechniqueRepository techniqueRepository;
	
	@GetMapping("/techniques")
	public List<Technique> getTechniques() {
		return techniqueRepository.findAll();
	}
}
