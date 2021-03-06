
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;

/**
 * The class "GetSendbox" loads and stores the sended mails from the server 
 * 
 */
public class GetSendbox {
	Message[] nachrichten;
	
	/**
	 * The class "GetSendbox"
	 * 
	 * @param imap 
	 * @param email 
	 * @param password 
	 */
	public GetSendbox(String imap, String email, String password){

		Properties props = new Properties();

	    props.setProperty("mail.store.protocol", "imaps");

	    try {
	        Session session = Session.getInstance(props, null);
	        Store store = session.getStore();
	        store.connect(imap, email, password);

	        Folder inbox = store.getFolder("[Gmail]/Gesendet");
	        inbox.open(Folder.READ_ONLY);
	        
	        nachrichten = inbox.getMessages();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public String getsubject(int i){
		Message message;
		if (nachrichten[i] == null) {
			System.out.println("Es befinden sich keine Nachrichten"); 
		} 
		message = nachrichten[i];
		try {
			return message.getSubject();
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Fehlgeschlagen";
		}
	}
	
	/**
	 * Gets the receiver and checks if there is any email
	 * 
	 * @param i for selecting sender from selected mail
	 */
	public String getreceive(int i){
		Message message;
		if (nachrichten[i] == null) {
			System.out.println("Es befinden sich keine Nachrichten"); 
		} 
		message = nachrichten[i];
		try {
			return InternetAddress.toString(message.getRecipients(RecipientType.TO));
		} catch (MessagingException e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	/**
	 * Gets the content and checks if there is any email
	 * 
	 * @param i for selecting content from selected mail
	 */
	public String getcontent(int i) throws IOException, MessagingException {
		Message message;
		if(nachrichten[i] == null) {
			System.out.println("Es befinden sich keine Nachrichten\n");
		}
		message = nachrichten[i];
		return message.getContent().toString();
	}
	
	/**
	 * Gets the date and checks if there is any email
	 * 
	 * @param i for selecting send date from selected mail
	 */
	public Date getdate(int i){
		Message message = nachrichten[i];
		try {
			return message.getSentDate();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Keine Nachricht enthalten.");
			e.printStackTrace();
		} 
		return null;
	}
}
