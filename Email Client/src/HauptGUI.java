
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
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
	
	/**
     * Declaration of GUI elements
     */
	private JFrame frame;
	@SuppressWarnings("unused")
	private JMenuBar bar,mailbar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2, item3, item4;
	private JTable table,table2;
	private JScrollPane scroll,scroll2;
	private JTabbedPane tabbed;
	private JPanel tab1, tab2, areaPanel, rightBorder, labelPanel, backgr,mailpanel;
	private JTextArea area;
	private JLabel sender, subject, date, send_l,standard;
	private JButton send,answer;
	@SuppressWarnings("rawtypes")
	private Vector fromdata,sentdata;
	private ImageIcon image,icon;
	private JToolBar toolbar;
	
	
	public String[] mail = null;
	public String[] from = null;
	public String [] subjects,getfrom;
	
	anmelden melde = new anmelden("Login");
	
	public String imap = melde.imap;
	public String smtp = melde.smtp;
	public String email = melde.email;
	public String password = melde.pass.toString();
	
	
	/**
     * Initialization this classes for getting properties and messages
     */
	
	getinbox get = new getinbox(imap,email,password);
	getsendbox sent = new getsendbox(imap,email,password);

	@SuppressWarnings({ "rawtypes", "unchecked" })
	
	/**
	 * The constructor calls the included features.
	 * "title" is given
	 * see
	 * 
	 * @param title 
	 */
	public HauptGUI(String title){
		/**
	     * Initialization GUI elements
	     */
		frame = new JFrame(title);
		frame.getContentPane().setLayout(new BorderLayout());
		
		bar = new JMenuBar();
		mailbar = new JMenuBar();
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
		fromdata = new Vector();
		sentdata = new Vector();
		image = new ImageIcon("./bin/java.png");
		icon = new ImageIcon("./bin/mail_answer.png");
		standard = new JLabel(image);
		
		
		/**
	     * 
	     */
		item1.addActionListener(new ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	            System.exit(0);
	        }
	    });
		
		item3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new guisende();
		}
		});
		
		/**
	     * 
	     */
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromdata.removeAllElements();
				System.out.println("Vector wurde geloescht");
				get = new getinbox(imap, email, password);
				for (int j = 0; j < get.nachrichten.length; j++) {
					Vector row = new Vector();
					row.add("<html>" + get.getfrom(j) + "<br>" +get.getdate(j)+ "<br>" + get.getsubject(j) + "</html>");
					fromdata.add(row);
				}
			}	
		});
		
		getfrom = new String[get.nachrichten.length];
		subjects = new String[get.nachrichten.length];
		
		for (int i = 0; i < get.nachrichten.length; i++) {
			Vector row = new Vector();
			row.add("<html>" + get.getfrom(i) + "<br>" +get.getdate(i) + "<br>" + get.getsubject(i) + "</html>");
			fromdata.add(row);
		}
		
		
		Vector fr = new Vector();
		fr.add("Inbox");
		
		
		
		table = new JTable(fromdata, fr);
		table.setRowHeight(75);
		table.setAutoCreateRowSorter(true);
		
		for (int i = 0; i < sent.nachrichten.length; i++) {
			Vector row = new Vector();
			row.add("<html>" + sent.getreceive(i) + "<br>" +sent.getdate(i)+ "<br>" + sent.getsubject(i) + "</html>");
			sentdata.add(row);
		}
		
		
		Vector sr = new Vector();
		sr.add("Sentbox");
		
		
		
		table2 = new JTable(sentdata, sr);
		table2.setRowHeight(75);
		table2.setAutoCreateRowSorter(true);
		
		/**
	     * 
	     */
		table.setRowSelectionAllowed(true);
		table2.setRowSelectionAllowed(true);
		scroll = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(200,700));

		
		scroll2 = new JScrollPane(table2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll2.setPreferredSize(new Dimension(200,700));

		
		
		tabbed = new JTabbedPane();
		tab1 = new JPanel();
		tab1.add(scroll);
		tab2 = new JPanel();
		tab2.add(scroll2);
		tabbed.addTab("Inbox", tab1);
		tabbed.addTab("Sent", tab2);
		rightBorder = new JPanel(new BorderLayout());
		labelPanel = new JPanel(new GridLayout(4,1));
		mailpanel = new JPanel(new BorderLayout());
		
		sender = new JLabel("   Sender: \t");
		subject = new JLabel("   Subject: \t");
		date = new JLabel("   Date: \t");
		send_l = new JLabel();
		send = new JButton("new E-Mail");
		send_l.add(send);
		labelPanel.add(sender);
		labelPanel.add(date);
		labelPanel.add(subject);
		labelPanel.add(send_l);
		
		toolbar = new JToolBar();
		answer = new JButton();
		answer.setIcon(icon);
		toolbar.add(answer);
		
		area = new JTextArea("\n No mail selected.", 40, 100);
		mailpanel.add(toolbar,BorderLayout.NORTH);
		mailpanel.add(area,BorderLayout.CENTER);
		areaPanel = new JPanel();
		areaPanel.add(mailpanel);
		areaPanel.add(standard);
		standard.setVisible(true);
		mailpanel.setVisible(false);
		
		

		
		rightBorder.add(labelPanel, BorderLayout.NORTH);
		rightBorder.add(areaPanel,BorderLayout.CENTER);
	
		backgr = new JPanel(new BorderLayout());
		backgr.add(tabbed, BorderLayout.WEST);
		backgr.add(rightBorder, BorderLayout.CENTER);
		

		// Tabelle fuer nur ein Select erlauben
		table.setCellSelectionEnabled(true);
		table2.setCellSelectionEnabled(true);
		
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    ListSelectionModel cellSelectionModel2 = table2.getSelectionModel();
	    cellSelectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    
	    /**
	     * Shows the selected email in detail on GUI
	     */
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			// Nach select den Inhalt der Email anzeigen
			public void valueChanged(ListSelectionEvent event) {
				int[] selectedrow = table.getSelectedRows();
				if (!event.getValueIsAdjusting()) {
					standard.setVisible(false);
					mailpanel.setVisible(true);
					for (int i = 0; i < selectedrow.length; i++) {
						final Message mails = get.nachrichten[selectedrow[i]];
						try {
							area.setText(get.getcontent(selectedrow[i]));
							sender.setText("   Sender: \t" + get.getfrom(selectedrow[i]));
							date.setText("   Date: \t" + get.nachrichten[selectedrow[i]].getReceivedDate());
							subject.setText("   Subject: \t" + get.getsubject(selectedrow[i]));
							area.setEditable(false);
							
							answer.addActionListener(new ActionListener() {
								
								public void actionPerformed(ActionEvent e) {
									try {
										new reanswer(InternetAddress.toString(mails.getFrom()), mails.getSubject(), mails.getContent().toString());
									} catch (MessagingException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
							} );
						} catch (IOException e) {
							e.printStackTrace();
						} catch (MessagingException e) {
							e.printStackTrace();
						}
					}
				} 	 
			}
		
		});
		
		table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			// Nach select den Inhalt der Email anzeigen
			public void valueChanged(ListSelectionEvent event) {
				int[] selectedrow = table2.getSelectedRows();
				if (!event.getValueIsAdjusting()) {
					standard.setVisible(false);
					mailpanel.setVisible(true);
					for (int i = 0; i < selectedrow.length; i++) {
						try {
							area.setText(sent.getcontent(selectedrow[i]));
							sender.setText("   Receiver: \t" + sent.getreceive(selectedrow[i]));
							date.setText("   Date: \t" + sent.nachrichten[selectedrow[i]].getSentDate());
							subject.setText("   Subject: \t" + sent.getsubject(selectedrow[i]));
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

		frame.setResizable(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
