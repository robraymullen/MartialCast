package com.rob.video.server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rob.video.server.model.serializer.CommentSerializer;
import com.rob.video.server.model.serializer.VideoCollectionSerializer;

@Entity
@Table(name="collections")
public class VideoCollection implements IVideoProperty {
	
	@Id
	@GeneratedValue(generator = "collection_generator")
	@SequenceGenerator(name = "collection_generator", sequenceName = "collection_sequence", initialValue = 1)
	private Long id;
	
	@NotBlank
	@Size(max = 4000)
	private String name;
	
	@JsonSerialize(using = VideoCollectionSerializer.class)
	@OneToMany(mappedBy = "collection")
	private Set<Video> videos = new HashSet<>();
	
	public VideoCollection() {
		
	}
	
	public VideoCollection(String name) {
		this.name = name;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoCollection other = (VideoCollection) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (videos == null) {
			if (other.videos != null)
				return false;
		} else if (!videos.equals(other.videos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Collection [id=" + id + ", name=" + name + ", videos=" + videos + "]";
	}

	@Override
	public Set<Video> getVideos() {
		return this.videos;
	}

	@Override
	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
