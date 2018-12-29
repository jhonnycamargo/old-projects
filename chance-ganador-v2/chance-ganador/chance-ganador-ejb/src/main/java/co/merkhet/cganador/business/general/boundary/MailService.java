package co.merkhet.cganador.business.general.boundary;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class MailService {

	@Resource(mappedName = "java:jboss/mail/Gmail")
	private Session mailSession;

	public void enviarMail(String subject, String body, String to, String... cc)
			throws MessagingException {

		Message message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("apuesta.a.ganar@gmail.com"));
		if (to != null) {
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
		}
		if (cc != null) {
			for (String c : cc) {
				if (c != null) {
					message.setRecipients(Message.RecipientType.CC,
							InternetAddress.parse(c));
				}
			}
		}
		message.setSubject(subject);
		message.setText(body);
		Transport.send(message);
	}
}
