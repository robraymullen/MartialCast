package com.rob.video.server.dto;

import java.util.Date;
import java.util.Set;

import com.rob.video.server.model.Video;

public class VideoDTO {
	
	private Long id;
	
	private String name;
	
	private String fileName;
	
	private String fileLocation;
	
	private String description;
	
	private Date postedAt;
	
	private Set<String> styles;
	
	private Set<String> techniques;
	
	private Set<String> forms;
	
	private Set<String> tags;
	
	private Set<String> comments;
	
	public VideoDTO(Video video) {
		
	}

}
