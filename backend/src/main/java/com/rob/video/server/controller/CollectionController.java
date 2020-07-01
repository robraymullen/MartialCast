package com.rob.video.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rob.video.server.model.VideoCollection;
import com.rob.video.server.repository.VideoCollectionRepository;

@RestController
public class CollectionController {
	
	@Autowired
	private VideoCollectionRepository collectionRepository;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/collections")
	public List<VideoCollection> getInstructors() {
		return collectionRepository.findAllByOrderByNameDesc();
	}

}
