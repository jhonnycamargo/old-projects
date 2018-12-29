package co.swat.tapfamily.business.person.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "GENDER_TYPE")
public class GenderType implements Serializable {

  private static final long serialVersionUID = -6146946741560041986L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENDER_TYPE_SEQ")
  @SequenceGenerator(name = "GENDER_TYPE_SEQ", sequenceName = "GENDER_TYPE_SEQ", allocationSize = 1)
  @Column(name = "GENDER_TYPE_ID")
  private Long id;

  @NotNull
  @NotEmpty
  @Size(min = 2, max = 255)
  @Column(name = "DESCRIPTION", unique = true)
  private String description;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
    GenderType other = (GenderType) obj;
    return new EqualsBuilder().append(this.id, other.id)
        .append(this.description, other.description).isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id)
        .append("description", description).toString();
  }
}
