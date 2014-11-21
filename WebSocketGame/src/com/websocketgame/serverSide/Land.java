package com.websocketgame.serverSide;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Land {

	private int id;
	private Polygon polygon = new Polygon();


	public Land(int id, Color color, Double[] boundaries)
	{
		this.id = id;
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

	public void setColor(Color color) {
		this.polygon.setFill(color);
	}
	
}
