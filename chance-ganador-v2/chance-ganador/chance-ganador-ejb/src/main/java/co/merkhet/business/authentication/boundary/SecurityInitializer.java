package co.merkhet.business.authentication.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.User;

@Singleton
@Startup
public class SecurityInitializer {

	@Inject
	private PartitionManager partitionManager;

	@PostConstruct
	public void create() {
		IdentityManager identityManager = this.partitionManager
				.createIdentityManager();

		if (BasicModel.getUser(identityManager, "superadmin") == null) {
			User superadmin = new User("superadmin");
			superadmin.setEmail("jhonnycamargo@gmail.com");
			superadmin.setFirstName("Jhonny");
			superadmin.setLastName("Camargo");
			identityManager.add(superadmin);
			identityManager.updateCredential(superadmin, new Password(
					"superadmin"));
		}
	}
}
