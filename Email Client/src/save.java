
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


public class save {
	public save(String smtp, String imap,String email, String password) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(new File("/Users/furkanyucel/git/Java-Projekt_Email/src/email/test.txt"), "UTF-8");
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
