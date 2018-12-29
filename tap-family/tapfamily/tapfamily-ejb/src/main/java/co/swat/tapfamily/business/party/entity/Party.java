package co.swat.tapfamily.business.party.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "PARTY")
public class Party implements Serializable {

  private static final long serialVersionUID = -7302945848861867245L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTY_SEQ")
  @SequenceGenerator(name = "PARTY_SEQ", sequenceName = "PARTY_SEQ", allocationSize = 1)
  @Column(name = "PARTY_ID")
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    Party other = (Party) obj;
    return new EqualsBuilder().append(this.id, other.id).isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).toString();
  }

}
