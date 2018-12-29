package co.merkhet.trex.model;

import static javax.persistence.DiscriminatorType.INTEGER;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.InheritanceType.JOINED;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

@Entity
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "PAYMENT_TYPE_ID", discriminatorType = INTEGER)
public abstract @Data class Payment {

	public static final String RECEIPT = "1";

	public static final String DISBURSMENT = "2";

	@Id
	@Column(name = "PAYMENT_ID")
	@GeneratedValue(strategy = SEQUENCE, generator = "PAYMENT_PAYMENT_ID_SEQ")
	@SequenceGenerator(name = "PAYMENT_PAYMENT_ID_SEQ", sequenceName = "PAYMENT_PAYMENT_ID_SEQ", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "PAYMENT_METHOD_TYPE_ID")
	private PaymentMethodType paymentMethodType;

	@ManyToOne
	@JoinColumn(name = "PARTY_ID_TO")
	private Party partyTo;

	@ManyToOne
	@JoinColumn(name = "PARTY_ID_FROM")
	private Party partyFrom;

	@NotNull
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime effectiveDate;

	@NotNull
	private Long paymentRefNum;

	@NotNull
	private BigDecimal amount;

	private String comment;

}
