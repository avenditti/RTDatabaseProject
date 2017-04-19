package gui;

import java.util.ArrayList;
import java.util.HashMap;

import dbtools.DatabaseTools;
import dbtools.DirectorResult;
import dbtools.Movie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	@FXML
	Button execute;
	@FXML
	Button searchButton;
	@FXML
	Button add;
	@FXML
	Button remove;

	private HBox selected;
	private HBox selectedTo;
	private HashMap<HBox, Movie> selectedMovies;
	private HashMap<HBox, DirectorResult> selectedDirectors;
	private Stage parentStage;
	private double xOffset = 0;
    private double yOffset = 0;
    private DatabaseTools data;

	public Recommender(Stage recommenderStage, DatabaseTools data) {
		parentStage = recommenderStage;
		this.data = data;
		selectedMovies = new HashMap<HBox, Movie>();
		selectedDirectors = new HashMap<HBox, DirectorResult>();
	}

	public void addMovieList(ArrayList<Movie> movies) {
		fromPane.getChildren().clear();
		for(Movie m : movies) {
			addMovieToFromPane(m);
		}
	}

	public void addDirectorList(ArrayList<DirectorResult> d) {
		fromPane.getChildren().clear();
		for(DirectorResult m : d) {
			addDirectorToFromPane(m);
		}
	}

	public void addDirectorToToPane(DirectorResult d) {
		HBox b = new HBox();
		b.setMaxWidth(420);
		b.setSpacing(20);
		b.setPrefHeight(25);
		b.getChildren().add(new Label(d.getDirectorName()));
		toPane.getChildren().add(b);
		b.setOnMouseClicked((obj) -> {
			if(selectedTo != null) {
				selectedTo.setStyle("");
			}
			selectedTo = b;
			selectedTo.setStyle("-fx-background-color: #ff0000;");
		});
	}

	public void addDirectorToFromPane(DirectorResult d) {
		HBox b = new HBox();
		b.setMaxWidth(420);
		b.setSpacing(20);
		b.setPrefHeight(25);
		b.getChildren().add(new Label(d.getDirectorName()));
		fromPane.getChildren().add(b);
		selectedDirectors.put(b, d);
		b.setOnMouseClicked((obj) -> {
			if(selected != null) {
				selected.setStyle("");
			}
			selected = b;
			selected.setStyle("-fx-background-color: #ff0000;");
		});
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
		selectedMovies.put(b, m);
		b.setOnMouseClicked((obj) -> {
			if(selected != null) {
				selected.setStyle("");
			}
			selected = b;
			selected.setStyle("-fx-background-color: #ff0000;");
		});
	}

	@FXML
	private void recByDirectors(ActionEvent e) {
		clear();
		searchField.setPromptText("Enter Director Name");
		searchButton.setOnAction((obj) -> {
			addDirectorList(data.getDirector(searchField.getText()));
		});
		add.setOnAction((obj) -> {
			addDirectorToToPane(selectedDirectors.get(selected));
		});
		remove.setOnAction((obj) -> {
			toPane.getChildren().remove(selectedTo);
		});
	}

	@FXML
	private void recByMovies(ActionEvent e) {
		clear();
		searchField.setPromptText("Enter Movie Name");
		searchButton.setOnAction((obj) -> {
			addMovieList(data.getMovies(searchField.getText()));
		});
		add.setOnAction((obj) -> {
			addMovieToToPane(selectedMovies.get(selected));
		});
		remove.setOnAction((obj) -> {
			toPane.getChildren().remove(selectedTo);
		});
	}

	@FXML
	private void closeButton(ActionEvent e) {
		parentStage.hide();
	}

	public void makeDraggable(Pane p) {
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

	public void clear() {
		selectedMovies.clear();
		selectedDirectors.clear();
		fromPane.getChildren().clear();
		toPane.getChildren().clear();
	}
}