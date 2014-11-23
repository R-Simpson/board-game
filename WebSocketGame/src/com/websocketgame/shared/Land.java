package com.websocketgame.shared;

import java.io.Serializable;

import javafx.scene.paint.Color;

public class Land implements Serializable {

	private static final long serialVersionUID = 0;
	
	private int id;
	private Double[] boundaries;
	private Color color;
	
	//private Polygon polygon = new Polygon();


	public Land(int id, Color color, Double[] boundaries)
	{
		this.id = id;
		this.color = color;
		this.boundaries = boundaries;
		
		//this.polygon.setFill(color);
		//this.polygon.setStroke(Color.BLACK);
		//this.polygon.getPoints().addAll(boundaries);
	}
	
	public int getLandId()
	{
		return this.id;
	}
	
	public Double[] getLandBounds()
	{
		return boundaries;
	}
	
	public Color getColor()
	{
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
