package gui;

import java.util.ArrayList;
import java.util.HashMap;

import dbtools.DatabaseTools;
import dbtools.Movie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Recommender {

	@FXML
	Pane rootPane;
	@FXML
	VBox toPane;
	@FXML
	VBox fromPane;
	@FXML
	TextField searchField;

	private HBox selected;
	private HBox selectedTo;
	private HashMap<HBox, Movie> selecteds;
	private Stage parentStage;
	private double xOffset = 0;
    private double yOffset = 0;
    private DatabaseTools data;

	public Recommender(Stage recommenderStage, DatabaseTools data) {
		parentStage = recommenderStage;
		this.data = data;
		selecteds = new HashMap<HBox, Movie>();
	}

	public void addMovieList(ArrayList<Movie> movies) {
		fromPane.getChildren().clear();
		for(Movie m : movies) {
			addMovieToFromPane(m);
		}
	}

	public void addMovieToToPane(Movie m) {
		HBox b = new HBox();
		b.setMaxWidth(420);
		b.setSpacing(20);
		b.setPrefHeight(25);
		b.getChildren().add(new Label(m.getTitle()));
		toPane.getChildren().add(b);
		b.setOnMouseClicked((obj) -> {
			if(selectedTo != null) {
				selectedTo.setStyle("");
			}
			selectedTo = b;
			selectedTo.setStyle("-fx-background-color: #ff0000;");
		});
	}

	public void addMovieToFromPane(Movie m) {
		HBox b = new HBox();
		b.setMaxWidth(420);
		b.setSpacing(20);
		b.setPrefHeight(25);
		b.getChildren().add(new Label(m.getTitle()));
		fromPane.getChildren().add(b);
		selecteds.put(b, m);
		b.setOnMouseClicked((obj) -> {
			if(selected != null) {
				selected.setStyle("");
			}
			selected = b;
			selected.setStyle("-fx-background-color: #ff0000;");
		});
	}


	@FXML
	private void closeButton(ActionEvent e) {
		parentStage.hide();
	}

	@FXML
	private void search() {
		addMovieList(data.getMovies(searchField.getText()));
	}

	@FXML
	private void addMovie(ActionEvent e) {
		addMovieToToPane(selecteds.get(selected));
	}

	@FXML
	private void removeMovie(ActionEvent e) {
		toPane.getChildren().remove(selectedTo);
	}

	public void makeDraggable(Pane p) {
		/*
		 * Make the window draggable by the menu bar
		 */
		p.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        xOffset = event.getSceneX();
		        yOffset = event.getSceneY();
		    }
		});
		p.setOnMouseDragged(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		    	parentStage.setX(event.getScreenX() - xOffset);
		    	parentStage.setY(event.getScreenY() - yOffset);
		    }
		});
	}
}