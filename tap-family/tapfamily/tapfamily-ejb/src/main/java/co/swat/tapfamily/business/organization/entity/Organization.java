package co.swat.tapfamily.business.organization.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import co.swat.tapfamily.business.party.entity.Party;

@Entity
@Table(name = "ORGANIZATION")
public class Organization extends Party {

  private static final long serialVersionUID = -4283942625988993696L;

  @NotNull
  @NotEmpty
  @Size(min = 1, max = 40)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
