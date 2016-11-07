import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Update {

	/*
	 * ”. This file contains the changes to movie data since the last time the
	 * extract ran.
	 * 
	 * This file will be sent to you. You have been contracted to develop the
	 * code necessary to apply these updates to the master file as well as
	 * generate needed managerial reports. Record Type (“H”) File Name File Date
	 * (MM\DD\YYYY)
	 */

	String fileName = "C:/Users/Andrew Himonas/Desktop/Movies2.txt";

	public static int headerCounter;
	public static int updatecounter;
	String headerName;
	// Header
	String recordH;
	String fileNameH;
	Calendar todayH = Calendar.getInstance();

	// Details
	String recordD;
	String actionCode;
	String movieTitle;
	int boxID;
	int movieID;
	String movieGenre;
	int releaseYear;
	boolean inStock;
	int totalRentals;
	int last30Days;

	// Trailer
	String recordTypeT;
	String fileNameT;
	Calendar todayT = Calendar.getInstance();
	int numOfRecs;
	int sumTotalRentals;

	int count = 0;

	ArrayList<Movie> myMovies = new ArrayList<Movie>();

	public Update(ArrayList<Movie> myMovies) {
		this.myMovies = myMovies;
	}

	public void updateMovies() {
		try {

			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(
					"C:/Users/Andrew Himonas/Desktop/Movies2.txt");

			// Always wrap FileReader in BufferedReader.
			String line = null;

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				String[] movieData = line.split("#");

				// Movie newMovie = new Movie(Integer.parseInt(movieData[0]),
				// Integer.parseInt(movieData[1]), movieData[2],movieData[3],
				// movieData[4],
				// Boolean.parseBoolean(movieData[5]),Integer.parseInt(movieData[7]),Integer.parseInt(movieData[8]));

				// if(recordType.equals("H") || recordType.equals("T"))
				if (movieData[0].equals("H") || movieData[0].equals("T")) {
					// this is the header parse it differently
					headerName = movieData[1];
					count++;
				} else {
					/*
					 * String recordD; String actionCode; String movieTitle; int
					 * boxID; int movieID; String movieGenre; int releaseYear;
					 * boolean inStock; int totalRentals; int last30Days;
					 */
					String recordType = movieData[0];
					String actionCode = movieData[1];
					int boxID = Integer.parseInt(movieData[2]);
					int movieID = Integer.parseInt(movieData[3]);
					String movieTitle = movieData[4];
					String movieGenre = movieData[5];
					String releaseYear = movieData[6];
					Boolean inStock = Boolean.parseBoolean(movieData[7]);
					int totalRentals = Integer.parseInt(movieData[8]);
					int rentalsLast30Days = Integer.parseInt(movieData[9]);

					// D#A#0001#0020#Narcos#Action#2015#true#7#1
					/*
					 * Record Type (“D”) Action Code (“A”, “C”, “D”)
					 */
					if (actionCode.equals("A"))
						myMovies.add(new Movie(boxID, movieID, movieTitle,
								movieGenre, releaseYear, inStock, totalRentals,
								last30Days));
					System.out.println("ACTION" + actionCode);
					if (actionCode.equals("D")) {
						System.out.println("HELLO");
						// search for element to delete
						for (int i = 0; i < myMovies.size(); i++) {
							if (myMovies.get(i).getMovieID() == movieID)
								myMovies.remove(i);
						}
					}

					if (actionCode.equals("C")) {
						for (int i = 0; i < myMovies.size(); i++) {
							if (myMovies.get(i).getMovieID() == movieID) {
								myMovies.get(i).boxID = boxID;
								myMovies.get(i).movieID = movieID;
								myMovies.get(i).movieTitle = movieTitle;
								myMovies.get(i).release = releaseYear;
								myMovies.get(i).totalRentals = totalRentals;
							}

						}

					}

					/*
					 * Box ID Movie ID Movie Title Movie Genre Release Year
					 * InStock Indicator Total Rentals Rentals Last 30 Days
					 */

					// if actionCode.equals(A)
					// if actionCode.equals("A")

					count++;
				}
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
	}

	public void printMovies() {
		System.out
				.println("This list should be the list of movies printing from UPDATE List Class");
		for (int i = 0; i < myMovies.size(); i++)
			myMovies.get(i).printMovies();
	}
}
