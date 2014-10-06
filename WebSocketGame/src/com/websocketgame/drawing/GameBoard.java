package com.websocketgame.drawing;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


public class GameBoard extends Application
{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Drawing Operations Test");
		Group root = new Group();
		Canvas canvas = new Canvas(150, 150);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		drawShapes(gc);
		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				
			}
		});
	}
	

	
	
	private void drawShapes(GraphicsContext gc) 
	{
		gc.setLineWidth(2);

		gc.strokePolygon(new double[]{0, 50, 40, 50, 0  }, 	 
						 new double[]{0, 0,  20, 40, 50}, 5); 
		gc.strokePolygon(new double[]{50, 40, 50, 100, 90, 100, 80, 100,},  
						 new double[]{0,  20, 40, 50,  40, 20,  10, 0}, 8); 
		gc.strokePolygon(new double[]{100, 80, 100, 90, 100, 150, 150}, 
						 new double[]{0,   10, 20,  40, 50,  70, 0}, 7);
		gc.strokePolygon(new double[]{0,  50, 100, 90,  0  },     
						 new double[]{50, 40, 50,  100, 100}, 5); 
		gc.strokePolygon(new double[]{100, 150, 150, 120, 90 },     
						 new double[]{50,  70,  150, 150, 100}, 5); 
		gc.strokePolygon(new double[]{0,   90,  120, 0  },     
						 new double[]{100, 100, 150, 150}, 4); 
	}
}
