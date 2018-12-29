package co.merkhet.cganador.business.stats.cache;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class StatsCacheKey implements Serializable {

	private static final long serialVersionUID = -5921865974123966574L;

	private Long loteriaId;

	public StatsCacheKey() {
		super();
	}

	public StatsCacheKey(Long loteriaId) {
		super();
		this.loteriaId = loteriaId;
	}

	public Long getLoteriaId() {
		return loteriaId;
	}

	public void setLoteriaId(Long loteriaId) {
		this.loteriaId = loteriaId;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.loteriaId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatsCacheKey other = (StatsCacheKey) obj;
		return new EqualsBuilder().append(this.loteriaId, other.loteriaId)
				.isEquals();
	}

}
