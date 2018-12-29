package co.merkhet.cganador.business.stats.comparator;

import java.util.Comparator;

import co.merkhet.cganador.business.stats.entity.SorteoMath;

public class SorteoMathComparator implements Comparator<SorteoMath> {

	private boolean asc = true;
	private int type = 0;

	public SorteoMathComparator(boolean asc, int type) {
		this.asc = asc;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int compare(SorteoMath o1, SorteoMath o2) {
		switch (type) {
		case 1:
			return o1.getResultado().compareTo(o2.getResultado())
					* (asc ? 1 : -1);
		case 2:
			return Double.valueOf(o1.getMedia()).compareTo(
					Double.valueOf(o2.getMedia()))
					* (asc ? 1 : -1);
		case 3:
			return Double.valueOf(o1.getDesviacion()).compareTo(
					Double.valueOf(o2.getDesviacion()))
					* (asc ? 1 : -1);
		case 4:
			return Integer.valueOf(o1.getSorteosAtras()).compareTo(
					Integer.valueOf(o2.getSorteosAtras()))
					* (asc ? 1 : -1);
		case 6:
			return Double.valueOf(o1.getProporcion()).compareTo(
					Double.valueOf(o2.getProporcion()))
					* (asc ? 1 : -1);
		default:
			return Double.valueOf(o1.getRate()).compareTo(
					Double.valueOf(o2.getRate()))
					* (asc ? 1 : -1);
		}
	}

}
