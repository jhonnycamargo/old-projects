package co.merkhet.cganador.business.letsplay.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import co.merkhet.cganador.business.chance.entity.Loteria;

@Entity
@Table(name = "PLAY", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"LOTERIA_ID", "FECHA", "JUGADOR" }) })
@NamedQueries({ @NamedQuery(name = Play.COUNT_BY_LOTERIA_FECHA_JUGADOR, query = "SELECT p.id FROM Play p WHERE p.loteria = :loteria AND p.fecha = :fecha AND p.jugador = :jugador") })
public class Play implements Serializable {

	private static final long serialVersionUID = 6591379693053991150L;

	public static final String COUNT_BY_LOTERIA_FECHA_JUGADOR = "co.merkhet.cganador.business.letsplay.entity.Play.countByLoteriaFechaJugador";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "play_id_seq_gen")
	@SequenceGenerator(name = "play_id_seq_gen", sequenceName = "play_id_seq", allocationSize = 1)
	@Column(name = "PLAY_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "LOTERIA_ID")
	private Loteria loteria;

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA")
	private Date fecha;

	@Column(name = "JUGADOR")
	private String jugador;

	@Column(name = "SUPER_PLENO")
	private String superPleno;

	@Column(name = "PLENO")
	private String pleno;

	@Column(name = "PATA")
	private String pata;

	@Column(name = "UNIA")
	private String unia;

	@Transient
	private int index;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Loteria getLoteria() {
		return loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public String getSuperPleno() {
		return superPleno;
	}

	public void setSuperPleno(String superPleno) {
		this.superPleno = superPleno;
	}

	public String getPleno() {
		return pleno;
	}

	public void setPleno(String pleno) {
		this.pleno = pleno;
	}

	public String getPata() {
		return pata;
	}

	public void setPata(String pata) {
		this.pata = pata;
	}

	public String getUnia() {
		return unia;
	}

	public void setUnia(String unia) {
		this.unia = unia;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
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
		Play other = (Play) obj;
		return new EqualsBuilder().append(this.id, other.id).isEquals();
	}

}
