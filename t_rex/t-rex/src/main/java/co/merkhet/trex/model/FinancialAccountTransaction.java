package co.merkhet.trex.model;

import static javax.persistence.DiscriminatorType.INTEGER;
import static javax.persistence.GenerationType.SEQUENCE;
import static javax.persistence.InheritanceType.JOINED;

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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

@Entity
@Inheritance(strategy = JOINED)
@DiscriminatorColumn(name = "FINANCIAL_ACCOUNT_TRANSACTION_TYPE_ID", discriminatorType = INTEGER)
public abstract @Data class FinancialAccountTransaction {

	public static final String WITHDRAWAL = "1";

	public static final String DEPOSIT = "2";

	@Id
	@Column(name = "FINANCIAL_ACCOUNT_TRANSACTION_ID")
	@GeneratedValue(strategy = SEQUENCE, generator = "FINANCIAL_ACCOUNT_TRANSACTION_FINANCIAL_ACCOUNT_TRANSACTION_ID_SEQ")
	@SequenceGenerator(name = "FINANCIAL_ACCOUNT_TRANSACTION_FINANCIAL_ACCOUNT_TRANSACTION_ID_SEQ", sequenceName = "FINANCIAL_ACCOUNT_TRANSACTION_FINANCIAL_ACCOUNT_TRANSACTION_ID_SEQ", initialValue = 1, allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "FINANCIAL_ACCOUNT_ID")
	private FinancialAccount financialAccount;

	@ManyToOne
	@JoinColumn(name = "PARTY_ID")
	private Party party;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime transactionDate;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime entryDate;
}
