package co.merkhet.cganador.business.letsplay.rules;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;

import co.merkhet.cganador.business.letsplay.entity.Play;
import co.merkhet.cganador.business.stats.cache.PonderacionCacheKey;
import co.merkhet.cganador.business.stats.cache.PonderacionesCache;
import co.merkhet.cganador.business.stats.entity.Ponderacion;

import com.google.common.base.Optional;

@Rule(name = "selectPataRule", description = "selecciona una pata si no existe.")
public class SelectPataRule {

	private Play play;

	@Inject
	private PonderacionesCache ponderacionesCache;
	@Inject
	private PlaySelectedIndexBean playSelectedIndexBean;

	public SelectPataRule() {
		super();
	}

	@Condition
	public boolean isPataSelected() {
		if (!StringUtils.isNotBlank(play.getPata())) {
			play.setIndex(playSelectedIndexBean.getPataIndex(play.getLoteria()));
		}
		return !StringUtils.isNotBlank(play.getPata());
	}

	@Action
	public void setPata() {
		Optional<List<Ponderacion>> s = ponderacionesCache
				.getEntry(new PonderacionCacheKey(play.getLoteria(), 3, 4));
		if (s.isPresent()) {
			List<Ponderacion> stats = s.get();
			play.setPata(stats.get(play.getIndex()).getNumero());
		} else {
			play.setPata("-1");
		}
	}

	@Priority
	public int getPriority() {
		return 3;
	}

	public void setPlay(Play play) {
		this.play = play;
	}
}
