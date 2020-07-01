package com.rob.video.server.model.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.rob.video.server.model.Comment;
import com.rob.video.server.model.IVideoProperty;
import com.rob.video.server.model.Video;

public class CommentSerializer extends StdSerializer<List<Comment>> {

    public CommentSerializer() {
        this(null);
    }

    public CommentSerializer(Class<List<Comment>> t) {
        super(t);
    }
    
    @Override
    public void serialize(
            List<Comment> comments,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException {

    	generator.writeStartArray();
    	for (Comment comment : comments) {
    		generator.writeStartObject();
    		generator.writeFieldName("id");
    		generator.writeNumber(comment.getId());
    		generator.writeFieldName("content");
    		generator.writeString(comment.getContent());
    		generator.writeFieldName("postedAt");
    		generator.writeObject(comment.getPostedAt().toString());
    		generator.writeEndObject();
        }
    	generator.writeEndArray();
    }

}
