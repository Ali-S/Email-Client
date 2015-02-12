
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JOptionPane;

/**
 * The class "save"  
 * 
 */
public class save {
	
	/**
	 * Constructor
	 * 
     * @param smtp
     * @param imap
     * @param email
     * @param password
     */
	public save(String smtp, String imap,String email, String password) {
		
		/**
		 * Save properties in "props.txt" file
		 * 
		*/
		PrintWriter writer;
		try {
			File file = new File("./bin/props.txt");
			if(file.exists() == false){
				try {
					file.createNewFile();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,"Fehler");
				}
			}
			writer = new PrintWriter((file), "UTF-8");
			writer.println(smtp);
			writer.println(imap);
			writer.println(email);
			writer.println(password);
			writer.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not Found");
			System.out.println("File not Found!");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("File could be creat");
			e.printStackTrace();
		}	
	}
}
