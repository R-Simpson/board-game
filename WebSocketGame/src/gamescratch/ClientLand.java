package gamescratch;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class ClientLand {

	public Polygon polygon = new Polygon();
	private int name;
	private Color defaultColor;



	public ClientLand(int name, Color defaultColor)
	{
		this.name = name;
		this.defaultColor = defaultColor;
		polygon.setFill(defaultColor);
		polygon.setStroke(Color.BLACK);
	}
	
	public int getLandName()
	{
		return this.name;
	}
	
	public Polygon getLand()
	{
		return polygon;
	}
	
	{		// I don't get these brackets
		this.polygon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("MOUSE CLICKED ON " + name);
				polygon.setFill(Color.BLACK);

			}
		});
	};		// I don't get these brackets

}
