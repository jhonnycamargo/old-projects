package co.merkhet.business.authentication.control;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;

@Stateless
@Named
public class LogoutController implements Serializable {

	private static final long serialVersionUID = 6639316928921514843L;

	@Inject
	private Identity identity;

	public void logout() {
		identity.logout();
	}

}
