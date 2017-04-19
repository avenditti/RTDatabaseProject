package dbtools;

public class UserResult {

	String movieName;
	int userName;
	int userRating;
	int day;
	int month;
	int year;
	int hour;
	int minute;
	int second;

	public UserResult(String movieName, int userName, int userRating, int day, int month, int year, int hour, int minute, int second) {
		this.movieName = movieName;
		this.userName = userName;
		this.userRating = userRating;
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
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
