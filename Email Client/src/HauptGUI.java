
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The MainGUI class 
 * 
 * 
 *
 * @author Furkan Yuecel
 * @author Suepriz Yaykan
 * @author Ali Selvi
 * @version 0.8
 */
public class HauptGUI{
	
	private JFrame frame;
	private JMenuBar bar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2, item3, item4;
	private JTable table;
	private JScrollPane scroll;
	private JTabbedPane tabbed;
	private JPanel tab1, tab2, areaPanel, rightBorder, labelPanel, backgr;
	private JTextArea area;
	private JLabel sender, receiver, subject, date, send_l;
	private JButton send;
	private Vector fromdata;
	
	public String[] mail = null;
	public String[] from = null;
	public String [] subjects,getfrom;
	
	int tabbedheight;

	filereader file = new filereader();	
	getinbox get = new getinbox(file.imap,file.email,file.password);

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HauptGUI(String title){								 		
		
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		
		bar = new JMenuBar();
		menu1 = new JMenu("File");
		item1 = new JMenuItem("Quit");
		item3 = new JMenuItem("New E-Mail");
		item4 = new JMenuItem("Update");
		menu1.add(item3);
		menu1.add(item4);
		menu1.add(item1);
		menu2 = new JMenu("Help");
		item2 = new JMenuItem("About");
		menu2.add(item2);
		bar.add(menu1);
		bar.add(menu2);
		frame.add(bar, BorderLayout.NORTH);
		item1.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            System.exit(0);
	        }
	    });
		item3.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new guisende();
		}
		});
		
		
		
		getfrom = new String[get.nachrichten.length];
		subjects = new String[get.nachrichten.length];
		
		Vector fromdata = new Vector();
		for (int i = 0; i < get.nachrichten.length; i++) {
			Vector row = new Vector();
			row.add("<html>" + get.getfrom(i) + "<br>" + "<br>" + get.getsubject(i) + "</html>");
			fromdata.add(row);
		}
		
		
		Vector fr = new Vector();
		fr.add("Inbox");
		
		
		table = new JTable(fromdata, fr);
		table.setRowHeight(75);
		table.setAutoCreateRowSorter(true);
		


		table.setRowSelectionAllowed(true);
		scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(200,600));

		
		
		tabbed = new JTabbedPane();
		tab1 = new JPanel();
		tab1.add(scroll);
		tab2 = new JPanel();
		tabbed.addTab("Inbox", tab1);
		tabbed.addTab("Sent", tab2);
		rightBorder = new JPanel(new BorderLayout());
		labelPanel = new JPanel(new GridLayout(4,1));
		
		sender = new JLabel("   Sender: \t");
//		receiver = new JLabel("   Receiver: \t");
		subject = new JLabel("   Subject: \t");
		date = new JLabel("   Date: \t");
		send_l = new JLabel();
		send = new JButton("new E-Mail");
		send_l.add(send);
		labelPanel.add(sender);
//		labelPanel.add(receiver);
		labelPanel.add(date);
		labelPanel.add(subject);
		labelPanel.add(send_l);
		rightBorder.add(labelPanel, BorderLayout.NORTH);
	
		
		
		backgr = new JPanel(new BorderLayout());
		backgr.add(tabbed, BorderLayout.WEST);
		backgr.add(rightBorder, BorderLayout.CENTER);
		
		
		area = new JTextArea("\n Text entry here: ", 50, 100);
		areaPanel = new JPanel(new GridLayout());
		areaPanel.add(area);
		rightBorder.add(area);
		// Tabelle fuer nur ein Select erlauben
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
							e.printStackTrace();
						} catch (MessagingException e) {
							e.printStackTrace();
						}
					}
				} 	 
			}
		
		});

		frame.getContentPane().add(backgr, BorderLayout.CENTER);
		
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	

}
