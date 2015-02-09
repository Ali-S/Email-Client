import java.io.IOException;

/**
 * 
 * @Author Suepriz Yaykan, Ali Metehan Selvi, Muhammed Furkan Yuecel
 */


public class start {

	static final String title = "Java E-Mail-Client Connection Init Beta v.01";
	
	
	public static void main(String[] args) throws IOException {
		// Teste anmelden Fenster
		//new anmelden(title);
		
		// Starte Hauptfenster
		new HauptGUI(title);
		
		//filereader file = new filereader();
		//new getinbox(file.imap,file.email,file.password);
		//System.out.println("Subjects" + get.getsubject(0));
		
		
		
		System.out.println("LAUFT");
	}

}
