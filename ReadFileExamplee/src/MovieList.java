import java.util.ArrayList;

public class MovieList {

	ArrayList<Movie> myMovies = new ArrayList<Movie>();

	public MovieList(ArrayList<Movie> myMovies) {
		this.myMovies = myMovies;
	}

	public void printMovies() {
		System.out
				.println("This list should be the list of movies printing from Movie List Class");
		for (int i = 0; i < myMovies.size(); i++)
			myMovies.get(i).printMovies();
	}

}
