package co.swat.tapfamily.business.authentication.control;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class LoginViewController extends SelectorComposer<Window> {

	private static final long serialVersionUID = 2168461564618141701L;

	@Wire
	private Textbox usernameTxt, passwordTxt;

	@WireVariable
	private LoginController loginController;

	@Listen("onClick=#loginBtn")
	public void doLogin() {
		// TODO logear si es necesario.
		System.out.println("logeando...");
		loginController.login(usernameTxt.getValue(), passwordTxt.getValue());
	}

}
