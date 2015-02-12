
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
			File file = new File("./src/props.txt");
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			writer = new PrintWriter((file), "UTF-8");
			writer.println(smtp);
			writer.println(imap);
			writer.println(email);
			writer.println(password);
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found!");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("File could be creat");
			e.printStackTrace();
		}	
	}
}
