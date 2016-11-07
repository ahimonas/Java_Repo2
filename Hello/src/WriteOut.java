import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteOut {
	public WriteOut(StringBuilder myOut) throws IOException {
		File file = new File("C:/Users/Andrew Himonas/Desktop/file.txt");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(myOut.toString());
		}
	}

}
