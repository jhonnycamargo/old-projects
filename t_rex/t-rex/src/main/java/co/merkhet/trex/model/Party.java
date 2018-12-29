package co.merkhet.trex.model;

import static javax.persistence.DiscriminatorType.INTEGER;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "PARTY_TYPE_ID", discriminatorType = INTEGER)
public abstract @Data class Party {

	public static final String PERSON = "1";

	@Id
	@Column(name = "PARTY_ID")
	@GeneratedValue(strategy = SEQUENCE, generator = "PARTY_PARTY_ID_SEQ")
	@SequenceGenerator(name = "PARTY_PARTY_ID_SEQ", sequenceName = "PARTY_PARTY_ID_SEQ", initialValue = 1, allocationSize = 1)
	private Long id;
}
