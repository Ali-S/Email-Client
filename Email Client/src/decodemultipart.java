import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import javax.naming.spi.DirStateFactory.Result;

import org.jsoup.Jsoup;
import org.jsoup.safety.Cleaner;
import org.omg.CosNaming.IstringHelper;


public class decodemultipart {
	String mail;
	String result;
	public String decodemultipart(Message message) throws IOException, MessagingException{
		  if(message instanceof MimeMessage)
	        {
	            MimeMessage m = (MimeMessage)message;
	            Object contentObject = m.getContent();
	            if(contentObject instanceof Multipart)
	            {
	                BodyPart clearTextPart = null;
	                BodyPart htmlTextPart = null;
	                Multipart content = (Multipart)contentObject;
	                int count = content.getCount();
	                for(int i=0; i<count; i++)
	                {
	                    BodyPart part =  content.getBodyPart(i);
	                    if(part.isMimeType("text/plain"))
	                    {
	                        clearTextPart = part;
	                        break;
	                    }
	                    else if(part.isMimeType("text/html"))
	                    {
	                        htmlTextPart = part;
	                    }
	                }

	                if(clearTextPart!=null)
	                {
	                    result = (String) clearTextPart.getContent();
	                }
	                else if (htmlTextPart!=null)
	                {
	                    String html = (String) htmlTextPart.getContent();
	                    result = Jsoup.parse(html).text();
	                }

	            }
	             else if (contentObject instanceof String) // a simple text message
	            {
	                result = (String) contentObject;
	            }
	            else // not a mime message
	            {
	                //logger.log(Level.WARNING,"notme part or multipart {0}",message.toString());
	                result = null;
	            }
	        }
		return result;
	}
}
