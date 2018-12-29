package co.merkhet.business.authentication.control;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.Identity;
import org.picketlink.Identity.AuthenticationResult;
import org.picketlink.credential.DefaultLoginCredentials;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.User;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = -8337098452940512712L;

	@Inject
	private Identity identity;
	@Inject
	private DefaultLoginCredentials credentials;
	@Inject
	private PartitionManager partitionManager;

	public void login(String username, String password) {
		username = username.toLowerCase();
		credentials.setUserId(username);
		credentials.setPassword(password);
		AuthenticationResult result = identity.login();
		if (AuthenticationResult.FAILED.equals(result)) {
			// TODO
		}
	}

	public boolean isAuthenticated() {
		return identity.isLoggedIn();
	}

	public User getUserAccount() {
		return (User) identity.getAccount();
	}

	public void updatePassword(String newPassword) {
		User user = getUserAccount();
		IdentityManager identityManager = this.partitionManager
				.createIdentityManager();
		identityManager.updateCredential(user, new Password(newPassword));
	}
}
