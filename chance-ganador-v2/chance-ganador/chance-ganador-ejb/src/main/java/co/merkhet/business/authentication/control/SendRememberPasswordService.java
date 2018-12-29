package co.merkhet.business.authentication.control;

import java.math.BigInteger;
import java.security.SecureRandom;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.credential.Password;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.User;

import co.merkhet.cganador.business.general.boundary.MailService;

@Stateless
public class SendRememberPasswordService {

	@Inject
	private PartitionManager partitionManager;
	@Inject
	private MailService mailService;

	public void sendRememberPassword(String email) throws MessagingException {
		email = email.toLowerCase();
		IdentityManager identityManager = this.partitionManager
				.createIdentityManager();
		User user = BasicModel.getUser(identityManager, email);
		if (user != null) {
			String newPassword = generarPassword();
			identityManager.updateCredential(user, new Password(newPassword));
			mailService.enviarMail("CGanador Recovery",
					"Your new password is: " + newPassword
							+ " please change it.", email, new String[0]);
		}
	}

	private String generarPassword() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
}
