package com.rob.video.server.model.serializer;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.rob.video.server.model.VideoCollection;
import com.rob.video.server.model.IVideoProperty;

public class CollectionSerializer extends StdSerializer<VideoCollection> {

	public CollectionSerializer() {
		this(null);
	}
	
	protected CollectionSerializer(Class<VideoCollection> t) {
		super(t);
	}

	@Override
	public void serialize(VideoCollection collection, JsonGenerator generator, SerializerProvider provider)
			throws IOException {

		generator.writeStartObject();
		generator.writeFieldName("id");
		generator.writeNumber(collection.getId());
		generator.writeFieldName("name");
		generator.writeString(collection.getName());
		generator.writeEndObject();
	}

}
