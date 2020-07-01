package com.rob.video.server.model.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.rob.video.server.model.Comment;
import com.rob.video.server.model.Form;
import com.rob.video.server.model.IVideoProperty;
import com.rob.video.server.model.Style;
import com.rob.video.server.model.Tag;
import com.rob.video.server.model.Technique;
import com.rob.video.server.model.Video;

public class VideoSerializer extends JsonSerializer<Video> {
	public VideoSerializer() {
        this(null);
    }

    public VideoSerializer(Video t) {
        super();
    }

	@Override
	public void serialize(Video video, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		List<Comment> comments = video.getComments();
		for(Comment comment : comments) {
			comment.setVideo(null);
		}
		Set<Form> forms = video.getForms();
		for(Form form : forms) {
			form.setVideos(null);
		}
		Set<Style> styles = video.getStyles();
		for(Style style : styles) {
			style.setVideos(null);
		}
		Set<Tag> tags = video.getTags();
		for(Tag tag : tags) {
			tag.setVideos(null);
		}
		Set<Technique> techniques = video.getTechniques();
		for(Technique technique : techniques) {
			technique.setVideos(null);
		}
		
        gen.writeObject(video);
	}

}
