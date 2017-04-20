package gui;

import java.io.IOException;
import java.util.ArrayList;

import dbtools.ActorResult;
import dbtools.DatabaseTools;
import dbtools.DirectorResult;
import dbtools.Movie;
import dbtools.TagResult;
import dbtools.UserResult;
import dbtools.UserTimeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainGui {

	@FXML
	VBox infoPane;
	@FXML
	MenuButton searchButton;
	@FXML
	Button buttonRecommend;
	@FXML
	public Pane rootPane;
	@FXML
	ImageView image1;
	@FXML
	ImageView image2;
	@FXML
	TextArea infoBox;
	@FXML
	TextArea tagBox;
	@FXML
	TextArea infoPane1;

	private HBox selected;
	private Stage parentStage;
	private Stage recommenderStage;
	private Recommender r;
	private double xOffset = 0;
    private double yOffset = 0;
    private DatabaseTools data;

	public MainGui(Stage parentStage, DatabaseTools data) throws IOException {
		this.parentStage = parentStage;
		this.data = data;
		recommenderStage = new Stage();
		FXMLLoader fxml = new FXMLLoader(Recommender.class.getResource("Recommender.fxml"));
		r = new Recommender(recommenderStage, data, this);
		fxml.setController(r);
		recommenderStage.initStyle(StageStyle.TRANSPARENT);
		recommenderStage.setScene(new Scene(fxml.load(), Color.TRANSPARENT));
		recommenderStage.initOwner(parentStage);
		r.makeDraggable(r.rootPane);
		r.initializeGui();
	}

	public void initializeGui() {
		loadMovieInfo(data.getMovies("Lord of the Rings").get(0));
		loadActorResultInfoPane1(data.getTop10ActorsByAvgMovieScore(10));
	}

	public void addMovieList(ArrayList<Movie> movies) {
		infoPane.getChildren().clear();
		for(Movie m : movies) {
			addMovieToPane(m);
		}
	}

	public void addMovieToPane(Movie m) {
		if(m.getId() == -1) {
			HBox b = new HBox();
			b.setMaxWidth(420);
			b.setSpacing(20);
			b.setPrefHeight(25);
			b.getChildren().add(new Label(m.getTitle()));
			infoPane.getChildren().add(b);
			b.setStyle("-fx-background-color: #ff00ff;");
		} else {
			HBox b = new HBox();
			b.setMaxWidth(420);
			b.setSpacing(20);
			b.setPrefHeight(25);
			b.getChildren().add(new Label(m.getTitle()));
			infoPane.getChildren().add(b);
			b.setOnMouseClicked((obj) -> {
				if(selected != null) {
					selected.setStyle("");
				}
				selected = b;
				selected.setStyle("-fx-background-color: #ff0000;");
				if(obj.getButton().equals(MouseButton.PRIMARY)){
		            if(obj.getClickCount() == 2){
		            	loadMovieInfo(m);
		            }
		        }
			});
		}
	}

	private void loadMovieInfo(Movie m) {
		infoBox.clear();
		tagBox.clear();
		try {
			image1.setImage(new Image(m.getImdbPictureURL()));
		} catch (Exception e) {
		}
		try {
			image2.setImage(new Image(m.getRtPictureURL()));
		} catch(Exception e) {

		}
    	infoBox.appendText(m.toString());
    	loadMovieTags(data.getMovieTags(m.getTitle()));
	}

	private void loadMovieTags(ArrayList<TagResult> tags) {
		for(TagResult t : tags) {
			tagBox.appendText(t.getTagValue() + "\n");
    	}
	}

	public Stage createPrompt(Control... panes) {
		Stage s = new Stage();
		VBox b = new VBox();
		Pane p = new Pane();
		p.setPrefSize(200, 200);
		b.setPrefSize(200, 200);
		b.setAlignment(Pos.CENTER);
		b.setPadding(new Insets(20,20,20,20));
		s.initOwner(parentStage);
		b.setSpacing(20);
		for (int i = 0; i < panes.length; i++) {
			b.getChildren().add(panes[i]);
		}
		Pane p2 = new Pane(p, b);
		Button close = new Button("Cancel");
		close.setOnAction((obj) -> {
			s.close();
		});
		b.getChildren().add(close);
		makeDraggable(p2,s);
		b.setStyle("-fx-background-color: transparent");
		p2.setStyle("-fx-background-color: transparent");
		p.setStyle("-fx-background-color: black;-fx-background-radius: 20");
		p.setOpacity(.65);
		s.setResizable(false);
		s.initStyle(StageStyle.TRANSPARENT);
		s.initOwner(parentStage);
		s.setScene(new Scene(p2, Color.TRANSPARENT));
		return s;
	}

	@FXML
	private void closeButton(ActionEvent event) {
		parentStage.close();
	}

	@FXML
	private void buttonRecommend(ActionEvent event) {
		r.clear();
		recommenderStage.show();
	}

	private void loadTagResultInfoPane1(ArrayList<TagResult> t) {
		infoPane1.clear();
		for(TagResult tf : t) {
			infoPane1.appendText(String.format("%-40s | Tag Data: %-20s\n", tf.getMovieTitle().length() > 40 ? tf.getMovieTitle().subSequence(0, 37) + "..." : tf.getMovieTitle(), tf.getTagValue()));
		}
	}

	private void loadActorResultInfoPane1(ArrayList<ActorResult> t) {
		infoPane1.clear();
		for(ActorResult tf : t) {
			infoPane1.appendText(String.format("%-20s | Actor AVG Score: %-20s\n", tf.getActorName(), tf.getAvgMovieScore()));
		}
	}

	private void loadDirectorResultInfoPane1(ArrayList<DirectorResult> t) {
		infoPane1.clear();
		for(DirectorResult tf : t) {
			try {
				infoPane1.appendText(String.format("%-20s %-20s \n", tf.getMovieName().length() > 40 ? tf.getMovieName().subSequence(0, 37) + "..." : tf.getMovieName(), tf.getDirectorName()));
			} catch(NullPointerException e) {
				infoPane1.appendText(String.format("%-20s %-20s \n", tf.getDirectorName(), tf.getAvgMovieScore()));
			}
		}
	}

	private void loadUserTimelineInfoPane1(UserTimeline ut) {
		infoPane1.clear();
		int sum = 0;
		for(Integer i : ut.getGenreBreakdown().values()) {
			sum += i;
		}
		for(String s : ut.getGenreBreakdown().keySet()) {
			infoPane1.appendText(String.format("%-10s Total: %4d Total Percentage of Whole: %-5.3f \n", s, ut.getGenreBreakdown().get(s), (double)ut.getGenreBreakdown().get(s) / (double)sum));
		}
		for(UserResult tf : ut.getUserResults()) {
			infoPane1.appendText(String.format("%-5s| %-25s | Score: %-2d | %-2d / %-2d / %-4d | %-2d : %-2d : %-2d\n", tf.getUserName(), tf.getMovieName().length() > 25 ? tf.getMovieName().subSequence(0, 22) + "..." : tf.getMovieName() , tf.getUserRating(), tf.getMonth(), tf.getDay(), tf.getYear(), tf.getHour(), tf.getMinute(), tf.getSecond()));
		}
	}

	@FXML
	private void getUserRatingTimeline(ActionEvent event) {
		TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Enter User Id");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			try {
				loadUserTimelineInfoPane1(data.getTimeline(Integer.parseInt(tf.getText())));
				s.close();
			} catch(Exception e) {
				System.out.println("Invalid Input");
				e.printStackTrace();
			}
		});
		s.show();
	}

	@FXML
	private void getMovieTags(ActionEvent event) {
		TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Enter Movie Name");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			loadTagResultInfoPane1( data.getMovieTags(tf.getText()));
			s.close();
		});
		s.show();
	}

	@FXML
	private void getTop10ActorsByAvgMovieScore(ActionEvent event) {
		TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Enter min movies starred in");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			try {
				loadActorResultInfoPane1(data.getTop10ActorsByAvgMovieScore(Integer.parseInt(tf.getText())));
				s.close();
			} catch(Exception e) {
				System.out.println("Invalid Input");
			}
		});
		s.show();
	}

	@FXML
	private void getTop10DirectorsByAvgMovieScore(ActionEvent event) {
		TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Enter min movies directed");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			try {
				loadDirectorResultInfoPane1(data.getTop10DirectorsByAvgMovieScore(Integer.parseInt(tf.getText())));
				s.close();
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Invalid Input");
			}
		});
		s.show();
	}

	@FXML
	private void getMoviesByTag(ActionEvent event) {
		TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Enter Tag");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			addMovieList(data.getMoviesByTag(tf.getText()));
			s.close();
		});
		s.show();
	}

	@FXML
	private void getTopMovies(ActionEvent event) {
		TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Amount of movies");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			try {
				addMovieList(data.getTopMovies(Integer.parseInt(tf.getText())));
			} catch(Exception e) {
				System.out.println("Invalid Data");
			}
			s.close();
		});
		s.show();
	}

	@FXML
	private void getMovies(ActionEvent event) {
		TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Movie Title");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			addMovieList(data.getMovies(tf.getText()));
			s.close();
		});
		s.show();
	}

	@FXML
	private void getMoviesByGenre(ActionEvent event) {
		TextField tf = new TextField();
		TextField tf1 = new TextField();
		Button b1 = new Button("Enter");
		tf1.setText("Amount of Movies");
		tf.setText("Genre");
		Stage s = createPrompt(tf, tf1, b1);
		b1.setOnAction((obj) -> {
			addMovieList(data.getMoviesByGenre(tf.getText(), Integer.parseInt(tf1.getText())));
			s.close();
		});
		s.show();
	}

	@FXML
	private void getMovieDirector(ActionEvent event) {
		TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Enter Movie Name");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			try {
				loadDirectorResultInfoPane1(data.getMovieDirector(tf.getText()));
				s.close();
			} catch(Exception e) {
				System.out.println("Invalid Input");
			}
		});
		s.show();
	}

	@FXML
	private void getMoviesByDirector(ActionEvent event) {
		TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Director Name");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			infoPane1.clear();
			ArrayList<Movie> movies =  data.getMoviesByDirector(tf.getText());
			addMovieList(movies);
			for(Movie m : movies) {
				infoPane1.appendText(String.format("%-40s %-20s \n",  m.getTitle().length() > 40 ?  m.getTitle().subSequence(0, 37) + "..." : m.getTitle(),  m.getDirectorName()));
			}
			s.close();
		});
		s.show();
	}

	 @FXML
	private void getActorMovies(ActionEvent event) {
		 TextField tf = new TextField();
		Button b1 = new Button("Enter");
		tf.setText("Actor Name");
		Stage s = createPrompt(tf, b1);
		b1.setOnAction((obj) -> {
			ArrayList<ActorResult> actors = data.getActorMovies(tf.getText());
			infoPane1.clear();
			ArrayList<Movie> movies = new ArrayList<Movie>();
			for(ActorResult g : actors) {
				movies.add(g.getMovie());
				infoPane1.appendText(String.format("%-20s | Movie Name: %-20s\n", g.getActorName(), g.getMovie().getTitle() ));
			}
			addMovieList(movies);
			s.close();
		});
		s.show();
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
	public void makeDraggable(Pane p, Stage s) {

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
		    	s.setX(event.getScreenX() - xOffset);
		    	s.setY(event.getScreenY() - yOffset);
		    }
		});
	}

}
