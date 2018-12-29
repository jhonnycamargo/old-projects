package co.merkhet.web.util;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zkplus.cdi.DelegatingVariableResolver;

import co.merkhet.business.authentication.control.LoginController;

public class AuthenticationInit implements Initiator {

	private LoginController loginController;

	@Override
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		if (loginController == null) {
			loginController = (LoginController) new DelegatingVariableResolver()
					.resolveVariable("loginController");
		}
		if (!loginController.isAuthenticated()) {
			Executions.getCurrent().sendRedirect("/login.zul");
		}
	}

}
