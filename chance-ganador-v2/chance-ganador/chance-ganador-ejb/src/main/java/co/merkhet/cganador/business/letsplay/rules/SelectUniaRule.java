package co.merkhet.cganador.business.letsplay.rules;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;

import com.google.common.base.Optional;

import co.merkhet.cganador.business.letsplay.entity.Play;
import co.merkhet.cganador.business.stats.cache.PonderacionCacheKey;
import co.merkhet.cganador.business.stats.cache.PonderacionesCache;
import co.merkhet.cganador.business.stats.entity.Ponderacion;

@Rule(name = "selectUniaRule", description = "selecciona una unia si no existe.")
public class SelectUniaRule {

	private Play play;

	@Inject
	private PonderacionesCache ponderacionesCache;
	@Inject
	private PlaySelectedIndexBean playSelectedIndexBean;

	public SelectUniaRule() {
		super();
	}

	@Condition
	public boolean isUniaSelected() {
		if (!StringUtils.isNotBlank(play.getUnia())) {
			play.setIndex(playSelectedIndexBean.getUniaIndex(play.getLoteria()));
		}
		return !StringUtils.isNotBlank(play.getUnia());
	}

	@Action
	public void setUnia() {
		Optional<List<Ponderacion>> s = ponderacionesCache
				.getEntry(new PonderacionCacheKey(play.getLoteria(), 4, 4));
		if (s.isPresent()) {
			List<Ponderacion> stats = s.get();
			play.setUnia(stats.get(play.getIndex()).getNumero());
		} else {
			play.setUnia("-1");
		}
	}

	@Priority
	public int getPriority() {
		return 2;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

}
