package co.merkhet.cganador.business.stats.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Stat implements Serializable {

	private static final long serialVersionUID = -4888300955874490030L;

	public static final String STAT_MAPPING = "co.merkhet.cganador.business.stats.entity.Stat.statMapping";

	private String numero;
	private double porcentaje;
	private long days;

	public Stat() {
	}

	public Stat(String numero, double porcentaje, long days) {
		this.numero = numero;
		this.porcentaje = porcentaje;
		this.days = days;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
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
		Stat other = (Stat) obj;
		return new EqualsBuilder().append(this.numero, other.numero).isEquals();
	}

}
