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

public class Land {

	public Polygon polygon = new Polygon();

	public String name;

	public Color defaultColor;

	private Color nextColor = Color.BLACK;

	public Land(String name, Color defaultColor)
	{
		this.name = name;
		this.defaultColor = defaultColor;
		polygon.setFill(defaultColor);
	}

	{
		this.polygon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("MOUSE CLICKED ON " + name);
				Color tempColor = (Color) polygon.getFill();
				polygon.setFill(nextColor);
				nextColor = tempColor;
			}
		});
	};

}
