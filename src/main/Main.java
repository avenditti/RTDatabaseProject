package main;

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

	}

}
