
import java.awt.List;
import java.awt.event.TextEvent;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

import com.sun.mail.handlers.text_html;
import com.sun.mail.handlers.text_plain;
import com.sun.mail.imap.*;
import com.sun.mail.util.DecodingException;

import javax.mail.internet.MimeUtility;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument.HTMLReader;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.omg.CORBA.Any;
import org.omg.CosNaming.NamingContextExtPackage.URLStringHelper;
import org.w3c.dom.Text;
public class getinbox{
	Message[] nachrichten;
	contentemail content;
	
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
			nachrichten = emailfolder.getMessages();

			
			for (int i = 0; i < mails.length; i++) {
				Message message = mails[i];
					System.out.println("---------------------------------");  
				    System.out.println("Email Number " + (i + 1));  
				    System.out.println("Subject: " + message.getSubject());  
				    System.out.println("From: " + message.getFrom()[0]);  
				    if (message.isMimeType("multipart/ALTERNATIVE")) {
				    	Multipart mp = (Multipart)message.getContent();
		                int partsCount = mp.getCount();
		                String content = ((String)mp.getBodyPart(partsCount -1).getContent());
		                content.replaceAll("%", "%25");
		                String content2 = MimeUtility.decodeText(content);
		                Whitelist white = new Whitelist();
					System.out.println(Jsoup.clean(content2, white));
				    } else {

					    System.out.println("Text: " + message.getContent().toString()); 
					    
					}System.out.println("Contenttyp: " + message.getContentType());
				    nachrichten[i] = mails[i];
			}
			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	
	public String getcontent(int i) {
		Message message;
		if(nachrichten[i] == null) {
			System.out.println("Es befinden sich keine Nachrichten\n");
		}
		message = nachrichten[i];
		try {
			return message.getContent().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
	


