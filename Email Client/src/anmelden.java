

import javax.swing.*;

/**
 * The class Anmelden reads the login properties
 * 
 * @author Furkan Yuecel
 * @author Suepriz Yaykan
 * @author Ali Selvi
 * @version 1.0
 */
public class anmelden {

	public String smtp;
	public String imap;
	public String email;
	public String pass;
	
	/**
	 * Declaration and initialization of GUI elements
	 */
	private final JTextField jSmtp = new JTextField("Please enter SMTP server", 25);
	private final JTextField jImap = new JTextField("Please enter IMAP server", 25);
	private final JTextField jEmail = new JTextField("Please enter E-Mail Adress",25);
	private final JPasswordField jPass = new JPasswordField("",15);
	
	/**
	 * Standard constructor to call the included features at the class "start".
	 * "title" is g
	 * 
	 * @param title
	 */
	public anmelden(String title){
		
		/**
		 * Objects which are shown on the popup frame
		 */
		Object[] props = {title, "IMAP", jImap, "SMTP", jSmtp, "E-Mail:", jEmail, "Password:", jPass};
		
		/**
		 * Popup for entering the informations
		 */
		JOptionPane fenster = new JOptionPane(props, JOptionPane.PLAIN_MESSAGE,	JOptionPane.DEFAULT_OPTION);
		fenster.createDialog(null,"Properties").setVisible(true);
		
		/**
		 * Setting the entered data
		 */
		setSsmtp(jSmtp.getText());
		setSimap(jImap.getText());
		setSemail(jEmail.getText());
		setSpass(jPass.getPassword());
		
	}
	
	
	public String getSsmtp() {
		return smtp;
	}

	public void setSsmtp(String ssmtp) {
		this.smtp = ssmtp;
	}

	public String getSimap() {
		return imap;
	}

	public void setSimap(String simap) {
		this.imap = simap;
	}

	public String getSemail() {
		return email;
	}

	public void setSemail(String semail) {
		this.email = semail;
	}

	public String getSpass() {
		return pass;
	}

	public void setSpass(char[] password) {
		this.pass = new String(password);
	}
	
}
