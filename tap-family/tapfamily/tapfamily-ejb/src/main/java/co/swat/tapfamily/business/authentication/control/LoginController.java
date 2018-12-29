package co.swat.tapfamily.business.authentication.control;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;
import org.picketlink.credential.DefaultLoginCredentials;
import java.io.Serializable;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -7608405194226687754L;

	@Inject
	private Identity identity;
	@Inject
	private DefaultLoginCredentials credentials;

	public void login(String username, String password) {
		credentials.setUserId(username);
		credentials.setPassword(password);
		AuthenticationResult result = identity.login();
		if (AuthenticationResult.FAILED.equals(result)) {
			// TODO logear la autenticacion fallida.
			System.out.println("Log Failed");
		}
	}
}
