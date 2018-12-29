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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PARTY_ROLE", uniqueConstraints = { @UniqueConstraint(columnNames = {
    "PARTY_ID", "ROLE_TYPE_ID" }) })
public class PartyRole implements Serializable {

  private static final long serialVersionUID = 8661591301136740645L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTY_ROLE_SEQ")
  @SequenceGenerator(name = "PARTY_ROLE_SEQ", sequenceName = "PARTY_ROLE_SEQ", allocationSize = 1)
  @Column(name = "PARTY_ROLE_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "PARTY_ID")
  private Party party;

  @ManyToOne
  @JoinColumn(name = "ROLE_TYPE_ID")
  private PartyRoleType partyRoleType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Party getParty() {
    return party;
  }

  public void setParty(Party party) {
    this.party = party;
  }

  public PartyRoleType getPartyRoleType() {
    return partyRoleType;
  }

  public void setPartyRoleType(PartyRoleType partyRoleType) {
    this.partyRoleType = partyRoleType;
  }

}
