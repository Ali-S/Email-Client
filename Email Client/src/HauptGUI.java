
import java.awt.*;
import java.util.Stack;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HauptGUI {
	
	private JFrame frame;
	private JMenuBar bar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2;
	private JTable table, table2;
	private DefaultTableModel dtable1, dtable2;
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
		menu1.add(item1);
		menu2 = new JMenu("Help");
		item2 = new JMenuItem("About");
		menu2.add(item2);
		bar.add(menu1);
		bar.add(menu2);
		frame.add(bar, BorderLayout.NORTH);
		
		subjects = new String[get.nachrichten.length];
		getfrom = new String[get.nachrichten.length];
		
		
		String [] table1C = {"sender"};
		Object [][] table1D =  {getfrom};
		
		String [] table2C = {"subject"};
		Object [][] table2D = {subjects};
		
		dtable1 = new DefaultTableModel(0,0);
		dtable2 = new DefaultTableModel(0,0);
		table = new JTable(dtable1);
		table2 = new JTable(dtable2);
		dtable1.addColumn("table1C");
		dtable2.addColumn("Subject");
		Vector<String> drow1 = new Vector<String>();
		Vector<String> drow2 = new Vector<String>();
		
		for (int i = 0; i < get.nachrichten.length; i++) {
			subjects[i] = get.getsubject(i);
			drow2.add(get.getsubject(i));
			System.out.println(i+".Subject " + subjects[i]);
		}
		
		for (int i = 0; i < get.nachrichten.length; i++) {
			getfrom[i] = get.getfrom(i);
			drow1.add(get.getfrom(i));
		}
		dtable1.addRow(drow1);
		dtable2.addRow(drow2);
		table.setRowSelectionAllowed(true);
		table2.setRowSelectionAllowed(true);
		scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(200, 650));
		scroll2 = new JScrollPane(table2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.setPreferredSize(new Dimension(200, 650));
		
		
		tabbed = new JTabbedPane();
		tab1 = new JPanel();
		tab1.add(scroll);
		tab1.add(scroll2);
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
//		receiver = new JLabel("   Receiver: \t");
		subject = new JLabel("   Subject: \t");
		date = new JLabel("   Date: \t");
		labelPanel.add(sender);
//		labelPanel.add(receiver);
		labelPanel.add(date);
		labelPanel.add(subject);
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