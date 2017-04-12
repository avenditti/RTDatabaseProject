package main;

import dbtools.DatabaseTools;
import gui.MainGui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{

	static DatabaseTools data;
	static MainGui mainGui;

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
		FXMLLoader fxml = new FXMLLoader(MainGui.class.getResource("MainGui.fxml"));
		mainGui = new MainGui(primaryStage, data);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		fxml.setController(mainGui);
		primaryStage.setScene(new Scene(fxml.load(), Color.TRANSPARENT));
		mainGui.makeDragable(mainGui.rootPane);
		mainGui.initializeGui();
		primaryStage.show();
	}

}
