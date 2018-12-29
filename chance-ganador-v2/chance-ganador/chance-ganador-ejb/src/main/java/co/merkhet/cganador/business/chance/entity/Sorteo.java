package co.merkhet.cganador.business.chance.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import co.merkhet.cganador.business.stats.entity.Ponderacion;
import co.merkhet.cganador.business.stats.entity.Stat;

@SqlResultSetMappings({
		@SqlResultSetMapping(name = Stat.STAT_MAPPING, classes = @ConstructorResult(targetClass = co.merkhet.cganador.business.stats.entity.Stat.class, columns = {
				@ColumnResult(name = "numero", type = String.class),
				@ColumnResult(name = "porcentaje", type = Double.class),
				@ColumnResult(name = "days", type = Long.class) })),
		@SqlResultSetMapping(name = Ponderacion.PONDERACION_MAPPING, classes = @ConstructorResult(targetClass = co.merkhet.cganador.business.stats.entity.Ponderacion.class, columns = {
				@ColumnResult(name = "numero", type = String.class),
				@ColumnResult(name = "ponderacion", type = Long.class) })) })
@Entity
@Table(name = "SORTEO")
@NamedQueries({
		@NamedQuery(name = Sorteo.FIND_ALL, query = "SELECT s FROM Sorteo s"),
		@NamedQuery(name = Sorteo.COUNT_ALL, query = "SELECT count(s) FROM Sorteo s"),
		@NamedQuery(name = Sorteo.FIND_ALL_BY_LOTERY, query = "SELECT s FROM Sorteo s WHERE s.loteria = :loteria ORDER BY s.fecha ASC") })
public class Sorteo implements Serializable {

	private static final long serialVersionUID = 7836932872706354736L;

	public static final String FIND_ALL = "co.merkhet.cganador.business.chance.entity.Sorteo.FindAll";
	public static final String COUNT_ALL = "co.merkhet.cganador.business.chance.entity.Sorteo.CountAll";
	public static final String FIND_ALL_BY_LOTERY = "co.merkhet.cganador.business.chance.entity.Sorteo.FindAllByLotery";

	@Id
	@GeneratedValue
	@Column(name = "SORTEO_ID")
	private Long id;

	@Column(name = "NUMERO")
	private Integer numero;

	@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA")
	private Date fecha;

	@NotNull
	@Size(max = 4, min = 4)
	@Column(name = "RESULTADO")
	private String resultado;

	@ManyToOne
	@JoinColumn(name = "LOTERIA_ID")
	private Loteria loteria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public Loteria getLoteria() {
		return loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
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
		Sorteo other = (Sorteo) obj;
		return new EqualsBuilder().append(this.id, other.id).isEquals();
	}

}
