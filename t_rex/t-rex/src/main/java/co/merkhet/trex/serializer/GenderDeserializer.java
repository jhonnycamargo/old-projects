package co.merkhet.trex.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import co.merkhet.trex.constant.Gender;

public class GenderDeserializer extends JsonDeserializer<Gender> {

	@Override
	public Gender deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		return Gender.valueOf(p.getText());
	}

}
