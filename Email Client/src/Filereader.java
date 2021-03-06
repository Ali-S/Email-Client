
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The class "Filereader" writes and reads the login properties at the test.txt 
 */
public class Filereader {
	
	public String text;
	public String smtp;
	public char[] textchar;
	public String imap;
	public String email;
	public String password;
	
	/**
	 * Constructor which call the included features.
	 */
	public Filereader(){
		
		/**
		 * Reading properties from "props.txt" file
		 */
		try {
			File file = new File("./bin/props.txt");
			
			if(file.exists() == false){
				file.createNewFile();
			}
			
			FileReader filereader = new FileReader(file);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			StringBuffer stringbuffer = new StringBuffer();
			String line; 
			
			while ((line = bufferedreader.readLine()) != null) {
				stringbuffer.append(line);
				stringbuffer.append("\n");
			}
			
			filereader.close();
			text = stringbuffer.toString();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		textchar = text.toCharArray();
		
		/**
		 * Initialize String Builder
		 * to save properties from the file to attribute
		 */
		StringBuilder sbsmtp = new StringBuilder();
		StringBuilder sbimap = new StringBuilder();
		StringBuilder sbemail = new StringBuilder();
		StringBuilder sbpass = new StringBuilder();
		
		
		/**
		 * Reading smtp, imap, email and password information from text file
		 */
		int i;
		for (i = 0; textchar[i] != '\n'; i++) {
			sbsmtp.append(textchar[i]);
		}
		smtp = sbsmtp.toString();
		
		for (i = i+1; textchar[i] != '\n'; i++) {
			sbimap.append(textchar[i]);
		}
		imap = sbimap.toString();
		
		for (i = i+1; textchar[i] != '\n'; i++) {
			sbemail.append(textchar[i]);
		}
		email = sbemail.toString();
		
		for (i = i+1; textchar[i] != '\n'; i++) {
			sbpass.append(textchar[i]);
		}
		password = sbpass.toString();
	}

	
}
