package co.merkhet.cganador.business.letsplay.rules;

import javax.inject.Inject;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;

import co.merkhet.business.data.boundary.CrudService;
import co.merkhet.cganador.business.letsplay.entity.Play;

@Rule(name = "newPlayRule", description = "Si es un nuevo juego se guarda en la bd.")
public class NewPlayRule {

	private Play play;

	@Inject
	private CrudService crudService;

	public NewPlayRule() {
		super();
	}

	@Condition
	public boolean isNewPlay() {
		return !(play.getId() != null);
	}

	@Action
	public void saveNewPlay() {
		crudService.create(play);
	}

	@Priority
	public int getPriority() {
		return 3;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

}
