package co.merkhet.cganador.business.stats.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Ponderacion implements Serializable {

	private static final long serialVersionUID = 3075653184106667031L;

	public static final String PONDERACION_MAPPING = "co.merkhet.cganador.business.stats.entity.Ponderacion.ponderacionMapping";

	private String numero;
	private long ponderacion;

	public Ponderacion() {
		super();
	}

	public Ponderacion(String numero, long ponderacion) {
		super();
		this.numero = numero;
		this.ponderacion = ponderacion;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public long getPonderacion() {
		return ponderacion;
	}

	public void setPonderacion(long ponderacion) {
		this.ponderacion = ponderacion;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.numero).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ponderacion other = (Ponderacion) obj;

		return new EqualsBuilder().append(this.numero, other.numero).isEquals();
	}

}
