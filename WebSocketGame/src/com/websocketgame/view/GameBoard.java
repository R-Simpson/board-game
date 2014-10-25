package com.websocketgame.view;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import com.websocketgame.model.Land;

public class GameBoard extends Application {
	
	List<Land> board;

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		root.setMinSize(150, 150);
		
		board = new ArrayList<Land>(Arrays.asList(
				new Land(1, Color.GREEN, new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0}), 
				new Land(2, Color.BROWN, new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 80.0,10.0, 100.0,0.0}), 
				new Land(3, Color.BLUE, new Double[]{100.0,0.0, 80.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0}),
				new Land(4, Color.YELLOW, new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0}), 
				new Land(5, Color.ORANGE, new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0}), 
				new Land(6, Color.PURPLE, new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0})));	

		List<Polygon> polyList = new ArrayList<Polygon>();
		
		for(Land land: board)
		{
			polyList.add(land.getLand());
			
			land.polygon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					Color tempColor = (Color) land.polygon.getFill();
					land.polygon.setFill(Color.RED);
					System.out.println("Land " + land.getLandId() + " claimed");
				}
			});
		}

		root.getChildren().addAll(polyList);
		
		stage.setScene(new Scene(root));
		stage.show();	
	}

	public static void main(String[] args) {
		launch(args);
	}
}


