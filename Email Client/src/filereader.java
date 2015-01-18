

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class filereader {
	public String text;
	public String smtp;
	public char[] textchar;
	public String imap;
	public String email;
	public String password;
	
	public filereader(){
		try {
			File file = new File("./src/test.txt");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		textchar = text.toCharArray();
		int i;
		StringBuilder sbsmtp = new StringBuilder();
		StringBuilder sbimap = new StringBuilder();
		StringBuilder sbemail = new StringBuilder();
		StringBuilder sbpass = new StringBuilder();
		
		
		// read smtp props from file 
		for (i = 0;textchar[i] != '\n'; i++) {
			sbsmtp.append(textchar[i]);
		}
		smtp = sbsmtp.toString();
		
		// read imap props from file 
		for (i = i+1;textchar[i] != '\n'; i++) {
			sbimap.append(textchar[i]);
		}
		imap = sbimap.toString();
		
		//read email props from file
		for (i = i+1; textchar[i] != '\n'; i++) {
			sbemail.append(textchar[i]);
		}
		email = sbemail.toString();
		
		//read pass props from file
		for (i = i+1; textchar[i] != '\n'; i++) {
			sbpass.append(textchar[i]);
		}
		password = sbpass.toString();
	}

	
}
