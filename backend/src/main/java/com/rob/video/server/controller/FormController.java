package com.rob.video.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rob.video.server.model.Form;
import com.rob.video.server.repository.FormRepository;

@RestController
public class FormController {

	@Autowired
	private FormRepository formRepository;
	
	@GetMapping("/forms")
	public List<Form> getForms() {
		return formRepository.findAll();
	}
}
