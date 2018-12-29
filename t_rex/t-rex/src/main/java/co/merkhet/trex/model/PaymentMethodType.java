package co.merkhet.trex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Entity
public @Data class PaymentMethodType {

	@Id
	@Column(name = "PAYMENT_METHOD_TYPE_ID")
	private Integer id;

	@NotBlank
	private String description;
}
