package co.swat.tapfamily.business.person.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import co.swat.tapfamily.business.party.entity.PartyRole;

@Entity
@Table(name = "PERSON_ROLE")
public class PersonRole extends PartyRole {

  private static final long serialVersionUID = 3714208907084970774L;

}
