package co.merkhet.trex.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static co.merkhet.trex.model.FinancialAccount.BANK_ACCOUNT;

@Entity
@DiscriminatorValue(BANK_ACCOUNT)
@EqualsAndHashCode(callSuper = true)
public @Data class BankAccount extends FinancialAccount {

}
