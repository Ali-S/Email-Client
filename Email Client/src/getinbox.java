
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
import javax.swing.JOptionPane;


public class getinbox{
	Message[] nachrichten;
	Message message;
	contentemail content;
	String imap,smtp,email,password;
	
	public getinbox(String imap, String email, String passwort){
		try {
			this.imap = imap;
			this.email = email;
			this.password = passwort;
			
			Properties props = System.getProperties();
	        props.setProperty("mail.store.protocol", "imaps");
	        props.put("mail.imaps.ssl.trust", "*");

	        Session session = Session.getDefaultInstance(props, null);

				
	        Store store = session.getStore("imaps");
	        store.connect(imap,email, passwort);
			
			Folder emailfolder = store.getFolder("inbox");
			emailfolder.open(Folder.READ_WRITE);
			System.out.println("Vorder FORSCLEIFE");
			Message[] mails = emailfolder.getMessages();
			String [] subjects;
			nachrichten = emailfolder.getMessages();
			for (int i = 0; i < mails.length; i++) {
				message = mails[i];
					System.out.println("---------------------------------");  
				    System.out.println("Email Number " + (i + 1));  
				    System.out.println("Subject: " + message.getSubject());  
				    System.out.println("From: " + message.getFrom()[0]); 
					System.out.println("Contenttyp: " + message.getContentType());
					nachrichten[i] = mails[i];
			} 

			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
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
	
	public String getcontent(int i) throws IOException, MessagingException {
		Message message;
		if(nachrichten[i] == null) {
			System.out.println("Es befinden sich keine Nachrichten\n");
		}
		message = nachrichten[i];
		return message.getContent().toString();
	}
	
	public String getcontenttyp(int i){
		Message message;
		message = nachrichten[i];
		try {
			return message.getContentType();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String) "Contenttyp cant be return\n";
	}
	
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
	


