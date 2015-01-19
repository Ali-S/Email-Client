import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;

import org.omg.CosNaming.IstringHelper;


public class decodemultipart {
	String mail;
	public decodemultipart(Message message) throws IOException, MessagingException{
		if (message instanceof MimeMessage) {
			MimeMessage m = (MimeMessage)message;
			Object contentobject = m.getContent();
			if(contentobject instanceof Multipart){
				BodyPart cleartextpart = null;
				BodyPart htmltextpart = null;
				Multipart content = (Multipart)contentobject;
				int count = content.getCount();
				for (int i = 0; i < count; i++) {
					BodyPart part = content.getBodyPart(i);
					if (part.isMimeType("text/plain")) {
						cleartextpart = part;
						break;
					}
					else if(part.isMimeType("text/html")){
						htmltextpart = part;
					}
				}
				if(cleartextpart!=null){
					return;
				}
				else if(htmltextpart!=null){
					String html = (String) htmltextpart.getContent();
					//return--> Starte hier html decoder
				}
				
			}
			else if(contentobject instanceof String){
				return;
			} else System.out.println("Dies ist kein MimeMessage\n");
		}
		return;
		
	}
}
