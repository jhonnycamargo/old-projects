package co.merkhet.business.authentication.control;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class LoginViewController extends SelectorComposer<Window> {

	private static final long serialVersionUID = -4230807435556936536L;

	@Wire
	private Textbox usernameTxt, passwordTxt;
	@Wire
	private Label messageLbl;

	@WireVariable
	private LoginController loginController;

	@Listen("onClick=#loginBtn; onOk=#passwordTxt")
	public void doLogin() {
		loginController.login(usernameTxt.getValue(), passwordTxt.getValue());
		if (loginController.isAuthenticated()) {
			Executions.getCurrent().sendRedirect("/index.zul");
		} else {
			messageLbl.setValue("Not logged in");
		}
	}

	@Listen("onClick=#signInBtn")
	public void doSignIn() {
		Executions.getCurrent().sendRedirect("/signin.zul");
	}

	@Listen("onClick=#recoverPwdBtn")
	public void doRecoverPassword() {
		Executions.getCurrent().sendRedirect("/recordar_password.zul");
	}
}
