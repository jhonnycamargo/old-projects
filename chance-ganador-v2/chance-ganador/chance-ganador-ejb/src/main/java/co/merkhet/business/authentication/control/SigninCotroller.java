package co.merkhet.business.authentication.control;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import org.picketlink.idm.IdentityManagementException;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.User;

import co.merkhet.business.authentication.exceptions.AuthenticacionException;

@Stateless
@Named
public class SigninCotroller implements Serializable {

	private static final long serialVersionUID = -4629949986702742592L;
	@Inject
	private PartitionManager partitionManager;

	public void signin(String username, String password)
			throws AuthenticacionException {
		try {
			username = username.toLowerCase();
			User superadmin = new User(username);
			superadmin.setEmail(username);
			superadmin.setFirstName(username);
			superadmin.setLastName(username);

			IdentityManager identityManager = this.partitionManager
					.createIdentityManager();

			identityManager.add(superadmin);
			identityManager
					.updateCredential(superadmin, new Password(password));

		} catch (IdentityManagementException ix) {
			throw new AuthenticacionException("Usuario ya registrado.", ix);
		}
	}
}
