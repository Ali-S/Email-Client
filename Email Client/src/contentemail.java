import javax.mail.Message;
import javax.mail.MessagingException;


public class contentemail {
	public void getcontentmail(Message message) throws MessagingException{
		System.out.println(message.getContentType());
	}
}
