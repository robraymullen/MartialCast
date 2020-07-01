package com.rob.video.server.model.serializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.rob.video.server.model.Comment;
import com.rob.video.server.model.Form;
import com.rob.video.server.model.Style;
import com.rob.video.server.model.Tag;
import com.rob.video.server.model.Technique;
import com.rob.video.server.model.Video;

public class VideoCollectionSerializer extends JsonSerializer<Set<Video>> {
	public VideoCollectionSerializer() {
        this(null);
    }

    public VideoCollectionSerializer(Set<Video> t) {
        super();
    }

	@Override
	public void serialize(Set<Video> videos, JsonGenerator gen, SerializerProvider serializers) throws IOException {	
        gen.writeObject(videos);
	}

}