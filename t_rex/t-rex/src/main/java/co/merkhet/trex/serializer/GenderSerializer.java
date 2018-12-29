package co.merkhet.trex.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import co.merkhet.trex.constant.Gender;

public class GenderSerializer extends JsonSerializer<Gender> {

	@Override
	public void serialize(Gender value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeString(value.name());
	}

}
