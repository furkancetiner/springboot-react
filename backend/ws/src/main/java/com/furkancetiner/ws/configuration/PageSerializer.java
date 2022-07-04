package com.furkancetiner.ws.configuration;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageSerializer extends JsonSerializer<Page<?>>{

	@Override
	public void serialize(Page<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeFieldName("content");
		serializers.defaultSerializeValue(value.getContent(), gen);
		gen.writeEndObject();
	}

}
