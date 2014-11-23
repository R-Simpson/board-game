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
	private Double centroidx;
	private Double centroidy;


	public Land(int id, Color color, Double[] boundaries)
	{
		this.id = id;
		this.polygon.setFill(color);
		this.polygon.setStroke(Color.BLACK);
		this.polygon.getPoints().addAll(boundaries);
			
		int i = 0;
		centroidx = 0.0;
		centroidy = 0.0;
		
		for (Double coord : boundaries)
		{
			if(i % 2 == 0)
			{	centroidx += coord;	}
			else
			{	centroidy += coord;	}
			i++;
		}
		
		
		centroidx = centroidx / ((i + 1)/2);
		centroidy = centroidy / ((i + 1)/2);
		
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
		return centroidx;
	}
	
	public Double getCentroidY()
	{
		return centroidy;
	}
}
