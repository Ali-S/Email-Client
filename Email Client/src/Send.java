import java.util.Properties;

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

/**
 * The class "Send" ensures that email will send or not
 */
public class Send {
	
	/**
     * Constructor to call the included features at the class "Send".
     *  
     * @param smtp outgoing server
     * @param email as username
     * @param password from mail account
     * @param subject 
     * @param content message
     */
	public Send(String smtp, final String email, final String password, String to, String cc, String bcc,
			String subject, String content){
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", smtp);
		properties.put("mail.smtp.port", "587");
		
		/**
	     * Verifying password
	     */
		Session session = Session.getInstance(properties, new Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	             return new PasswordAuthentication(email, password);
	         }
	       });
		
		/**
	     * Message configurations and sending
	     */
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(email));
			msg.setRecipients(RecipientType.TO,InternetAddress.parse(to));
			msg.setSubject(subject);
			msg.setText(content);
			if (cc == null && bcc == null) {
				Transport.send(msg);
			} else if(cc != null) {
				msg.addRecipients(RecipientType.CC, InternetAddress.parse(bcc));
			} else if (bcc != null) {
				msg.addRecipients(RecipientType.BCC, InternetAddress.parse(bcc)); 
				Transport.send(msg);
			} else 
			Transport.send(msg);
			
			JOptionPane.showMessageDialog(null, "Your E-Mail is send.");
			
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, "Please enter valid E-Mail adress.");

		} catch (MessagingException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Your E-Mail was not send.");
		}
	}
}
