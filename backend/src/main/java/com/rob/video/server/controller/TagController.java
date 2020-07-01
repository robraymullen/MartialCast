package com.rob.video.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rob.video.server.model.Tag;
import com.rob.video.server.repository.TagRepository;

@RestController
public class TagController {
	
	@Autowired
	private TagRepository tagRepository;
	
	@GetMapping("/tags")
    public List<Tag> getVideos() {
        return tagRepository.findAll();
    }

}
