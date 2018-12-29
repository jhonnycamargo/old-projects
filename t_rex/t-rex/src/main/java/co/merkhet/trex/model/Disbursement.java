package co.merkhet.trex.model;

import static co.merkhet.trex.model.Payment.DISBURSMENT;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue(DISBURSMENT)
@EqualsAndHashCode(callSuper = true)
public @Data class Disbursement extends Payment {

}
