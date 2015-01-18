
import java.awt.List;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.util.*;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import com.sun.mail.imap.*;
public class getinbox{
	Message[] nachrichten = null;
	
	public getinbox(String imap, String email, String passwort){
		
		try {
			Properties props = System.getProperties();
	        props.setProperty("mail.store.protocol", "imaps");

	        Session session = Session.getDefaultInstance(props, null);

				
	        Store store = session.getStore("imaps");
	        store.connect(imap,email, passwort);
			
			Folder emailfolder = store.getFolder("inbox");
			emailfolder.open(Folder.READ_ONLY);
			System.out.println("Vorder FORSCLEIFE");
			Message[] mails = emailfolder.getMessages();
			String [] subjects;
			for (int i = 0; i < mails.length; i++) {
				Message message = mails[i];
					System.out.println("---------------------------------");  
				    System.out.println("Email Number " + (i + 1));  
				    System.out.println("Subject: " + message.getSubject());  
				    System.out.println("From: " + message.getFrom()[0]);  
				    System.out.println("Text: " + message.getContent().toString()); 
			}
			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
//	public String getsubject(int i){
//		Message message;
//		if (nachrichten[i] == null) {
//			System.out.println("Es befinden sich keine Nachrichten"); 
//		} 
//		message = nachrichten[i];
//		try {
//			return message.getSubject();
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			return "Fehlgeschlagen";
//		}
//	}
//	
//	public String getfrom(int i){
//		Message message;
//		message = nachrichten[i];
//		try {
//			return InternetAddress.toString(message.getFrom());
//		} catch (MessagingException e) {
//			e.printStackTrace();
//			return null; 
//		}
//	}
}
	


