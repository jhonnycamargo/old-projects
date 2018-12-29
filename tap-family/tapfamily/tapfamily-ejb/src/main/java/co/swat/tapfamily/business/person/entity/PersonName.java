package co.swat.tapfamily.business.person.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PERSON_NAME")
public class PersonName implements Serializable {

  private static final long serialVersionUID = -26536506970108427L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_NAME_SEQ")
  @SequenceGenerator(name = "PERSON_NAME_SEQ", sequenceName = "PERSON_NAME_SEQ", allocationSize = 1)
  @Column(name = "PERSON_NAME_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "PERSON_NAME_TYPE_ID")
  private PersonNameType personNameType;

  @NotNull
  @NotEmpty
  @Size(min = 1, max = 40)
  private String name;

  @NotNull
  @Past
  private Date fromDate;

  @Past
  private Date thruDate;

  @ManyToOne
  @JoinColumn(name = "PARTY_ID")
  private Person person;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PersonNameType getPersonNameType() {
    return personNameType;
  }

  public void setPersonNameType(PersonNameType personNameType) {
    this.personNameType = personNameType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getThruDate() {
    return thruDate;
  }

  public void setThruDate(Date thruDate) {
    this.thruDate = thruDate;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder().append(this.id).toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (this == obj) {
      return true;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    PersonName other = (PersonName) obj;
    return new EqualsBuilder().append(this.id, other.id)
        .append(this.name, other.name).isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("name", name)
        .append("fromDate", fromDate).toString();
  }
}
