package co.swat.tapfamily.business.party.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PARTY_RELATIONSHIP_TYPE")
public class PartyRelationshipType implements Serializable {

  private static final long serialVersionUID = 3453349173425017109L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTY_RELATIONSHIP_TYPE_SEQ")
  @SequenceGenerator(name = "PARTY_RELATIONSHIP_TYPE_SEQ", sequenceName = "PARTY_RELATIONSHIP_TYPE_SEQ", allocationSize = 1)
  @Column(name = "PARTY_RELATIONSHIP_TYPE_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "ROLE_TYPE_ID_VALID_TO")
  private PartyRoleType validTo;

  @ManyToOne
  @JoinColumn(name = "ROLE_TYPE_ID_VALID_FROM")
  private PartyRoleType validFrom;

  @NotEmpty
  @Size(max = 255)
  private String description;

  @NotNull
  @NotEmpty
  @Size(min = 1, max = 40)
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PartyRoleType getValidTo() {
    return validTo;
  }

  public void setValidTo(PartyRoleType validTo) {
    this.validTo = validTo;
  }

  public PartyRoleType getValidFrom() {
    return validFrom;
  }

  public void setValidFrom(PartyRoleType validFrom) {
    this.validFrom = validFrom;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
