
import java.io.IOException;
import java.util.*;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * The class "GetInbox" 
 */
public class getinbox{
	Message[] nachrichten;
	Message message;
	String imap, smtp, email, password;
	
	/**
	 * Constructor which call the included features.
	 * The parameters are 
	 * 
	 * @param imap
	 * @param email
	 * @param passwort
	 */
	public getinbox(String imap, String email, String passwort){
		try {
			this.imap = imap;
			this.email = email;
			this.password = passwort;
			
			
			JFrame frameload = new JFrame();
			frameload.setSize(300, 100);
			frameload.setTitle("JProgressBar Beispiel");
			JPanel panelload = new JPanel();
			JProgressBar load = new JProgressBar(0, 100);
			load.setValue(0);
			load.setStringPainted(true);
			panelload.add(load);
			frameload.add(panelload);
			frameload.setVisible(true);
			frameload.setLocationRelativeTo(null);

			
			Properties props = System.getProperties();
	        props.setProperty("mail.store.protocol", "imaps");
	        props.put("mail.imaps.ssl.trust", "*");

	        Session session = Session.getDefaultInstance(props, null);
	        
	        Store store = session.getStore("imaps");
	        store.connect(imap, email, passwort);
			
			Folder emailfolder = store.getFolder("inbox");
			emailfolder.open(Folder.READ_WRITE);
			System.out.println("Vorder FORSCLEIFE");
			Message[] mails = emailfolder.getMessages();
			String [] subjects;
			nachrichten = emailfolder.getMessages();
			for (int i = 0; i < mails.length; i++) {
					load.setValue(i);
					nachrichten[i] = mails[i];
			} 
			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the subject and checks if there is any email
	 * 
	 * @param i
	 */
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
	 * Gets the sender and checks if there is any email
	 * 
	 * @param i
	 */
	public String getfrom(int i){
		Message message;
		if (nachrichten[i] == null) {
			System.out.println("Es befinden sich keine Nachrichten"); 
		} 
		message = nachrichten[i];
		try {
			return InternetAddress.toString(message.getFrom());
		} catch (MessagingException e) {
			e.printStackTrace();
			return null; 
		}
	}
	
	/**
	 * Gets the content and checks if there is any email
	 * 
	 * @param i
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
	 * Gets the content type and checks if there is any email
	 * 
	 * @param i
	 */
	public String getcontenttyp(int i){
		Message message;
		message = nachrichten[i];
		try {
			return message.getContentType();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return (String) "Contenttyp cant be return\n";
	}
	
	/**
	 * Gets the date and checks if there is any email
	 * 
	 * @param i
	 */
	public Date getdate(int i){
		Message message = nachrichten[i];
		try {
			return message.getReceivedDate();
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Keine Nachricht enthalten.");
			e.printStackTrace();
		} 
		return null;
	}
}
	


