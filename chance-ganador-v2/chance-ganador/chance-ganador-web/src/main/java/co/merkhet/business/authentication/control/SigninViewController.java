package co.merkhet.business.authentication.control;

import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.merkhet.business.authentication.exceptions.AuthenticacionException;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class SigninViewController extends SelectorComposer<Window> {

	private static final long serialVersionUID = -1426116202698718015L;

	@Wire
	private Textbox usernameTxt, passwordTxt, confirmPasswordTxt;
	@Wire
	private Label messageLbl;

	@WireVariable
	private SigninCotroller signinCotroller;
	@WireVariable
	private LoginController loginController;

	@NotifyChange({ "redirectToLogin" })
	@Listen("onClick=#registrarBtn")
	public void doSignIn() {
		if (passwordTxt.getValue().equals(confirmPasswordTxt.getValue())) {
			try {
				signinCotroller.signin(usernameTxt.getValue(),
						passwordTxt.getValue());
				loginController.login(usernameTxt.getValue(),
						passwordTxt.getValue());
				Executions.getCurrent().sendRedirect("/index.zul");
			} catch (AuthenticacionException e) {
				messageLbl.setValue(e.getMessage());
			}
		} else {
			messageLbl.setValue("Las contraseñas no coinciden.");
		}
	}

	@Listen("onClick=#toLoginBtn")
	public void doToLogin() {
		Executions.getCurrent().sendRedirect("/login.zul");
	}

	@Listen("onClick=#recoverPwdBtn")
	public void doRecoverPassword() {
		Executions.getCurrent().sendRedirect("/recordar_password.zul");
	}
}
