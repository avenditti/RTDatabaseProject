package Datatypes;

public class ActorResult {

	private String ActorName;
	private Movie m;

	public ActorResult(String actorName, Movie m) {
		this.m = m;
		ActorName = actorName;
	}

	public String getActorName() {
		return ActorName;
	}

	public Movie getMovie() {
		return m;
	}

}
