package co.merkhet.cganador.business.stats.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SorteoMath implements Serializable {

	private static final long serialVersionUID = -462948621655444260L;

	private String resultado;
	private double media;
	private double desviacion;
	private int sorteosAtras;
	private double proporcion;

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double getDesviacion() {
		return desviacion;
	}

	public void setDesviacion(double desviacion) {
		this.desviacion = desviacion;
	}

	public int getSorteosAtras() {
		return sorteosAtras;
	}

	public void setSorteosAtras(int sorteosAtras) {
		this.sorteosAtras = sorteosAtras;
	}

	public double getRate() {
		return Math.abs((this.media + this.desviacion) - sorteosAtras);
	}

	public double getProporcion() {
		return proporcion;
	}

	public void setProporcion(double tasa) {
		this.proporcion = tasa;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.resultado).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SorteoMath other = (SorteoMath) obj;
		return new EqualsBuilder().append(this.resultado, other.resultado)
				.isEquals();
	}

}
