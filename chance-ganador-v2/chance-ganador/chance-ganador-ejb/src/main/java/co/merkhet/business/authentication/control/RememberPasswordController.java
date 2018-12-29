package co.merkhet.business.authentication.control;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;

@Stateless
@Named
public class RememberPasswordController implements Serializable {

	private static final long serialVersionUID = 127230111461096552L;

	@Inject
	private SendRememberPasswordService sendRememberPasswordService;

	public void rememberPassword(String email) throws MessagingException {
		sendRememberPasswordService.sendRememberPassword(email);
	}
}
