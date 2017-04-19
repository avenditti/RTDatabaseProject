package dbtools;

public class UserResult {

	String movieName;
	int userName;
	int userRating;

	public UserResult(String movieName, int userName, int userRating) {
		this.movieName = movieName;
		this.userName = userName;
		this.userRating = userRating;
	}

	public String getMovieName() {
		return movieName;
	}

	public int getUserName() {
		return userName;
	}

	public int getUserRating() {
		return userRating;
	}
}
