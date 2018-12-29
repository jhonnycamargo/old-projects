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
@DiscriminatorColumn(name = "FINANCIAL_ACCOUNT_TYPE_ID", discriminatorType = INTEGER)
public abstract @Data class FinancialAccount {

	public static final String BANK_ACCOUNT = "1";

	@Id
	@Column(name = "FINANCIAL_ACCOUNT_ID")
	@GeneratedValue(strategy = SEQUENCE, generator = "FINANCIAL_ACCOUNT_FINANCIAL_ACCOUNT_ID_SEQ")
	@SequenceGenerator(name = "FINANCIAL_ACCOUNT_FINANCIAL_ACCOUNT_ID_SEQ", sequenceName = "FINANCIAL_ACCOUNT_FINANCIAL_ACCOUNT_ID_SEQ", initialValue = 1, allocationSize = 1)
	private Long id;

	private String financialAccountName;
}
