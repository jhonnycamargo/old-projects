package co.merkhet.cganador.business.stats.control;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import co.merkhet.cganador.business.chance.boundary.LoteriasBean;
import co.merkhet.cganador.business.chance.entity.Loteria;
import co.merkhet.cganador.business.stats.boundary.StatsBean;
import co.merkhet.cganador.business.stats.entity.Stat;

public class StatsViewViewModel {

	private static final int UNIA_INI_POSICION = 4;
	private static final int PATA_INI_POSICION = 3;
	private static final int PLENO_INI_POSICION = 2;
	private static final int SUPERPLENO_INI_POSICION = 1;

	@WireVariable
	private StatsBean statsBean;
	@WireVariable
	private LoteriasBean loteriasBean;

	private Date fechaIni;
	private Date fechaFin;
	private Loteria loteria;

	public List<Stat> getStatsUnia() {
		return statsBean.selectStats(loteria, fechaIni, fechaFin,
				UNIA_INI_POSICION, UNIA_INI_POSICION);
	}

	public List<Stat> getStatsPata() {
		return statsBean.selectStats(loteria, fechaIni, fechaFin,
				PATA_INI_POSICION, UNIA_INI_POSICION);
	}

	public List<Stat> getStatsPleno() {
		return statsBean.selectStats(loteria, fechaIni, fechaFin,
				PLENO_INI_POSICION, UNIA_INI_POSICION);
	}

	public List<Stat> getStatsSuperPleno() {
		return statsBean.selectStats(loteria, fechaIni, fechaFin,
				SUPERPLENO_INI_POSICION, UNIA_INI_POSICION);
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Loteria getLoteria() {
		return loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
	}

	public List<Loteria> getLoterias() {
		return loteriasBean.selectAllLoteries();
	}

	@Command
	@NotifyChange({ "statsUnia", "statsPata", "statsPleno", "statsSuperPleno" })
	public void calcularStats() {
	}
}
