package main;

import Datatypes.ActorResult;
import Datatypes.DirectorResult;
import Datatypes.Movie;
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
		for(ActorResult ar : data.getActorMovies("Wilmer Valderrama")) {
			System.out.println(ar.getActorName() + " " + ar.getMovie().getTitle());
		}
	}

}
