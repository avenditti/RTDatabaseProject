package gui;

import java.util.ArrayList;
import java.util.HashMap;

import dbtools.DatabaseTools;
import dbtools.DirectorResult;
import dbtools.Movie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
	private HashMap<Node, Movie> selectedMovies;
	private HashMap<Node, Movie> toPaneSelectedMovies;
	private Stage parentStage;
	private double xOffset = 0;
    private double yOffset = 0;
    private DatabaseTools data;
    private MainGui mainGui;
    private boolean byDirector = false;

	public Recommender(Stage recommenderStage, DatabaseTools data, MainGui mainGui) {
		parentStage = recommenderStage;
		this.data = data;
		selectedMovies = new HashMap<Node, Movie>();
		toPaneSelectedMovies = new HashMap<Node, Movie>();
		this.mainGui = mainGui;
	}

	public void addMovieList(ArrayList<Movie> movies) {
		fromPane.getChildren().clear();
		for(Movie m : movies) {
			addMovieToFromPane(m);
		}
	}

	public void initializeGui() {
		searchField.setPromptText("Enter Movie Name");
		searchButton.setOnAction((obj) -> {
			addMovieList(data.getMovies(searchField.getText()));
		});
		add.setOnAction((obj) -> {
			toPaneSelectedMovies.put(addMovieToToPane(selectedMovies.get(selected)), selectedMovies.get(selected));
		});
		remove.setOnAction((obj) -> {
			toPaneSelectedMovies.remove(selectedTo);
			toPane.getChildren().remove(selectedTo);
		});
		execute.setOnAction((obj) -> {
			if(!byDirector) {
				HashMap<String, Integer> genres = new HashMap<String, Integer>();
				for(Node h : toPane.getChildren()) {
					for(String s : data.getMovieGenres(toPaneSelectedMovies.get(h).getTitle())) {
						genres.put(s, genres.get(s) != null ? genres.get(s)+1 : 1);
					}
				}
				mainGui.infoPane.getChildren().clear();
				for(String s : genres.keySet()) {
					int i = 0;
					mainGui.addMovieToPane(new Movie(-1, s));
					for(Movie m : data.getMoviesByGenre(s, 100)) {
						if(i > 4) {
							break;
						}
						boolean flag = false;
						for(Movie m2 : toPaneSelectedMovies.values()) {
							if(m2.getTitle().equals(m.getTitle())) {
								flag = true;
							}
						}
						if(!flag) {
							mainGui.addMovieToPane(m);
							i++;
						}
					}
				}
				parentStage.hide();
			} else {
				HashMap<String, Integer> directors = new HashMap<String, Integer>();
				for(Node h : toPane.getChildren()) {
					for(DirectorResult d : data.getMovieDirector(toPaneSelectedMovies.get(h).getTitle())) {
						directors.put(d.getDirectorName(), directors.get(d.getDirectorName()) != null ? directors.get(d.getDirectorName())+1 : 1);
					}
				}
				mainGui.infoPane.getChildren().clear();
				for(String s : directors.keySet()) {
					int i = 0;
					mainGui.addMovieToPane(new Movie(-1, s));
					for(Movie m : data.getMoviesByDirector(s)) {
						if(i > 4) {
							break;
						}
						boolean flag = false;
						for(Movie m2 : toPaneSelectedMovies.values()) {
							if(m2.getTitle().equals(m.getTitle())) {
								flag = true;
							}
						}
						if(!flag) {
							mainGui.addMovieToPane(m);
							i++;
						}
					}
				}
				parentStage.hide();
			}
		});
	}

	public HBox addMovieToToPane(Movie m) {
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
		return b;
	}

	public HBox addMovieToFromPane(Movie m) {
		HBox b = new HBox();
		b.setMaxWidth(420);
		b.setSpacing(20);
		b.setPrefHeight(25);
		b.getChildren().add(new Label(m.getTitle()));
		selectedMovies.put(b, m);
		fromPane.getChildren().add(b);
		b.setOnMouseClicked((obj) -> {
			if(selected != null) {
				selected.setStyle("");
			}
			selected = b;
			selected.setStyle("-fx-background-color: #ff0000;");
		});
		return b;
	}

	@FXML
	private void recByDirectors(ActionEvent e) {
		this.byDirector = true;
	}

	@FXML
	private void recByMovies(ActionEvent e) {
		this.byDirector = false;
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
	@FXML
	public void clear() {
		selectedMovies.clear();
		fromPane.getChildren().clear();
		toPane.getChildren().clear();
	}
}