package co.merkhet.business.authentication.control;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.mail.MessagingException;

/*@MessageDriven(name = "CGanadorQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/jms/queue/CGanadorQueue"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })*/
public class SendRememberPasswordMDB /*implements MessageListener*/ {
/*
	@Inject
	private SendRememberPasswordService sendRememberPasswordService;

	@Override
	public void onMessage(Message message) {
		try {
			String email = message.getBody(String.class);
			sendRememberPasswordService.sendRememberPassword(email);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
		}
	}
*/
}
