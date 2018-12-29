package co.merkhet.cganador.business.letsplay.boundary;

import java.text.ParseException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.easyrules.api.RulesEngine;

import co.merkhet.cganador.business.chance.entity.Loteria;
import co.merkhet.cganador.business.general.boundary.DateService;
import co.merkhet.cganador.business.general.exception.CGanadorException;
import co.merkhet.cganador.business.letsplay.entity.Play;
import co.merkhet.cganador.business.letsplay.rules.CurrentPlay;
import co.merkhet.cganador.business.letsplay.rules.NewPlayRule;
import co.merkhet.cganador.business.letsplay.rules.SelectPataRule;
import co.merkhet.cganador.business.letsplay.rules.SelectPlenoRule;
import co.merkhet.cganador.business.letsplay.rules.SelectSuperPlenoRule;
import co.merkhet.cganador.business.letsplay.rules.SelectUniaRule;
import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

@Stateless
@Named
public class LetsPlayBean {

	@Inject
	private CurrentPlay currentPlay;
	@Inject
	private SelectUniaRule selectUniaRule;
	@Inject
	private SelectPataRule selectPataRule;
	@Inject
	private SelectPlenoRule selectPlenoRule;
	@Inject
	private SelectSuperPlenoRule selectSuperPlenoRule;
	@Inject
	private NewPlayRule newPlayRule;
	@Inject
	private DateService dateService;

	public Play jugar(Loteria loteria, String jugador) throws CGanadorException {
		Play play = new Play();
		play.setLoteria(loteria);
		play.setJugador(jugador);
		try {
			play.setFecha(dateService.currentDateByCountry(loteria.getPais()));
		} catch (ParseException e) {
			throw new CGanadorException("Error parseando fecha ingresada.", e);
		}

		RulesEngine rulesEngine = aNewRulesEngine().build();
		currentPlay.setPlay(play);
		rulesEngine.registerRule(currentPlay);
		selectUniaRule.setPlay(play);
		rulesEngine.registerRule(selectUniaRule);
		selectPataRule.setPlay(play);
		rulesEngine.registerRule(selectPataRule);
		selectPlenoRule.setPlay(play);
		rulesEngine.registerRule(selectPlenoRule);
		selectSuperPlenoRule.setPlay(play);
		rulesEngine.registerRule(selectSuperPlenoRule);
		newPlayRule.setPlay(play);
		rulesEngine.registerRule(newPlayRule);
		rulesEngine.fireRules();
		rulesEngine.clearRules();

		return play;
	}
}
