package co.merkhet.cganador.business.chance.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import co.merkhet.cganador.business.general.entity.Pais;

@Entity
@Table(name = "LOTERIA")
@NamedQueries({ @NamedQuery(name = Loteria.FIND_ALL, query = "SELECT o FROM Loteria o") })
public class Loteria implements Serializable {

	private static final long serialVersionUID = -8850541429100538510L;

	public static final String FIND_ALL = "co.merkhet.cganador.business.chance.entity.Loteria.findAll";

	@Id
	@GeneratedValue
	@Column(name = "LOTERIA_ID")
	private Long id;

	@NotNull
	@NotEmpty
	@Size(max = 50, min = 2)
	@Column(name = "NOMBRE")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "PAIS_ID")
	private Pais pais;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.id).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Loteria other = (Loteria) obj;

		return new EqualsBuilder().append(this.id, other.id).isEquals();
	}

}
