package co.merkhet.cganador.business.labs.control;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import co.merkhet.cganador.business.chance.boundary.LoteriasBean;
import co.merkhet.cganador.business.chance.boundary.Sorteos;
import co.merkhet.cganador.business.chance.entity.Loteria;
import co.merkhet.cganador.business.chance.entity.Sorteo;
import co.merkhet.cganador.business.stats.comparator.SorteoMathComparator;
import co.merkhet.cganador.business.stats.entity.SorteoMath;

public class LaboratoriosViewViewModel {

	@WireVariable
	private Sorteos sorteos;
	@WireVariable
	private LoteriasBean loteriasBean;

	private Date fechaIni;
	private Date fechaFin;
	private Loteria loteria;

	private List<SorteoMath> sorteoMaths;
	private List<SorteoMath> sorteoMathsPata;
	private List<SorteoMath> sorteoMathsPleno;
	private List<SorteoMath> sorteoMathsSuperPleno;

	Comparator<SorteoMath> numeroComparatorAsc = new SorteoMathComparator(true,
			1);
	Comparator<SorteoMath> numeroComparatorDesc = new SorteoMathComparator(
			false, 1);
	Comparator<SorteoMath> mediaComparatorAsc = new SorteoMathComparator(true,
			2);
	Comparator<SorteoMath> mediaComparatorDesc = new SorteoMathComparator(
			false, 2);
	Comparator<SorteoMath> desvComparatorAsc = new SorteoMathComparator(true, 3);
	Comparator<SorteoMath> desvComparatorDesc = new SorteoMathComparator(false,
			3);
	Comparator<SorteoMath> noSortsAtrasComparatorAsc = new SorteoMathComparator(
			true, 4);
	Comparator<SorteoMath> noSortsAtrasComparatorDesc = new SorteoMathComparator(
			false, 4);
	Comparator<SorteoMath> rateComparatorAsc = new SorteoMathComparator(true, 5);
	Comparator<SorteoMath> rateComparatorDesc = new SorteoMathComparator(false,
			5);
	Comparator<SorteoMath> tasaComparatorAsc = new SorteoMathComparator(true, 6);
	Comparator<SorteoMath> tasaComparatorDesc = new SorteoMathComparator(false,
			6);

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

	public List<SorteoMath> getSorteoMaths() {
		return sorteoMaths;
	}

	public void setSorteoMaths(List<SorteoMath> sorteoMaths) {
		this.sorteoMaths = sorteoMaths;
	}

	public List<SorteoMath> getSorteoMathsPata() {
		return sorteoMathsPata;
	}

	public void setSorteoMathsPata(List<SorteoMath> sorteoMathsPata) {
		this.sorteoMathsPata = sorteoMathsPata;
	}

	public List<SorteoMath> getSorteoMathsPleno() {
		return sorteoMathsPleno;
	}

	public void setSorteoMathsPleno(List<SorteoMath> sorteoMathsPleno) {
		this.sorteoMathsPleno = sorteoMathsPleno;
	}

	public List<SorteoMath> getSorteoMathsSuperPleno() {
		return sorteoMathsSuperPleno;
	}

	public void setSorteoMathsSuperPleno(List<SorteoMath> sorteoMathsSuperPleno) {
		this.sorteoMathsSuperPleno = sorteoMathsSuperPleno;
	}

	public Comparator<SorteoMath> getNumeroComparatorAsc() {
		return numeroComparatorAsc;
	}

	public void setNumeroComparatorAsc(
			Comparator<SorteoMath> numeroComparatorAsc) {
		this.numeroComparatorAsc = numeroComparatorAsc;
	}

	public Comparator<SorteoMath> getNumeroComparatorDesc() {
		return numeroComparatorDesc;
	}

	public void setNumeroComparatorDesc(
			Comparator<SorteoMath> numeroComparatorDesc) {
		this.numeroComparatorDesc = numeroComparatorDesc;
	}

