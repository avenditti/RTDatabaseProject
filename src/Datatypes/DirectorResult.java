package Datatypes;

public class DirectorResult {

	String directorID;
	String directorName;
	int movieID;
	String movieName;

	public DirectorResult(int movieID, String directorID, String directorName, String movieName) {
		this.directorID = directorID;
		this.directorName = directorName;
		this.movieID = movieID;
		this.movieName = movieName;
	}

	public String getDirectorID() {
		return directorID;
	}

	public String getDirectorName() {
		return directorName;
	}

	public int getMovieID() {
		return movieID;
	}

	public String getMovieName() {
		return movieName;
	}

}
