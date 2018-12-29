package co.swat.tapfamily.business.person.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import co.swat.tapfamily.business.party.entity.Party;

@Entity
@Table(name = "PERSON")
public class Person extends Party {

  private static final long serialVersionUID = -8026736994469689391L;

  @Past
  private Date birthday;

  @ManyToOne
  @JoinColumn(name = "GENDER_TYPE_ID")
  private GenderType genderType;

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public GenderType getGenderType() {
    return genderType;
  }

  public void setGenderType(GenderType genderType) {
    this.genderType = genderType;
  }

}
