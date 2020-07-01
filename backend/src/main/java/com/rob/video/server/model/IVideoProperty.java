package com.rob.video.server.model;

import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rob.video.server.model.serializer.PropertySerializer;
import com.rob.video.server.model.serializer.VideoCollectionSerializer;

public interface IVideoProperty {

	@JsonSerialize(using = VideoCollectionSerializer.class)
	Set<Video> getVideos();
	
	void setVideos(Set<Video> videos);
	
	Long getId();
	
	String getName();
}
