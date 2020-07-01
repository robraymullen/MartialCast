package com.rob.video.server.model.serializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.rob.video.server.model.Comment;
import com.rob.video.server.model.Form;
import com.rob.video.server.model.IVideoProperty;
import com.rob.video.server.model.Instructor;
import com.rob.video.server.model.Style;
import com.rob.video.server.model.Tag;
import com.rob.video.server.model.Video;

public class PropertySerializer extends StdSerializer<Set<IVideoProperty>> {

    public PropertySerializer() {
        this(null);
    }

    public PropertySerializer(Class<Set<IVideoProperty>> t) {
        super(t);
    }
    
    @Override
    public void serialize(
    		Set<IVideoProperty> properties,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException {
    	
    	generator.writeStartArray();
    	for (IVideoProperty property : properties) {
    		generator.writeStartObject();
    		generator.writeFieldName("id");
    		generator.writeNumber(property.getId());
    		generator.writeFieldName("name");
    		generator.writeString(property.getName());
    		generator.writeEndObject();
        }
    	generator.writeEndArray();
    }

}
