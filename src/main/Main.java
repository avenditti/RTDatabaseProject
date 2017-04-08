package main;

import Datatypes.ActorResult;
import Datatypes.DirectorResult;
import Datatypes.Movie;
import Datatypes.TagResult;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	static DatabaseTools data;

	public static void main(String[] args) {
		data = new DatabaseTools();
		if(!data.initializeDatabase()) {
			System.out.println("Error initializing database");
			return;
		}
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		int i = 0;
//		for(Movie m : data.getMoviesByDirector("Kurosawa")) {
//			i++;
//			System.out.println(i + " " + m.getId() + " " + m.getTitle());
//		}
//		for(DirectorResult d : data.getTop10DirectorsByAvgMovieScore(10)) {
//			System.out.printf("%-25s %3d %10d\n", d.getDirectorName(), d.getMoviesDirected(), d.getAvgMovieScore());
//		}
//		for(ActorResult a : data.getTop10ActorsByAvgMovieScore(10)) {
//			System.out.printf("%-25s %3d %10d\n", a.getActorName(), a.getMoviesActedIn(), a.getAvgMovieScore());
//		}
//		for(TagResult t : data.getMovieTags("Lord of the Rings")) {
//			System.out.printf("%-50s %-50s\n", t.getMovieTitle(), t.getTagValue());
//		}
	}

}
