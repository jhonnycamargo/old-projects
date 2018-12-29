package co.merkhet.cganador.business.sorteos.control;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import co.merkhet.cganador.business.chance.boundary.LoteriasBean;
import co.merkhet.cganador.business.chance.boundary.Sorteos;
import co.merkhet.cganador.business.chance.entity.Loteria;
import co.merkhet.cganador.business.chance.entity.Sorteo;

public class SorteosViewViewModel {

	int pageSize = 10;
	int activePage = 0;

	@WireVariable
	private Sorteos sorteos;
	@WireVariable
	private LoteriasBean loteriasBean;

	private Date fechaIni;
	private Date fechaFin;
	private Loteria loteria;

	public long getTotalSize() {
		return sorteos.selectCountSorteosWithLimit(loteria, fechaIni, fechaFin,
				activePage, pageSize);
	}

	public List<Sorteo> getSorteos() {
		return sorteos.selectSorteosWithLimit(loteria, fechaIni, fechaFin,
				activePage, pageSize);
	}

	public int getPageSize() {
		return pageSize;
	}

	@NotifyChange("sorteos")
	public void setActivePage(int activePage) {
		this.activePage = activePage;
	}

	public int getActivePage() {
		return activePage;
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

	public List<Loteria> getLoterias() {
		return loteriasBean.selectAllLoteries();
	}

	public Loteria getLoteria() {
		return loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
	}

	@Command
	@NotifyChange({ "sorteos", "totalSize" })
	public void buscarSorteos() {
		this.activePage = 0;
	}

}