	public Comparator<SorteoMath> getMediaComparatorAsc() {
		return mediaComparatorAsc;
	}

	public void setMediaComparatorAsc(Comparator<SorteoMath> mediaComparatorAsc) {
		this.mediaComparatorAsc = mediaComparatorAsc;
	}

	public Comparator<SorteoMath> getMediaComparatorDesc() {
		return mediaComparatorDesc;
	}

	public void setMediaComparatorDesc(
			Comparator<SorteoMath> mediaComparatorDesc) {
		this.mediaComparatorDesc = mediaComparatorDesc;
	}

	public Comparator<SorteoMath> getDesvComparatorAsc() {
		return desvComparatorAsc;
	}

	public void setDesvComparatorAsc(Comparator<SorteoMath> desvComparatorAsc) {
		this.desvComparatorAsc = desvComparatorAsc;
	}

	public Comparator<SorteoMath> getDesvComparatorDesc() {
		return desvComparatorDesc;
	}

	public void setDesvComparatorDesc(Comparator<SorteoMath> desvComparatorDesc) {
		this.desvComparatorDesc = desvComparatorDesc;
	}

	public Comparator<SorteoMath> getNoSortsAtrasComparatorAsc() {
		return noSortsAtrasComparatorAsc;
	}

	public void setNoSortsAtrasComparatorAsc(
			Comparator<SorteoMath> noSortsAtrasComparatorAsc) {
		this.noSortsAtrasComparatorAsc = noSortsAtrasComparatorAsc;
	}

	public Comparator<SorteoMath> getNoSortsAtrasComparatorDesc() {
		return noSortsAtrasComparatorDesc;
	}

	public void setNoSortsAtrasComparatorDesc(
			Comparator<SorteoMath> noSortsAtrasComparatorDesc) {
		this.noSortsAtrasComparatorDesc = noSortsAtrasComparatorDesc;
	}

	public Comparator<SorteoMath> getRateComparatorAsc() {
		return rateComparatorAsc;
	}

	public void setRateComparatorAsc(Comparator<SorteoMath> rateComparatorAsc) {
		this.rateComparatorAsc = rateComparatorAsc;
	}

	public Comparator<SorteoMath> getRateComparatorDesc() {
		return rateComparatorDesc;
	}

	public void setRateComparatorDesc(Comparator<SorteoMath> rateComparatorDesc) {
		this.rateComparatorDesc = rateComparatorDesc;
	}

	public Comparator<SorteoMath> getTasaComparatorAsc() {
		return tasaComparatorAsc;
	}

	public void setTasaComparatorAsc(Comparator<SorteoMath> tasaComparatorAsc) {
		this.tasaComparatorAsc = tasaComparatorAsc;
	}

	public Comparator<SorteoMath> getTasaComparatorDesc() {
		return tasaComparatorDesc;
	}

	public void setTasaComparatorDesc(Comparator<SorteoMath> tasaComparatorDesc) {
		this.tasaComparatorDesc = tasaComparatorDesc;
	}

	@Command
	@NotifyChange({ "sorteoMaths", "sorteoMathsPata", "sorteoMathsPleno",
			"sorteoMathsSuperPleno" })
	public void calcularLabs() {
		if (loteria != null) {
			List<Sorteo> sorts = sorteos.findAll(loteria);
			this.sorteoMaths = new ArrayList<SorteoMath>();
			this.sorteoMathsPata = new ArrayList<SorteoMath>();
			this.sorteoMathsPleno = new ArrayList<SorteoMath>();
			this.sorteoMathsSuperPleno = new ArrayList<SorteoMath>();
			calcularLabs(sorts, this.sorteoMaths, 3);
			calcularLabs(sorts, this.sorteoMathsPata, 2);
			calcularLabs(sorts, this.sorteoMathsPleno, 1);
			calcularLabs(sorts, this.sorteoMathsSuperPleno, 0);
			//simulacion(sorts);
		}
	}

