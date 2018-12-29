package co.merkhet.cganador.business.general.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "PAIS")
public class Pais implements Serializable {

	private static final long serialVersionUID = 9126615126893757553L;

	@Id
	@GeneratedValue
	@Column(name = "PAIS_ID")
	private Long id;

	@NotNull
	@NotEmpty
	@Size(min = 2, max = 50)
	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "CODIGO")
	private String codigo;

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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.id).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pais other = (Pais) obj;
		return new EqualsBuilder().append(this.id, other.id).isEquals();
	}

}
