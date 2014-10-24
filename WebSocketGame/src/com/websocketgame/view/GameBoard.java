package com.websocketgame.view;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import com.websocketgame.model.Continent;

public class GameBoard extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		root.setMinSize(150, 150);
		
		Continent continent = new Continent();
		List<Polygon> polyList = continent.getBoard();	
		root.getChildren().addAll(polyList);

		stage.setScene(new Scene(root));
		stage.show();	
	}

	public static void main(String[] args) {
		launch(args);
	}
}


