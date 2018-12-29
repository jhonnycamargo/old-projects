package co.merkhet.trex.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import co.merkhet.trex.constant.Gender;

import static co.merkhet.trex.constant.Gender.FEMALE;
import static co.merkhet.trex.constant.Gender.MALE;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

	@Override
	public String convertToDatabaseColumn(Gender attribute) {
		switch (attribute) {
		case MALE:
			return "M";
		case FEMALE:
			return "F";
		default:
			throw new IllegalArgumentException("Unknown" + attribute);
		}
	}

	@Override
	public Gender convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "M":
			return MALE;
		case "F":
			return FEMALE;
		default:
			throw new IllegalArgumentException("Unknown" + dbData);
		}
	}

}
