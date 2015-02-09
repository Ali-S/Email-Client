import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class send {
	public send(String smtp, final String email, final String password, String to,String subject, String content){
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", smtp);
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties,new Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	             return new PasswordAuthentication(email, password);
	          }
	       });

		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(email));
			msg.setRecipients(RecipientType.TO,InternetAddress.parse(to));
			msg.setSubject(subject);
			msg.setText(content);
			Transport.send(msg);
			JOptionPane.showMessageDialog(null, "Your E-Mail is send.");
			
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, "Please enter valid E-Mail adress.");
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
