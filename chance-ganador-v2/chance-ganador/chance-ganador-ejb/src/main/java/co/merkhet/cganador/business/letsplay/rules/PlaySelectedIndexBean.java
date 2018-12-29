package co.merkhet.cganador.business.letsplay.rules;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;

import co.merkhet.cganador.business.chance.entity.Loteria;

@Singleton
public class PlaySelectedIndexBean {

	private Map<Loteria, Integer> uniaIndex = new HashMap<Loteria, Integer>();
	private Map<Loteria, Integer> pataIndex = new HashMap<Loteria, Integer>();
	private Map<Loteria, Integer> plenoIndex = new HashMap<Loteria, Integer>();
	private Map<Loteria, Integer> superPlenoIndex = new HashMap<Loteria, Integer>();

	public int getUniaIndex(Loteria loteria) {
		Integer indx = uniaIndex.get(loteria);
		if (indx == null) {
			indx = -1;
		}
		if (indx > 4) {
			indx = 0;
		} else {
			indx++;
		}
		uniaIndex.put(loteria, indx);
		return indx;
	}

	public int getPataIndex(Loteria loteria) {
		Integer indx = pataIndex.get(loteria);
		if (indx == null) {
			indx = -1;
		}
		if (indx > 40) {
			indx = 0;
		} else {
			indx++;
		}
		pataIndex.put(loteria, indx);
		return indx;
	}

	public int getPlenoIndex(Loteria loteria) {
		Integer indx = plenoIndex.get(loteria);
		if (indx == null) {
			indx = -1;
		}
		if (indx > 400) {
			indx = 0;
		} else {
			indx++;
		}
		plenoIndex.put(loteria, indx);
		return indx;
	}

	public int getSuperPlenoIndex(Loteria loteria) {
		Integer indx = superPlenoIndex.get(loteria);
		if (indx == null) {
			indx = -1;
		}
		if (indx > 4000) {
			indx = 0;
		} else {
			indx++;
		}
		superPlenoIndex.put(loteria, indx);
		return indx;
	}

}
