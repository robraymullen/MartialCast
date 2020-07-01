package com.rob.video.server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rob.video.server.model.serializer.PropertySerializer;
import com.rob.video.server.model.serializer.VideoCollectionSerializer;

@Entity
@Table(name="forms")
public class Form implements IVideoProperty{
	
	@Id
    @GeneratedValue(generator = "form_generator")
    @SequenceGenerator(
            name = "form_generator",
            sequenceName = "form_sequence",
            initialValue = 1
    )
	private Long id;
	
	@NotBlank
	@Size(max=1000)
	private String name;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "form_styles",
            joinColumns = { @JoinColumn(name = "form_id") },
            inverseJoinColumns = { @JoinColumn(name = "style_id") })
    private Set<Style> styles = new HashSet<>();
	
	@JsonSerialize(using = VideoCollectionSerializer.class)
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "forms")
    private Set<Video> videos = new HashSet<>();
	
	@Override
	@JsonSerialize(using = VideoCollectionSerializer.class)
	public Set<Video> getVideos() {
		return videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}

	public Form() {
		
	}
	
	public Form(String name){
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

	public Set<Style> getStyles() {
		return styles;
	}

	public void setStyles(Set<Style> styles) {
		this.styles = styles;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Form other = (Form) obj;
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
		if (styles == null) {
			if (other.styles != null)
				return false;
		} else if (!styles.equals(other.styles))
			return false;
		return true;
	}
}
