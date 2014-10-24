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

	private int id;
	private Color color;

	public Land(int id, Color color, Double[] boundaries)
	{
		this.id = id;
		this.color = color;
		this.polygon.setFill(color);
		this.polygon.setStroke(Color.BLACK);
		this.polygon.getPoints().addAll(boundaries);
	}
	
	public int getLandId()
	{
		return this.id;
	}
	
	public Polygon getLand()
	{
		return polygon;
	}
	
	{		// I don't get these brackets
		this.polygon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				System.out.println("MOUSE CLICKED ON LAND " + id);
				Color tempColor = (Color) polygon.getFill();
				polygon.setFill(Color.BLACK);
			}
		});
	};		// I don't get these brackets

}
