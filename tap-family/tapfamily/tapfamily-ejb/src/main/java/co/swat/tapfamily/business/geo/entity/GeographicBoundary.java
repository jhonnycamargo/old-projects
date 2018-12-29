package co.swat.tapfamily.business.geo.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "GEOGRAPHIC_BOUNDARY")
public class GeographicBoundary implements Serializable {

  private static final long serialVersionUID = 3784982413904184176L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEOGRAPHIC_BOUNDARY_SEQ")
  @SequenceGenerator(name = "GEOGRAPHIC_BOUNDARY_SEQ", sequenceName = "GEOGRAPHIC_BOUNDARY_SEQ", allocationSize = 1)
  @Column(name = "GEO_ID")
  private Long id;

  @NotNull
  @NotEmpty
  @Size(min = 2, max = 40)
  private String name;

  @Size(max = 40)
  private String geoCode;

  @Size(max = 40)
  private String abbreviation;

  @JoinTable(name = "GEOGRAPHIC_BOUNDARY_ASSOCIATION", joinColumns = { @JoinColumn(name = "GEO_ID_FROM", referencedColumnName = "GEO_ID", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "GEO_ID_TO", referencedColumnName = "GEO_ID", nullable = false) })
  @ManyToMany
  private Collection<GeographicBoundary> from;

  @ManyToMany(mappedBy = "from")
  private Collection<GeographicBoundary> to;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGeoCode() {
    return geoCode;
  }

  public void setGeoCode(String geoCode) {
    this.geoCode = geoCode;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public Collection<GeographicBoundary> getFrom() {
    return from;
  }

  public void setFrom(Collection<GeographicBoundary> from) {
    this.from = from;
  }

  public Collection<GeographicBoundary> getTo() {
    return to;
  }

  public void setTo(Collection<GeographicBoundary> to) {
    this.to = to;
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
    GeographicBoundary other = (GeographicBoundary) obj;
    return new EqualsBuilder().append(this.id, other.id)
        .append(this.name, other.name).isEquals();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("id", id).append("name", name)
        .append("geoCode", geoCode)
        .append("abbreviation", abbreviation).toString();
  }
}
