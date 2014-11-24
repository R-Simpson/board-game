package com.websocketgame.serverSide;

import java.io.Serializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Land implements Serializable {
	
	private static final long serialVersionUID = 0;

	private int id;
	private String color;
	private Double[] boundaries;
	private Double[] centroid;
	
	//private Double x;
	//private Double y;
	
	// private Polygon polygon = new Polygon();

	public Land(int id, String color, Double[] boundaries)
	{
		this.id = id;
		this.color = color;
		this.boundaries = boundaries;
		this.centroid = calculateCentroid(boundaries);
		//this.polygon.setFill(color); this.polygon.setStroke(Color.BLACK); this.polygon.getPoints().addAll(boundaries);
	}

	
	public int getLandId()
	{
		return this.id;
	}
	public String getColor()
	{
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Double[] getLandBounds()
	{
		return boundaries;
	}
	public Double[] getCentroid()
	{
		return centroid;
	}
	
	public Double[] calculateCentroid(Double[] boundaries)
	{
		int i = 0;
		Double x = 0.0;
		Double y = 0.0;
		
		for (Double coord : boundaries)
		{
			if(i % 2 == 0)
			{	x += coord;	}
			else
			{	y += coord;	}
			i++;
		}
		
		x = x / ((i + 1)/2);
		y = y / ((i + 1)/2);
		
		return new Double[]{x,y};
	}
}
