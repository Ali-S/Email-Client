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
 * 
 */
public class send {
	
	/**
     *  Constructor to call the included features at the class "Send".
     *  
     * 
     * @param smtp
     * @param email
     * @param password
     * @param subject
     * @param content
     */
	public send(String smtp, final String email, final String password, String to,String cc, String bcc
			,String subject, String content){
		
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
			if(cc != null){
				msg.addRecipients(RecipientType.CC, InternetAddress.parse(cc));
			}
			else if(bcc != null){
				msg.addRecipients(RecipientType.BCC, InternetAddress.parse(bcc));
			}
			msg.setSubject(subject);
			msg.setText(content);
			Transport.send(msg);
			JOptionPane.showMessageDialog(null, "Your E-Mail is send.");
			
		} catch (AddressException e) {
			JOptionPane.showMessageDialog(null, "Please enter valid E-Mail adress.");

		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Your E-Mail was not send.");
		}
	}
}
