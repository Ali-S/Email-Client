import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * The class "Reanswer" ensures to answer on specifically emails
 */
public class Reanswer extends GuiSend{
	private JLabel tolabel, cclabel, bcclabel, subjectlabel;
	private JTextField tofield, ccfield, bccfield, subjectfield;
	@SuppressWarnings("unused")
	private String message, to, cc, bcc, subject,fielto; 
	private JButton send, cancel;
	private JTextArea area;
	
	private SendButtonHandler sbhandler;
	private CancelButtonHandler cbhandler;
	
	private static int width = 500;
	private static int height = 500;
	
	private LineBorder border = new LineBorder(Color.BLACK); 
	
	Filereader file = new Filereader();
	JFrame fenster = new JFrame("Send a new E-Mail");

	/**
	 * Constructor calls the included features
	 * 
	 * @param to (receiver)
	 * @param subject 
	 * @param content 
	 */
	public Reanswer(String to, String subject, String content){
			fenster.setSize(width,height);
			
			Container feld = fenster.getContentPane();
			BorderLayout borderlayout = new BorderLayout();
			feld.setLayout(borderlayout);
			GridLayout gridlayout = new GridLayout(4,1);
			GridLayout clayout = new GridLayout(1, 1);
			
			tolabel = new JLabel("To: ");
			cclabel = new JLabel("CC: ");
			bcclabel = new JLabel("BCC: ");
			subjectlabel = new JLabel("Subject: ");
			
			tofield = new JTextField(to);
			ccfield = new JTextField();
			bccfield = new JTextField();
			subjectfield = new JTextField("RE: " + subject);
			
			
			area = new JTextArea("\n''"+content+"''");
			area.setLineWrap(true);
			area.setWrapStyleWord(true);
			
			send = new JButton("Send");
			sbhandler = new SendButtonHandler();
			send.addActionListener(sbhandler);
			
			cancel = new JButton("Cancel");
			cbhandler = new CancelButtonHandler();
			cancel.addActionListener(cbhandler);
			
			JPanel infopanel = new JPanel();
			JPanel contentpanel = new JPanel();
			JPanel buttonpanel = new JPanel();
			
			infopanel.setLayout(gridlayout);
			
			infopanel.add(tolabel);
			infopanel.add(tofield);
			infopanel.add(cclabel);
			infopanel.add(ccfield);
			infopanel.add(bcclabel);
			infopanel.add(bccfield);
			infopanel.add(subjectlabel);
			infopanel.add(subjectfield);
			infopanel.setBackground(Color.white);
			infopanel.setBorder(border);
			
			contentpanel.setLayout(clayout);
			contentpanel.add(area);
			contentpanel.setBorder(border);
			
			buttonpanel.add(send);
			buttonpanel.add(cancel);
			buttonpanel.setLayout(new FlowLayout());
			buttonpanel.setBorder(border);
			
			feld.add(infopanel,BorderLayout.NORTH);
			feld.add(contentpanel,BorderLayout.CENTER);
			feld.add(buttonpanel,BorderLayout.SOUTH);
			feld.setBackground(Color.white);
			fenster.setLocationRelativeTo(null);
			fenster.setVisible(true);
			fenster.setBackground(Color.white);
	}
	
private class SendButtonHandler implements ActionListener{
		
		/**
		 * sending mail by clicking
		 */
		public void actionPerformed(ActionEvent e) {
			to = tofield.getText();
			subject = subjectfield.getText();
			message = area.getText();
			new Send(file.smtp, file.email, file.password, to, cc, bcc, subject, message);
			fenster.dispose();
		}
	}

	private class CancelButtonHandler implements ActionListener{
		
		/**
		 * cancels this frame
		 */
		public void actionPerformed(ActionEvent e) {		
			fenster.dispose();
		}
	}
}
