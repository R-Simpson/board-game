package com.websocketgame.model;

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

	private String name;

	public Color defaultColor;

	private Color nextColor = Color.WHITE;

	public Land(String name, Color defaultColor)
	{
		this.name = name;
		this.defaultColor = defaultColor;
		polygon.setFill(defaultColor);
		polygon.setStroke(Color.BLACK);
	}
	
	public String getLandName()
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
				Color tempColor = (Color) polygon.getFill();
				polygon.setFill(nextColor);
				nextColor = tempColor;
			}
		});
	};		// I don't get these brackets

}
