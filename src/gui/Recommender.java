package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Recommender {

	@FXML
	Pane rootPane;

	private Stage parentStage;
	private GridPane fromPane;
	private GridPane toPane;
	private double xOffset = 0;
    private double yOffset = 0;

	public Recommender(Stage recommenderStage) {
		parentStage = recommenderStage;
	}

	@FXML
	private void closeButton(ActionEvent e) {
		parentStage.hide();
	}

	@FXML
	private void addMovie(ActionEvent e) {

	}

	@FXML
	private void removeMovie(ActionEvent e) {

	}
	public void makeDragable(Pane p) {
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
