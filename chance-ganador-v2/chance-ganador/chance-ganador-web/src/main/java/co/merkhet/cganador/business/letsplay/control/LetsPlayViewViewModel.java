package co.merkhet.cganador.business.letsplay.control;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import co.merkhet.business.authentication.control.LoginController;
import co.merkhet.cganador.business.chance.boundary.LoteriasBean;
import co.merkhet.cganador.business.chance.entity.Loteria;
import co.merkhet.cganador.business.general.exception.CGanadorException;
import co.merkhet.cganador.business.letsplay.boundary.LetsPlayBean;
import co.merkhet.cganador.business.letsplay.entity.Play;

public class LetsPlayViewViewModel {

	@WireVariable
	private LoteriasBean loteriasBean;
	@WireVariable
	private LetsPlayBean letsPlayBean;
	@WireVariable
	private LoginController loginController;

	private Play play;

	private Loteria loteria;

	public Play getPlay() {
		return play;
	}

	public List<Loteria> getLoterias() {
		return loteriasBean.selectAllLoteries();
	}

	public Loteria getLoteria() {
		return loteria;
	}

	public void setLoteria(Loteria loteria) {
		this.loteria = loteria;
	}

	@Command
	@NotifyChange({ "play" })
	public void jugar() {
		play = new Play();
		if (loteria != null) {
			try {
				play = letsPlayBean.jugar(loteria,
						(loginController.getUserAccount()).getLoginName());
			} catch (CGanadorException e) {
				Clients.showNotification(e.getMessage(), "error", null,
						"middle_center", 5000);
			}
		}
	}

}
