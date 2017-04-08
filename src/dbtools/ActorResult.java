package dbtools;

public class ActorResult {

	private String ActorName;
	private Movie m;
	private int moviesActedIn;
	private int avgMovieScore;

	public ActorResult(String actorName, Movie m) {
		this.m = m;
		ActorName = actorName;
	}

	public ActorResult(String actorName, int moviesActedIn, int avgMovieScore) {
		ActorName = actorName;
		this.moviesActedIn = moviesActedIn;
		this.avgMovieScore = avgMovieScore;
	}

	public int getMoviesActedIn() {
		return moviesActedIn;
	}

	public int getAvgMovieScore() {
		return avgMovieScore;
	}

	public String getActorName() {
		return ActorName;
	}

	public Movie getMovie() {
		return m;
	}

}
