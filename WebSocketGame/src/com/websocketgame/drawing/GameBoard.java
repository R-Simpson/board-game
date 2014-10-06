package com.websocketgame.drawing;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class GameBoard extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();

		root.setMinSize(150, 150);

		Land l1 = new Land("Land 1", Color.GREEN);
		Land l2 = new Land("Land 2", Color.RED);
		Land l3 = new Land("Land 3", Color.BLUE);
		Land l4 = new Land("Land 4", Color.YELLOW);
		Land l5 = new Land("Land 5", Color.ORANGE);
		Land l6 = new Land("Land 6", Color.PURPLE);

		l1.polygon.getPoints().addAll(new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0});
		l2.polygon.getPoints().addAll(new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 80.0,10.0, 100.0,0.0});
		l3.polygon.getPoints().addAll(new Double[]{100.0,0.0, 80.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0});
		l4.polygon.getPoints().addAll(new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0});
		l5.polygon.getPoints().addAll(new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0});
		l6.polygon.getPoints().addAll(new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0});

		root.getChildren().addAll(l1.polygon,l2.polygon,l3.polygon,l4.polygon,l5.polygon,l6.polygon);

		stage.setScene(new Scene(root));
		stage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}


