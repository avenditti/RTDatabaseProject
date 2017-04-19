package dbtools;

public class Movie {

	private int id;
	private String title;
	private String imdbID;
	private String spanishTitle;
	private String imdbPictureURL;
	private int year;
	private String rtID;
	private int rtAllCriticsRating;
	private int rtAllCriticsNumReviews;
	private int rtAllCriticsNumFresh;
	private int rtAllCriticsNumRotten;
	private int rtAllCriticsScore;
	private int rtTopCriticsRating;
	private int rtTopCriticsNumReviews;
	private int rtTopCriticsNumFresh;
	private int rtTopCriticsNumRotten;
	private int rtTopCriticsScore;
	private int rtAudienceRating;
	private int rtAudienceNumRatings;
	private int rtAudienceScore;
	private String rtPictureURL;
	private String directorName;

	public Movie(int id, String title, String imdbID, String spanishTitle, String imdbPictureURL, int year, String rtID, int rtAllCriticsRating, int rtAllCriticsNumReviews, int rtAllCriticsNumFresh, int rtAllCriticsNumRotten, int rtAllCriticsScore, int rtTopCriticsRating, int rtTopCriticsNumReviews, int rtTopCriticsNumFresh, int rtTopCriticsNumRotten, int rtTopCriticsScore, int rtAudienceRating, int rtAudienceNumRatings, int rtAudienceScore, String rtPictureURL) {
		this.id = id;
		this.title = title;
		this.imdbID = imdbID;
		this.spanishTitle = spanishTitle;
		this.imdbPictureURL = imdbPictureURL;
		this.year= year;
		this.rtID = rtID;
		this.rtAllCriticsRating = rtAllCriticsRating;
		this.rtAllCriticsNumReviews = rtAllCriticsNumReviews;
		this.rtAllCriticsNumFresh = rtAllCriticsNumFresh;
		this.rtAllCriticsNumRotten = rtAllCriticsNumRotten;
		this.rtAllCriticsScore = rtAllCriticsScore;
		this.rtTopCriticsRating = rtTopCriticsRating;
		this.rtTopCriticsNumReviews = rtTopCriticsNumReviews;
		this.rtTopCriticsNumFresh = rtTopCriticsNumFresh;
		this.rtTopCriticsNumRotten = rtTopCriticsNumRotten;
		this.rtTopCriticsScore = rtTopCriticsScore;
		this.rtAudienceRating = rtAudienceRating;
		this.rtAudienceNumRatings = rtAudienceNumRatings;
		this.rtAudienceScore = rtAudienceScore;
		this.rtPictureURL = rtPictureURL;
	}

	public Movie(int id, String title, String imdbID, String spanishTitle, String imdbPictureURL, int year, String rtID, int rtAllCriticsRating, int rtAllCriticsNumReviews, int rtAllCriticsNumFresh, int rtAllCriticsNumRotten, int rtAllCriticsScore, int rtTopCriticsRating, int rtTopCriticsNumReviews, int rtTopCriticsNumFresh, int rtTopCriticsNumRotten, int rtTopCriticsScore, int rtAudienceRating, int rtAudienceNumRatings, int rtAudienceScore, String rtPictureURL, String directorName) {
		this.id = id;
		this.title = title;
		this.imdbID = imdbID;
		this.spanishTitle = spanishTitle;
		this.imdbPictureURL = imdbPictureURL;
		this.year= year;
		this.rtID = rtID;
		this.rtAllCriticsRating = rtAllCriticsRating;
		this.rtAllCriticsNumReviews = rtAllCriticsNumReviews;
		this.rtAllCriticsNumFresh = rtAllCriticsNumFresh;
		this.rtAllCriticsNumRotten = rtAllCriticsNumRotten;
		this.rtAllCriticsScore = rtAllCriticsScore;
		this.rtTopCriticsRating = rtTopCriticsRating;
		this.rtTopCriticsNumReviews = rtTopCriticsNumReviews;
		this.rtTopCriticsNumFresh = rtTopCriticsNumFresh;
		this.rtTopCriticsNumRotten = rtTopCriticsNumRotten;
		this.rtTopCriticsScore = rtTopCriticsScore;
		this.rtAudienceRating = rtAudienceRating;
		this.rtAudienceNumRatings = rtAudienceNumRatings;
		this.rtAudienceScore = rtAudienceScore;
		this.rtPictureURL = rtPictureURL;
		this.directorName = directorName;
	}

	public String getDirectorName() {
		return directorName;
	}


	@Override
	public String toString() {
		return ""
				+ "Movie ID: \t" + id + "\n"
				+ "Movie Title: \t" + title + "\n"
				+ "Year: \t\t" + year + "\n"
				+ "imdbID: \t" + imdbID + "\n"
				+ "rtId: \t\t" + rtID + "\n"
				+ "Spanish Title: \t" + spanishTitle + "\n\n"
				+ "All Critics Rating: \t\t" + rtAllCriticsRating + "\n"
				+ "All Critics Reviews: \t\t" + rtAllCriticsNumReviews + "\n"
				+ "All Critics Fresh Score: \t" + rtAllCriticsNumFresh + "\n"
				+ "All Critics Rotten Score: \t" + rtAllCriticsNumRotten + "\n"
				+ "All Critics Score: \t\t" + rtAllCriticsScore + "\n"
				+ "Top Critics Rating: \t\t" + rtTopCriticsRating + "\n"
				+ "Top Critics Reviews: \t\t" + rtTopCriticsNumReviews + "\n"
				+ "Top Critics Fresh Score: \t" + rtTopCriticsNumFresh + "\n"
				+ "Top Critics Rotten Score: \t" + rtTopCriticsNumRotten + "\n"
				+ "Top Critics Score: \t\t" + rtTopCriticsScore + "\n"
				+ "Audience Rating: \t\t" + rtAudienceRating + "\n"
				+ "Audience Ratings: \t\t" + rtAudienceNumRatings + "\n"
				+ "Audience Score: \t\t" + rtAudienceScore;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getImdbID() {
		return imdbID;
	}

	public String getSpanishTitle() {
		return spanishTitle;
	}

	public String getImdbPictureURL() {
		return imdbPictureURL;
	}

	public int getYear() {
		return year;
	}

	public String getRtID() {
		return rtID;
	}

	public int getRtAllCriticsRating() {
		return rtAllCriticsRating;
	}

	public int getRtAllCriticsNumReviews() {
		return rtAllCriticsNumReviews;
	}

	public int getRtAllCriticsNumFresh() {
		return rtAllCriticsNumFresh;
	}

	public int getRtAllCriticsNumRotten() {
		return rtAllCriticsNumRotten;
	}

	public int getRtAllCriticsScore() {
		return rtAllCriticsScore;
	}

	public int getRtTopCriticsRating() {
		return rtTopCriticsRating;
	}

	public int getRtTopCriticsNumReviews() {
		return rtTopCriticsNumReviews;
	}

	public int getRtTopCriticsNumFresh() {
		return rtTopCriticsNumFresh;
	}

	public int getRtTopCriticsNumRotten() {
		return rtTopCriticsNumRotten;
	}

	public int getRtTopCriticsScore() {
		return rtTopCriticsScore;
	}

	public int getRtAudienceRating() {
		return rtAudienceRating;
	}

	public int getRtAudienceNumRatings() {
		return rtAudienceNumRatings;
	}

	public int getRtAudienceScore() {
		return rtAudienceScore;
	}

	public String getRtPictureURL() {
		return rtPictureURL;
	}
}
