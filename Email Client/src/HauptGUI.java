
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Stack;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;
import javax.mail.internet.MimeUtility;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.sun.mail.util.MimeUtil;

public class HauptGUI{
	
	private JFrame frame;
	private JMenuBar bar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2, item3;
	private JTable table;
	private JScrollPane scroll, scroll2;
	private JTabbedPane tabbed;
	private JPanel tab1, tab2, tab3, areaPanel, rightBorder, labelPanel, backgr;
	private JTextArea area;
	private JLabel sender, receiver, subject, date;
	
	public String[] mail = null;
	public String[] from = null;
	public String [] subjects,getfrom;
	
	

	filereader file = new filereader();	
	getinbox get = new getinbox(file.imap,file.email,file.password);

	@SuppressWarnings("unchecked")
	public HauptGUI(String title){
		
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		
		bar = new JMenuBar();
		menu1 = new JMenu("File");
		item1 = new JMenuItem("Quit");
		item3 = new JMenuItem("New E-Mail");
		menu1.add(item3);
		menu1.add(item1);
		menu2 = new JMenu("Help");
		item2 = new JMenuItem("About");
		menu2.add(item2);
		bar.add(menu1);
		bar.add(menu2);
		frame.add(bar, BorderLayout.NORTH);
		
		getfrom = new String[get.nachrichten.length];
		subjects = new String[get.nachrichten.length];

		String[] tableColumn = {"sender"};
		Object[][] tableData = {getfrom};
		
		String[] table2Column = {"subject"};
		Object[][] table2Data = {subjects};
		
		
		Vector subjectdata = new Vector();
		for (int i = 0; i < get.nachrichten.length; i++) {
			Vector row = new Vector();
			row.add(get.getsubject(i));
			subjectdata.add(row);
		}
		
		Vector fromdata = new Vector();
		for (int i = 0; i < get.nachrichten.length; i++) {
			Vector row = new Vector();
			row.add(get.getfrom(i));
			fromdata.add(row);
		}
		
		
		Vector sub = new Vector();
		sub.add("Subject");
		
		
		Vector fr = new Vector();
		fr.add("From");
		
		
		
		table = new JTable(fromdata, fr);
		
		for (int i = 0; i < get.nachrichten.length; i++) {
			subjects[i] = get.getsubject(i);
			
			System.out.println(i+".Subject " + subjects[i]);
		}
		
		for (int i = 0; i < get.nachrichten.length; i++) {
			getfrom[i] = get.getfrom(i);
		}

		
		table.setRowSelectionAllowed(true);
		scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(200, 650));
		
		
		tabbed = new JTabbedPane();
		tab1 = new JPanel();
		tab1.add(scroll);
		tab2 = new JPanel();
		tab3 = new JPanel();
		tabbed.addTab("Inbox", tab1);
		tabbed.addTab("Sent", tab2);
		tabbed.addTab("Deleted", tab3);
		
		rightBorder = new JPanel(new BorderLayout());
		labelPanel = new JPanel(new GridLayout(3,1));
		
		sender = new JLabel("   Sender: \t");
//		receiver = new JLabel("   Receiver: \t");
		subject = new JLabel("   Subject: \t");
		date = new JLabel("   Date: \t");
		labelPanel.add(sender);
//		labelPanel.add(receiver);
		labelPanel.add(date);
		labelPanel.add(subject);
		rightBorder.add(labelPanel, BorderLayout.NORTH);
	
		

		
		backgr = new JPanel(new BorderLayout());
		backgr.add(tabbed, BorderLayout.WEST);
		backgr.add(rightBorder, BorderLayout.CENTER);
		
		
		area = new JTextArea("\n Text entry here: ", 50, 100);
		areaPanel = new JPanel(new GridLayout());
		areaPanel.add(area);
		rightBorder.add(area);
		// Tabelle fŸr nur ein Select erlauben
		table.setCellSelectionEnabled(true);
		
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			// Nach select den Inhalt der Email anzeigen
			public void valueChanged(ListSelectionEvent event) {
				int[] selectedrow = table.getSelectedRows();
				if (!event.getValueIsAdjusting()) {
					for (int i = 0; i < selectedrow.length; i++) {
						Message mails = get.nachrichten[selectedrow[i]];
						try {
							area.setText(get.getcontent(selectedrow[i]));
							sender.setText("   Sender: \t" + get.getfrom(selectedrow[i]));
							date.setText("   Date: \t" + get.nachrichten[selectedrow[i]].getReceivedDate());
							subject.setText("   Subject: \t" + get.getsubject(selectedrow[i]));
							area.setEditable(false);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} 	 
			}
		
		});

		frame.getContentPane().add(backgr, BorderLayout.CENTER);
		
		frame.setSize(800, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	


}