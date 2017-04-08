package Datatypes;

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

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", imdbID=" + imdbID + ", spanishTitle=" + spanishTitle
				+ ", imdbPictureURL=" + imdbPictureURL + ", year=" + year + ", rtID=" + rtID + ", rtAllCriticsRating="
				+ rtAllCriticsRating + ", rtAllCriticsNumReviews=" + rtAllCriticsNumReviews + ", rtAllCriticsNumFresh="
				+ rtAllCriticsNumFresh + ", rtAllCriticsNumRotten=" + rtAllCriticsNumRotten + ", rtAllCriticsScore="
				+ rtAllCriticsScore + ", rtTopCriticsRating=" + rtTopCriticsRating + ", rtTopCriticsNumReviews="
				+ rtTopCriticsNumReviews + ", rtTopCriticsNumFresh=" + rtTopCriticsNumFresh + ", rtTopCriticsNumRotten="
				+ rtTopCriticsNumRotten + ", rtTopCriticsScore=" + rtTopCriticsScore + ", rtAudienceRating="
				+ rtAudienceRating + ", rtAudienceNumRatings=" + rtAudienceNumRatings + ", rtAudienceScore="
				+ rtAudienceScore + ", rtPictureURL=" + rtPictureURL + "]";
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
