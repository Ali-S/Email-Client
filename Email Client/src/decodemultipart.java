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
		String q = null;
		Multipart mp = (Multipart) message.getContent();
		int mpcount = mp.getCount();
		for (int i = 0; i < mpcount; i++) {
			Object p = mp.getBodyPart(i).getContent();
			q = p.toString();
		}
		return q;
	}
}
