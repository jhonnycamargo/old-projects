package co.merkhet.trex.model;

import static co.merkhet.trex.model.Party.PERSON;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import co.merkhet.trex.constant.Gender;
import co.merkhet.trex.converter.GenderConverter;
import co.merkhet.trex.serializer.GenderDeserializer;
import co.merkhet.trex.serializer.GenderSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue(PERSON)
@EqualsAndHashCode(callSuper = true)
public @Data class Person extends Party {

	@NotBlank
	@Column(name = "CURRENT_LAST_NAME")
	private String currentLastName;

	@NotBlank
	@Column(name = "CURRENT_FIRST_NAME")
	private String currentFirstName;

	@Column(name = "BIRTHDATE")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate birthdate;

	@Convert(converter = GenderConverter.class)
	@JsonSerialize(using = GenderSerializer.class)
	@JsonDeserialize(using = GenderDeserializer.class)
	private Gender gender;

}
