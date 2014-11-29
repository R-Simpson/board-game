package com.websocketgame.shared;

import java.io.IOException;
import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Land implements Serializable {
	
	private static final long serialVersionUID = 0;

	private int id;
	private String color;
	private Double[] boundaries;
	private Double[] centroid;
	private transient Polygon polygon = new Polygon();

	public Land(int id, String color, Double[] boundaries)
	{
		this.id = id;
		this.color = color;
		this.boundaries = boundaries;
		this.centroid = calculateCentroid(boundaries);
		this.polygon = setUpPolygon(color, boundaries);
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
		this.polygon.setFill(Color.valueOf(color));
	}
	
	public Double[] getLandBounds()
	{
		return boundaries;
	}
	
	public Double[] getCentroid()
	{
		return centroid;
	}
	
	public Polygon getPolygon()
	{
		return polygon;
	}
	
	public void setPolygon(Polygon polygon)	// unneeded? Update method to take colour, owner, units?
	{
		this.polygon = polygon;
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
	
	private Polygon setUpPolygon(String color, Double[] boundaries)
	{
		Polygon tempPoly = new Polygon();
		tempPoly.setFill(Color.valueOf(color)); 
		tempPoly.setStroke(Color.BLACK); 
		tempPoly.getPoints().addAll(boundaries);
		return tempPoly;
	}
	
    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
    	stream.writeInt(id);
    	stream.writeObject(color);
    	stream.writeObject(boundaries);
    	stream.writeObject(centroid);
    	System.out.println("Writing land " + id + ", color " + color);
    }
	
    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		// how does default readObject work? Does it call constructor? 
    	this.id = stream.readInt();
    	this.color = (String) stream.readObject();
    	this.boundaries = (Double[]) stream.readObject();
		this.centroid = (Double[]) stream.readObject();
		this.polygon = setUpPolygon(color, boundaries); // set up Polygon (transient) from boundaries & colour
		System.out.println("Reading land " + id + ", color " + color);
		
	}
}
