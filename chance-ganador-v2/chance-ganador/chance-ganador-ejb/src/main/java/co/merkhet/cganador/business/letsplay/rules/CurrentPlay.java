package co.merkhet.cganador.business.letsplay.rules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;

import co.merkhet.business.data.boundary.CrudService;
import co.merkhet.cganador.business.letsplay.entity.Play;

@Rule(name = "currentPlay", description = "Verifica que ya tenga un juego disponible.")
public class CurrentPlay {

	private Play play;

	@Inject
	private CrudService crudService;

	public CurrentPlay() {
	}

	@SuppressWarnings("rawtypes")
	@Condition
	public boolean isPlayed() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loteria", play.getLoteria());
		params.put("fecha", play.getFecha());
		params.put("jugador", play.getJugador());
		List plays = crudService.findWithNamedQuery(
				Play.COUNT_BY_LOTERIA_FECHA_JUGADOR, params);
		boolean result = CollectionUtils.isEmpty(plays);
		if (!result) {
			play.setId((Long) plays.get(0));
		}
		return !result;
	}

	@Action
	public void setPlayed() {
		Play p = crudService.find(Play.class, this.play.getId());
		this.play.setPata(p.getPata());
		this.play.setPleno(p.getPleno());
		this.play.setSuperPleno(p.getSuperPleno());
		this.play.setUnia(p.getUnia());
	}

	@Priority
	public int getPriority() {
		return 1;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

}
