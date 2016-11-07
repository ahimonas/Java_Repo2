import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Report {
	ArrayList<Movie> myMovies = new ArrayList<Movie>();
	String header;

	public Report(ArrayList<Movie> myMovies, String header) {
		this.myMovies = myMovies;
		this.header = header;
	}

	public void writeFile() throws IOException {
		File file = new File("C:/Users/Andrew Himonas/Desktop/test.txt");
		StringBuilder b1 = new StringBuilder();
		b1.append(header);
		b1.append(System.getProperty("line.separator"));
		for (int i = 0; i < myMovies.size(); i++) {
			b1.append(myMovies.get(i).returnMovie());
			b1.append(System.getProperty("line.separator"));
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			writer.write(b1.toString());
			b1.append(System.getProperty("line.separator"));
		}
	}
}
