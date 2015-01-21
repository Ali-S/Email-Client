import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class guisende {
	public guisende(){
		JFrame fenster = new JFrame("Send E-Mail");
		fenster.getContentPane().setLayout(new BorderLayout());
		JTextArea textarea = new JTextArea();
		textarea.setSize(400, 300);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.setSize(400, 100);
		JLabel to = new JLabel("To: ");
		panel.add(to);
		to.setAlignmentX(to.CENTER_ALIGNMENT);
		JTextArea textto = new JTextArea();
		to.add(textto);
		JLabel subject = new JLabel("Subject: ");
		panel.add(subject);
		to.setAlignmentX(subject.CENTER_ALIGNMENT);
		
		fenster.add(panel, BorderLayout.NORTH);
		fenster.add(textarea, BorderLayout.CENTER);
		
		fenster.setSize(600, 600);
		fenster.setLocationRelativeTo(null);
		fenster.setVisible(true);
		
	}
}
