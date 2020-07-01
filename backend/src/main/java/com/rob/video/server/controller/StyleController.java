package com.rob.video.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rob.video.server.model.Style;
import com.rob.video.server.repository.StyleRepository;

@RestController
public class StyleController {

	@Autowired
	private StyleRepository styleRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/styles")
	public List<Style> getStyles() {
		return styleRepository.findAllByOrderByNameDesc();
	}
}
