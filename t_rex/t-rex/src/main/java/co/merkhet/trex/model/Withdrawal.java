package co.merkhet.trex.model;

import static co.merkhet.trex.model.FinancialAccountTransaction.WITHDRAWAL;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue(WITHDRAWAL)
@EqualsAndHashCode(callSuper = true)
public @Data class Withdrawal extends FinancialAccountTransaction {

	// private Payment payment;
}
