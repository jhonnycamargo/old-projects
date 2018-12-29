package co.merkhet.trex.model;

import static co.merkhet.trex.model.FinancialAccountTransaction.DEPOSIT;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue(DEPOSIT)
@EqualsAndHashCode(callSuper = true)
public @Data class Deposit extends FinancialAccountTransaction {

}
