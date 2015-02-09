import java.net.Authenticator;
import java.net.PasswordAuthentication;


public class MailAuthenticator extends Authenticator{
	private final String email;
	private final String password;
	
	public MailAuthenticator(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(email, password);
	}
}
