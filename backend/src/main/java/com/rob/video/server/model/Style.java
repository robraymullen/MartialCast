package com.rob.video.server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rob.video.server.model.serializer.PropertySerializer;
import com.rob.video.server.model.serializer.VideoCollectionSerializer;

@Entity
@Table(name="styles")
public class Style implements IVideoProperty {

	@Id
    @GeneratedValue(generator = "style_generator")
    @SequenceGenerator(
            name = "style_generator",
            sequenceName = "style_sequence",
            initialValue = 1
    )
	@Column(unique = true)
	private Long id;
	
	@NotBlank
	@Size(max=1000)
	private String name;
	
	@JsonSerialize(using = VideoCollectionSerializer.class)
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "styles")
    private Set<Video> videos = new HashSet<>();
	
	public Style() {
		
	}
	
	public Style(String name){
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	@JsonSerialize(using = VideoCollectionSerializer.class)
	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Style other = (Style) obj;
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
}