	private void simulacion(List<Sorteo> sorts) {
		int gane = 0;
		int juge = 0;

		for (int i = 10; i < sorts.size(); i++) {
			juge++;
			List<SorteoMath> sorteoMaths = new ArrayList<SorteoMath>();
			calcularLabs(sorts.subList(0, i), sorteoMaths, 2);
			String uniaSel = seleccionar(sorteoMaths);
			Sorteo actual = sorts.get(i);
			if (actual.getResultado().endsWith(uniaSel)) {
				System.out.println("Gane: " + uniaSel);
				gane++;
			}
		}
		System.out.println("GANE: " + gane + ", JUGE: " + juge);
		System.out.println("APOSTE: " + juge * 1000 + ", GANE: " + gane * 50000);
	}

	private String seleccionar(List<SorteoMath> sms) {
		SorteoMath ini = sms.get(0);
		String result = ini.getResultado();
		double rate = ini.getSorteosAtras()*60 + ini.getProporcion()*40;
		for (int i = 0; i < sms.size(); i++) {
			double r = sms.get(i).getSorteosAtras()*60 + sms.get(i).getProporcion()*40;
			if (r > rate) {
				result = sms.get(i).getResultado();
				rate = r;
			}
		}
		return result;
	}

	private void calcularLabs(List<Sorteo> sorteos, List<SorteoMath> labs,
			int index) {
		Map<String, List<Integer>> difs = new HashMap<String, List<Integer>>();
		for (int i = 0; i < sorteos.size(); i++) {
			String res = sorteos.get(i).getResultado();
			String unia = res.substring(index);
			List<Integer> d = difs.get(unia);
			if (d == null) {
				d = new ArrayList<Integer>();
				difs.put(unia, d);
			}
			int count = 1;
			for (int j = i + 1; j < sorteos.size(); j++) {
				String res2 = sorteos.get(j).getResultado();
				if (unia.equals(res2.substring(index))) {
					d.add(count);
					break;
				} else {
					count++;
				}
			}
		}
		calcularMaths(difs, labs);
		for (SorteoMath sorteoM : labs) {
			sorteoM.setSorteosAtras(sorteosAtras(sorteos,
					sorteoM.getResultado()));
			sorteoM.setProporcion(proporcion(sorteos, sorteoM.getResultado()));
		}
	}

	private double proporcion(List<Sorteo> sorteos, String n) {
		int count = 0;
		for (int i = 0; i < sorteos.size(); i++) {
			if (sorteos.get(i).getResultado().endsWith(n)) {
				count++;
			}
		}

		return count * 100.0 / sorteos.size();
	}

	private void calcularMaths(Map<String, List<Integer>> difs,
			List<SorteoMath> labs) {
		Set<String> keys = difs.keySet();
		for (String key : keys) {
			SorteoMath sorteoMath = new SorteoMath();
			sorteoMath.setResultado(key);
			sorteoMath.setMedia(media(difs.get(key)));
			sorteoMath.setDesviacion(desviacion(difs.get(key)));
			labs.add(sorteoMath);
		}
	}

	private int sorteosAtras(List<Sorteo> sorteos, String n) {
		int count = 0;
		for (int i = (sorteos.size() - 1); i >= 0; i--) {
			if (sorteos.get(i).getResultado().endsWith(n)) {
				return count;
			}
			count++;
		}

		return count;
	}

	private double desviacion(List<Integer> list) {
		double desviacion = 0;
		double media = media(list);
		double sumatoria = 0;
		for (Integer integer : list) {
			sumatoria += ((integer - media) * (integer - media));
		}
		desviacion = Math.sqrt(sumatoria / (list.size() - 1));
		return desviacion;
	}

	private double media(List<Integer> list) {
		double sum = 0;
		for (Integer integer : list) {
			sum += integer;
		}
		return sum / list.size();
	}

}
