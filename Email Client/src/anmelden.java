
import java.awt.Checkbox;

import javax.swing.*;

/**
 * The class Anmelden reads and saves the configurations properties in a text file
 * 
 * @author Furkan Yuecel
 * @author Suepriz Yaykan
 * @author Ali Selvi
 * @version 1.0
 */
public class anmelden {

	private String ssmtp;
	private String simap;
	private String semail;
	private String spass;
	
	private final JTextField smtp = new JTextField("Please enter SMTP server", 25);
	private final JTextField imap = new JTextField("Please enter IMAP server", 25);
	private final JTextField email = new JTextField("Please enter E-Mail Adress",25);
	private final JPasswordField pass = new JPasswordField("",15);
	
	/**
	 * Standard constructor that calls the included features.
	 * The parameter "title" only sets the name of the frame.
	 * 
	 * @param title
	 */
	public anmelden(String title){
		
		// Remember me 
		Checkbox remember = new Checkbox("Remember me!");
		
		// Objecst they are listening on the popup frame
		Object[] props = {title, "IMAP", imap, "SMTP", smtp, "E-Mail:", email, "Password:", pass, remember};
		
		// create the popupPane
		JOptionPane fenster = new JOptionPane(props, JOptionPane.PLAIN_MESSAGE,	JOptionPane.DEFAULT_OPTION);
		fenster.createDialog(null,"Properties").setVisible(true);

		setSsmtp(smtp.getText());
		setSimap(imap.getText());
		setSemail(email.getText());
		setSpass(pass.getPassword());
		
		// checked for remember properties from email server 
		if (remember.getState()) {
			System.out.println(getSsmtp() + getSimap() + getSemail() + getSpass());
			new save(getSsmtp(), getSimap(), getSemail(), getSpass());
			new HauptGUI(title);
		}
		else {
			new HauptGUI(title);
		}
	}
	
	public String getSsmtp() {
		return ssmtp;
	}

	public void setSsmtp(String ssmtp) {
		this.ssmtp = ssmtp;
	}

	public String getSimap() {
		return simap;
	}

	public void setSimap(String simap) {
		this.simap = simap;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getSpass() {
		return spass;
	}

	public void setSpass(char[] password) {
		this.spass = new String(password);
	}
	
}
