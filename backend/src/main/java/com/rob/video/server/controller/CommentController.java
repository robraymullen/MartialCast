package com.rob.video.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rob.video.server.model.Comment;
import com.rob.video.server.model.Video;
import com.rob.video.server.repository.CommentRepository;
import com.rob.video.server.repository.VideoRepository;

@RestController
public class CommentController {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	VideoRepository videoRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/comments")
	public Page<Comment> getComments(Pageable pageable) {
		return commentRepository.findAll(pageable);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/comments/add")
	public Comment addComment(@Valid @RequestBody Comment comment) {
		Video video = videoRepository.getOne(comment.getVideo().getId());
		comment.setVideo(video);
		video.getComments().add(comment);
		videoRepository.save(video);
		return commentRepository.save(comment);
	}
	
	
}
