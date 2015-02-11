
import java.awt.Checkbox;

import javax.swing.*;

/**
 * The class Anmelden reads configurations properties 
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
	
	private JTextField smtp = new JTextField("Please enter SMTP server", 25);
	private JTextField imap = new JTextField("Please enter IMAP server", 25);
	private JTextField email = new JTextField("Please enter E-Mail Adress",25);
	
	public anmelden(String title){
		
		JPasswordField pass = new JPasswordField("",15);
		
		Checkbox remember = new Checkbox("Remember me!");
		
		/**
		 * Objects they are listening on the popup frame
		 */
		Object[] props = {title, "IMAP", imap, "SMTP", smtp, "E-Mail:", email, "Password:", pass, remember};
		
		/**
		 * Popup for entering the informations
		 */
		JOptionPane fenster = new JOptionPane(props, JOptionPane.PLAIN_MESSAGE,	JOptionPane.DEFAULT_OPTION);
		fenster.createDialog(null,"Properties").setVisible(true);
		
		/**
		 * 
		 */
		setSsmtp(smtp.getText());
		setSimap(imap.getText());
		setSemail(email.getText());
		setSpass(pass.getPassword());
		
		/**
		 * checking remember properties from mail server
		 */
		if (remember.getState()) {
			System.out.println(getSsmtp()+getSimap()+getSemail()+getSpass());
			new save(getSsmtp(),getSimap(),getSemail(),getSpass());
			new HauptGUI(title);
			
		} else {
			new HauptGUI(title);
		}
		
	}
	
	/**
	 * Setter and getter functions
	 */
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
