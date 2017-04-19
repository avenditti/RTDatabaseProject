package dbtools;

public class DirectorResult {

	private String directorID;
	private String directorName;
	private int movieID;
	private String movieName;
	private int moviesDirected;
	private int avgMovieScore;

	public DirectorResult(int movieID, String directorID, String directorName, String movieName) {
		this.directorID = directorID;
		this.directorName = directorName;
		this.movieID = movieID;
		this.movieName = movieName;
	}

	public DirectorResult(String directorID, String directorName, int moviesDirected, int avgMovieScore) {
		this.directorID = directorID;
		this.directorName = directorName;
		this.moviesDirected = moviesDirected;
		this.avgMovieScore = avgMovieScore;
	}

	public DirectorResult(int movieID, String directorID, String directorName) {
		this.directorID = directorID;
		this.directorName = directorName;
		this.movieID = movieID;
	}

	public int getMoviesDirected() {
		return moviesDirected;
	}

	public int getAvgMovieScore() {
		return avgMovieScore;
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
