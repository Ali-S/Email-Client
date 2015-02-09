import javax.mail.Authenticator;

public class send {
	public void send(String imap, String email, String password, String subject, String content){
		MailAuthenticator auth = new MailAuthenticator(email,password);
	}
}
