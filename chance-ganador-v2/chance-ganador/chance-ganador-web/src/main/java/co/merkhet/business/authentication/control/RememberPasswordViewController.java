package co.merkhet.business.authentication.control;

import javax.inject.Inject;
import javax.mail.MessagingException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.merkhet.business.logging.boundary.MerkhetLogger;

@VariableResolver(org.zkoss.zkplus.cdi.DelegatingVariableResolver.class)
public class RememberPasswordViewController extends SelectorComposer<Window> {

	private static final long serialVersionUID = 7618206056724287952L;

	@Inject
	private MerkhetLogger logger;

	@Wire
	private Textbox emailTxt;
	@Wire
	private Label messageLbl;

	@WireVariable
	private RememberPasswordController rememberPasswordController;

	@Listen("onClick=#rememberPasswordBtn")
	public void doRememberPassword() {
		/*
		 * try { rememberPasswordController.rememberPassword(emailTxt.getValue()
		 * .toLowerCase()); Executions.getCurrent().sendRedirect("/login.zul");
		 * } catch (WrongValueException | MessagingException e) {
		 * logger.errorThrowable(e); }
		 */
		Clients.showNotification("Not working yet");
	}

	@Listen("onClick=#toLoginBtn")
	public void doToLogin() {
		Executions.getCurrent().sendRedirect("/login.zul");
	}

	@Listen("onClick=#signInBtn")
	public void doSignIn() {
		Executions.getCurrent().sendRedirect("/signin.zul");
	}
}
