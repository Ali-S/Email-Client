import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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
import javax.swing.JTextField;
import javax.swing.JToolBar;



public class guisende {
	
	String message; 
	
	public guisende(){
		JFrame frame = new JFrame("New E-Mail");
		frame.setLocationRelativeTo(null);
		frame.setSize(800,600);
		
		
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setSize(300,100);
		panel.setLayout(layout);
		
		JLabel lto = new JLabel("TO:");
		panel.add(lto);
		
		JTextField tfto = new JTextField("",15);
		panel.add(tfto);
		
		JLabel lsubject = new JLabel("Subject:");
		panel.add(lsubject);
		
		JTextField tfsubject = new JTextField("",15);
		panel.add(tfsubject);
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				 	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				 			.addComponent(lto).addComponent(tfto))
				 	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				 			.addComponent(lsubject).addComponent(tfsubject)));
		
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(lto).addComponent(lsubject))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(tfto).addComponent(tfsubject)));
		
		
		
		JToolBar tbar = new JToolBar();
		tbar.add(new JButton("Test"));
		tbar.add(new JButton("Made"));
		tbar.setSize(300, 10);
		
		JEditorPane editor = new JEditorPane();
		editor.setContentType("text/html");
		editor.setEditable(true);
		
		JScrollPane scrollPane = new JScrollPane(editor);
		
		message = editor.getText();
		
		
		
		
		frame.add(panel);
		frame.add(tbar);
		frame.add(scrollPane);
		frame.setLayout(new GridLayout(3,0));
		frame.setVisible(true);
	}
}
