package dbtools;

public class TagResult {

	String movieTitle;
	String tagValue;

	public TagResult(String movieTitle, String tagValue) {
		super();
		this.movieTitle = movieTitle;
		this.tagValue = tagValue;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public String getTagValue() {
		return tagValue;
	}

}
