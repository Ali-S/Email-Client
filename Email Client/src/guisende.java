import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;



public class guisende {
	private JLabel tolabel,cclabel,bcclabel,subjectlabel;
	private JTextField tofield,ccfield,bccfield,subjectfield;
	private String message,to,cc,bcc,subject; 
	private JButton send,cancel;
	private JTextArea area;
	
	private SendButtonHandler sbhandler;
	private CancelButtonHandler cbhandler;
	
	private static int width = 500;
	private static int height = 500;
	
	private LineBorder border = new LineBorder(Color.BLACK); 
	
	public guisende(){
		JFrame fenster = new JFrame("Send a new E-Mail");
		fenster. setSize(width,height);
		
		Container feld = fenster.getContentPane();
		BorderLayout borderlayout = new BorderLayout();
		feld.setLayout(borderlayout);
		GridLayout gridlayout = new GridLayout(4,1);
		GridLayout clayout = new GridLayout(1, 1);
		
		tolabel = new JLabel("To: ");
		cclabel = new JLabel("CC:");
		bcclabel = new JLabel("BCC:");
		subjectlabel = new JLabel("Subject:");
		
		tofield = new JTextField();
		ccfield = new JTextField();
		bccfield = new JTextField();
		subjectfield = new JTextField();
		
		area = new JTextArea("Please enter your content");
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
		fenster.setVisible(true);
		fenster.setBackground(Color.white);
		
	}

	
	private class SendButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}

	private class CancelButtonHandler implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
}