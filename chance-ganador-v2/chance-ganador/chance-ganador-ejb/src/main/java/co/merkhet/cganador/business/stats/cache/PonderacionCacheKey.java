package co.merkhet.cganador.business.stats.cache;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import co.merkhet.cganador.business.chance.entity.Loteria;

public class PonderacionCacheKey implements Serializable {

	private static final long serialVersionUID = -6548948476279383513L;

	private Loteria loteria;
	private int posicionInicial;
	private int posicionFinal;

	public PonderacionCacheKey() {
		super();
	}

	public PonderacionCacheKey(Loteria loteria, int posicionInicial,
			int posicionFinal) {
		super();
		this.loteria = loteria;
		this.posicionInicial = posicionInicial;
		this.posicionFinal = posicionFinal;
	}

	public Loteria getLoteria() {
		return loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
	}

	public int getPosicionInicial() {
		return posicionInicial;
	}

	public void setPosicionInicial(int posicionInicial) {
		this.posicionInicial = posicionInicial;
	}

	public int getPosicionFinal() {
		return posicionFinal;
	}

	public void setPosicionFinal(int posicionFinal) {
		this.posicionFinal = posicionFinal;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.loteria)
				.append(this.posicionInicial).append(this.posicionFinal)
				.toHashCode();
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
		PonderacionCacheKey other = (PonderacionCacheKey) obj;

		return new EqualsBuilder().append(this.loteria, other.loteria)
				.append(this.posicionInicial, other.posicionInicial)
				.append(this.posicionFinal, other.posicionFinal).isEquals();
	}

}
