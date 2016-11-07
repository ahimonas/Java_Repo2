/*
VideoMart has created a “Master Movie File” containing data on all movies for all locations.  The file tracks the information needed to idetify the box location, the movie, how many times the movie has been rented and how many times the movie has been rented in the past 30 days.
 */

public class Movie {
	int boxID;
	int movieID;
	String movieTitle;
	String movieGenre;
	String release;
	boolean inStock;
	int releaseYear;
	int totalRentals;
	int last30Rentals;

	public boolean Equals(Movie mov) {
		if (boxID == mov.boxID && mov.movieID == movieID)
			// && movieTitle.equals(mov.movieTitle)
			// && movieGenre.equals(mov.movieGenre) && release.equals(release)
			// && inStock == mov.inStock && totalRentals == mov.totalRentals
			// && last30Rentals == mov.last30Rentals)
			return true;

		return false;
	}

	// 000#001#"Hello Movie"#"Horror"#"2006"#true#2016#0#0
	public Movie(int boxID, int movieID, String movieTitle, String movieGenre,
			String release, boolean inStock, int totalRentals, int last30Rentals) {
		this.boxID = boxID;
		this.movieID = movieID;
		this.movieTitle = movieTitle;
		this.movieGenre = movieGenre;
		this.release = release;
		this.inStock = inStock;
		// this.releaseYear = releaseYear;
		this.totalRentals = totalRentals;
		this.last30Rentals = last30Rentals;
	}

	public int getBoxID() {
		return boxID;
	}

	/**
	 * 
	 * @param movieID
	 */
	public void setBoxID(int boxID) {
		this.boxID = boxID;
	}

	/**
	 * 
	 * @return movieID
	 */
	public int getMovieID() {
		return movieID;
	}

	/**
	 * 
	 * @param movieID
	 */
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	/**
	 * 
	 * @return releaseYear
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * 
	 * @param releaseYear
	 */
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	/**
	 * 
	 * @return inStock
	 */
	public boolean inStock() {
		return inStock;
	}

	/**
	 * 
	 * @param inStock
	 */
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	/**
	 * 
	 * @return totalRentals
	 */
	public int getTotalRentals() {
		return totalRentals;
	}

	/**
	 * 
	 * @param totalRentals
	 */
	public void setTotalRentals(int totalRentals) {
		this.totalRentals = totalRentals;
	}

	/**
	 * 
	 * @return last30Rentals
	 */
	public int getLast30Rentals() {
		return last30Rentals;
	}

	/**
	 * 
	 * @param last30Rentals
	 */
	public void setLast30Rentals(int totalRentals) {
		this.totalRentals = totalRentals;
	}

	/**
	 * 
	 * @return movieTitle
	 */
	public int getMovieTitle() {
		return boxID;
	}

	/**
	 * 
	 * @param movieTitle
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	/**
	 * 
	 * @return movieGenre
	 */
	public String getMovieGenre() {
		return movieGenre;
	}

	/**
	 * 
	 * @param movieGenre
	 */
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public void printMovies() {
		System.out.println(boxID + " " + movieID + " " + movieTitle + " "
				+ movieGenre + " " + release + " " + inStock + " " + " "
				+ totalRentals + " " + last30Rentals);
	}

	public String returnMovie() {
		return (boxID + " " + movieID + " " + movieTitle + " " + movieGenre
				+ " " + release + " " + inStock + " " + " " + totalRentals
				+ " " + last30Rentals);
	}
}
