package co.merkhet.trex.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static co.merkhet.trex.model.Payment.RECEIPT;

@Entity
@DiscriminatorValue(RECEIPT)
@EqualsAndHashCode(callSuper = true)
public @Data class Receipt extends Payment {

}