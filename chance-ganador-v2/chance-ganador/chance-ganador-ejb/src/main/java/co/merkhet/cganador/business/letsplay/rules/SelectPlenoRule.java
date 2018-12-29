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

@Rule(name = "selectPlenoRule", description = "selecciona un pleno si no existe.")
public class SelectPlenoRule {

	private Play play;

	@Inject
	private PonderacionesCache ponderacionesCache;
	@Inject
	private PlaySelectedIndexBean playSelectedIndexBean;

	public SelectPlenoRule() {
		super();
	}

	@Condition
	public boolean isPlenoSelected() {
		if (!StringUtils.isNotBlank(play.getPleno())) {
			play.setIndex(playSelectedIndexBean.getPlenoIndex(play.getLoteria()));
		}
		return !StringUtils.isNotBlank(play.getPleno());
	}

	@Action
	public void setPleno() {
		Optional<List<Ponderacion>> s = ponderacionesCache
				.getEntry(new PonderacionCacheKey(play.getLoteria(), 2, 4));
		if (s.isPresent()) {
			List<Ponderacion> stats = s.get();
			play.setPleno(stats.get(play.getIndex()).getNumero());
		} else {
			play.setPleno("-1");
		}
	}

	@Priority
	public int getPriority() {
		return 4;
	}

	public void setPlay(Play play) {
		this.play = play;
	}
}
