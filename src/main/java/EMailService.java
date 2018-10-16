
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@SuppressWarnings("unused")
public class EMailService {
	private static Logger log = Logger.getLogger(EMailService.class.getName());

	private static String SMTP_AUTH_USER = "";
	private static String SMTP_AUTH_PWD = "";

	// private static final Logger slf4jLogger =
	// LoggerFactory.getLogger(EMailService.class);

	private static class SMTPAuthenticator extends javax.mail.Authenticator {
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}

	EMailService() {
	}

	EMailService(String host, String username, String password, String from, String to, String msg, String subject) {
		new Params();
		host = Params.getMailhost();
		String protocol = Params.getMailProtocol();
		String port = Params.getMailPort();

		System.out.println(host);
		System.out.println(protocol);
		System.out.println(port);

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.transport.protocol", protocol);
		properties.setProperty("mail.smtp.socketFactory.port", port);
		properties.put("mail.smtp.debug", "true");
		if (protocol.equals("smtps")) {
			properties.put("mail.smtp.starttls.enable", "true");
		}

		Authenticator auth = new SMTPAuthenticator();
		SMTP_AUTH_USER = username;
		SMTP_AUTH_PWD = password;
		Session session = Session.getDefaultInstance(properties, auth);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(msg);

			// Send message
			Transport.send(message);

		} catch (MessagingException mex) {
			// mex.printStackTrace();
			log.log(Level.SEVERE, "Exception: ", mex);
		}

	}

}