import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFileExample {

	public static void main(String[] args) throws IOException {

		// The name of the file to open.
		String fileName = "C:/Users/Andrew Himonas/Desktop/Movies.txt.txt";

		// This will reference one line at a time
		String line = null;

		ArrayList<Movie> myMovies = new ArrayList<Movie>();

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				String[] movieData = line.split("#");
				Movie newMovie = new Movie(Integer.parseInt(movieData[0]),
						Integer.parseInt(movieData[1]), movieData[2],
						movieData[3], (movieData[4]),
						Boolean.parseBoolean(movieData[5]),
						Integer.parseInt(movieData[6]),
						Integer.parseInt(movieData[7]));
				myMovies.add(newMovie);

			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		Update u1 = new Update(myMovies);
		u1.updateMovies();
		System.out.println("Deleting movie");
		u1.printMovies();
		ArrayList<Movie> ml2 = u1.myMovies;

		// Movie m1 = new Movie(0, 1, "Hello Movie", "Horror", "2006", true, 0,
		// 0);
		Movie m2 = new Movie(1, 20, "Narcos", "Action", "2015", true, 7, 0);
		Movie m3 = new Movie(1, 20, "THISISANEWMOVIE", "Action", "2012", true,
				7, 0);

		ArrayList<Movie> ml1 = new ArrayList<Movie>();
		// ml1.add(m1);
		ml1.add(m2);
		ml1.add(m3);

		Process p1 = new Process();

		boolean checker = p1.MovieListComparator(ml1, ml2);
		System.out.println("THIS IS THE CHECKER" + checker);

		Report r1 = new Report(u1.myMovies, u1.headerName);
		r1.writeFile();
		// MovieList m1 = new MovieList(myMovies);
		// m1.printMovies();
		/*
		 * System.out.println("This list should be the list of movies"); for
		 * (int i = 0; i < myMovies.size(); i++) myMovies.get(i).printMovies();
		 */

	}

}
