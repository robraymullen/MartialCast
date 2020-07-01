package com.rob.video.server.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rob.video.server.model.serializer.CommentSerializer;
import com.rob.video.server.model.serializer.PropertySerializer;
import com.rob.video.server.model.serializer.VideoSerializer;

@Entity
@Table(name="comments")
public class Comment {
	
	@Id
    @GeneratedValue(generator = "form_generator")
    @SequenceGenerator(
            name = "form_generator",
            sequenceName = "form_sequence",
            initialValue = 1
    )
	private Long id;
	
	@NotBlank
	@Size(max=10000)
	private String content;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "posted_at")
    private Date postedAt = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL,
                    CascadeType.MERGE
                })
    @JoinColumn(name="video_id")
	@JsonBackReference
	//@JsonSerialize(using = VideoSerializer.class)
    private Video video;
	
	public Comment(){
		
	}
	
	public Comment(String content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(Date postedAt) {
		this.postedAt = postedAt;
	}
	
	@JsonSerialize(using = VideoSerializer.class)
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", postedAt=" + postedAt + ", video=" + video + "]";
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (postedAt == null) {
			if (other.postedAt != null)
				return false;
		} else if (!postedAt.equals(other.postedAt))
			return false;
		if (video == null) {
			if (other.video != null)
				return false;
		} else if (!video.equals(other.video))
			return false;
		return true;
	}

}
