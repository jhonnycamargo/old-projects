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

@Rule(name = "SelectSuperPlenoRule", description = "selecciona un super pleno si no existe.")
public class SelectSuperPlenoRule {

	private Play play;

	@Inject
	private PonderacionesCache ponderacionesCache;
	@Inject
	private PlaySelectedIndexBean playSelectedIndexBean;

	public SelectSuperPlenoRule() {
		super();
	}

	@Condition
	public boolean isSuperPlenoSelected() {
		if (!StringUtils.isNotBlank(play.getSuperPleno())) {
			play.setIndex(playSelectedIndexBean.getSuperPlenoIndex(play
					.getLoteria()));
		}
		return !StringUtils.isNotBlank(play.getSuperPleno());
	}

	@Action
	public void setSuperPleno() {
		Optional<List<Ponderacion>> s = ponderacionesCache
				.getEntry(new PonderacionCacheKey(play.getLoteria(), 1, 4));
		if (s.isPresent()) {
			List<Ponderacion> stats = s.get();
			play.setSuperPleno(stats.get(play.getIndex()).getNumero());
		} else {
			play.setSuperPleno("-1");
		}
	}

	@Priority
	public int getPriority() {
		return 5;
	}

	public void setPlay(Play play) {
		this.play = play;
	}
}
