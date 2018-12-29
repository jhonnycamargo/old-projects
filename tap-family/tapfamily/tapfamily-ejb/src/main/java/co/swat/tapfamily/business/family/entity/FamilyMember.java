package co.swat.tapfamily.business.family.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import co.swat.tapfamily.business.person.entity.PersonRole;

@Entity
@Table(name = "FAMILY_MEMBER")
public class FamilyMember extends PersonRole {

  private static final long serialVersionUID = 8308111391326017110L;

}
