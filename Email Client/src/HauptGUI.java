

import java.awt.*;

import javax.swing.*;

public class HauptGUI {
	
	private JFrame frame;
	private JMenuBar bar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2;
	private JTable table;
	private JScrollPane scroll;
	private JTabbedPane tabbed;
	private JPanel tab1, tab2, tab3, areaPanel, rightBorder, labelPanel, backgr;
	private JTextArea area;
	private JLabel sender, receiver, subject, date;
	
	public String[] mail = null;
	public String[] from = null;
	
	

	filereader file = new filereader();	
	getinbox get = new getinbox(file.imap,file.email,file.password);

	public HauptGUI(String title){
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		
		bar = new JMenuBar();
		menu1 = new JMenu("File");
		item1 = new JMenuItem("Quit");
		menu1.add(item1);
		menu2 = new JMenu("Help");
		item2 = new JMenuItem("About");
		menu2.add(item2);
		bar.add(menu1);
		bar.add(menu2);
		frame.add(bar, BorderLayout.NORTH);
		
		/*
		for (int i = 0; i < get.nachrichten.length; i++) {
			mail[i] = get.getsubject(i);
		}
		
		for (int i = 0; i < get.nachrichten.length; i++) {
			from[i] = get.getfrom(i);
		}
		*/
		
		String [] columns = {"sender", "subject"};
		Object [][] data =  {{"test","test"},{"test", "email"}};
		
		table = new JTable(data, columns);
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
		
		area = new JTextArea("\n Text entry here: ", 50, 100);
		areaPanel = new JPanel(new GridLayout());
		areaPanel.add(area);
		
		rightBorder = new JPanel(new BorderLayout());
		labelPanel = new JPanel(new GridLayout(3,1));
		sender = new JLabel("   Sender: \t");
		receiver = new JLabel("   Receiver: \t");
		subject = new JLabel("   Subject: \t");
		date = new JLabel("   Date: \t");
		labelPanel.add(sender);
		labelPanel.add(receiver);
		labelPanel.add(subject);
		labelPanel.add(date);
		rightBorder.add(labelPanel, BorderLayout.NORTH);
		rightBorder.add(area);
		
		backgr = new JPanel(new BorderLayout());
		backgr.add(tabbed, BorderLayout.WEST);
		backgr.add(rightBorder, BorderLayout.CENTER);
		
		frame.getContentPane().add(backgr, BorderLayout.CENTER);
		
		frame.setSize(800, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}