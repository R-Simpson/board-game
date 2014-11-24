package com.websocketgame.serverSide;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Land {

	private int id;
	private Polygon polygon = new Polygon();
	private Double centroidX;
	private Double centroidY;


	public Land(int id, Color color, Double[] boundaries)
	{
		this.id = id;
		this.polygon.setFill(color);
		this.polygon.setStroke(Color.BLACK);
		this.polygon.getPoints().addAll(boundaries);
			
		int i = 0;
		centroidX = 0.0;
		centroidY = 0.0;
		
		for (Double coord : boundaries)
		{
			if(i % 2 == 0)
			{	centroidX += coord;	}
			else
			{	centroidY += coord;	}
			i++;
		}
		
		
		centroidX = centroidX / ((i + 1)/2);
		centroidY = centroidY / ((i + 1)/2);
		
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
	
	public Double getCentroidX()
	{
		return centroidX;
	}
	
	public Double getCentroidY()
	{
		return centroidY;
	}
}
