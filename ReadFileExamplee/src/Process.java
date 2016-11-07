import java.util.ArrayList;

public class Process {

	public boolean MovieListComparator(ArrayList<Movie> myMovies1,
			ArrayList<Movie> myMovies2) {
		boolean b = true;
		for (int i = 0; i < myMovies1.size(); i++) {
			if (!(myMovies1.get(i).Equals(myMovies2.get(i))))
				b = false;

		}

		return b;
	}

}
