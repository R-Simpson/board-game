package com.websocketgame.shared;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;

public class Land implements Serializable {
	
	private static final long serialVersionUID = 0;

	private int id;
	private int owner;
	//private String color;
	private Double[] boundaries;
	private Double[] centroid;
	private int[] adjacentLands;
	private boolean castle;
	private boolean fort;
	private int crowns;
	private int supply;	
	private Unit unit;
	// private ArrayList units;
	private transient Polygon polygon = new Polygon();
	
	public Land(int id, Double[] boundaries, int[] adjacentLands, boolean castle, boolean fort, int crowns, int supply)
	{
		this.id = id;
		this.owner = 0;
//		this.color = "WHITE";
		this.boundaries = boundaries;
		this.centroid = calculateCentroid(boundaries);
		this.adjacentLands = adjacentLands;	
		this.castle = castle;
		this.fort = fort;
		this.crowns = crowns;
		this.supply = supply;
		this.unit = null;
		//this.units = new ArrayList<Unit>();
		this.polygon = setUpPolygon(getColor(), boundaries);
	}
	
	// Constructor for custom centroids
	public Land(int id, Double[] boundaries, int[] adjacentLands, Double[] centroid, boolean castle, boolean fort, int crowns, int supply)
	{
		this.id = id;
		this.owner = 0;
//		this.color = "WHITE";
		this.boundaries = boundaries;
		this.unit = null;
		//this.units = new ArrayList<Unit>();
		this.centroid = centroid;
		this.adjacentLands = adjacentLands;
		this.polygon = setUpPolygon(getColor(), boundaries);
	}
	
	public int getLandId()
	{
		return this.id;
	}
	
	public int getOwner()
	{
		return this.owner;
	}
	
	public void setOwner(int owner)
	{
		this.owner = owner;
		switch (owner){
		case 0: setColor("WHITE");		break;
		case 1:	setColor("BLUE"); 		break;
		case 2:	setColor("RED");		break;
		case 3:	setColor("YELLOW");		break;
		case 4:	setColor("BLACK");		break;
		case 5:	setColor("GREEN");		break;
		case 6:	setColor("DARKORANGE");	break;
		}
	}
	
	public void setColor(String color) {
		//this.color = color;
		this.polygon.setFill(Color.valueOf(color).deriveColor(1, 1, 1, 0.2));
	}
	
	public String getColor()
	{
		switch (owner){
		case 0: return("WHITE");
		case 1:	return("BLUE"); 
		case 2:	return("RED");
		case 3:	return("YELLOW");
		case 4:	return("BLACK");
		case 5:	return("GREEN");
		case 6:	return("DARKORANGE");
		}
		return("WHITE");
	}
	
	public Double[] getLandBounds()
	{
		return boundaries;
	}
	
	public Double[] getCentroid()
	{
		return centroid;
	}
	
	public int[] getAdjacency()
	{
		return adjacentLands;
	}
	
	public boolean hasCastle() {
		return castle;
	}
	public boolean hasFort() {
		return fort;
	}
	public int getCrowns() {
		return crowns;
	}
	public int getSupply() {
		return supply;
	}
	
	
	public Polygon getPolygon()
	{
		return polygon;
	}
	
	public void setPolygon(Polygon polygon)	// unneeded? Update method to take colour, owner, units?
	{
		this.polygon = polygon;
	}
	
	public Unit getUnit()
	{
		return unit;
	}
	
	public void addUnit(Unit unit)
	{
		//units.add(unit);
		this.unit = unit;
	}
	
	public void removeUnit(Unit unit)
	{
		this.unit = null;
		//units.remove(unit);
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
		tempPoly.setFill(Color.valueOf(color).deriveColor(1, 1, 1, 0.2));		
		tempPoly.setStroke(Color.WHITE);
		tempPoly.setStrokeWidth(7);
		tempPoly.getPoints().addAll(boundaries);
		return tempPoly;
	}
	
    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
    	stream.writeInt(id);
    	stream.writeInt(owner);
//    	stream.writeObject(color);
    	stream.writeObject(boundaries);
    	stream.writeObject(centroid);
    	stream.writeObject(adjacentLands);
    	stream.writeObject(unit);
//    	System.out.println("Writing land " + id + ", color " + color);
    }
	
    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
		// how does default readObject work? Does it call constructor? 
    	this.id = stream.readInt();
    	this.owner = stream.readInt();
//    	this.color = (String) stream.readObject();
    	this.boundaries = (Double[]) stream.readObject();
		this.centroid = (Double[]) stream.readObject();
		this.adjacentLands = (int[]) stream.readObject();
		this.unit = (Unit) stream.readObject();
		this.polygon = setUpPolygon(getColor(), boundaries); // set up Polygon (transient) from boundaries & colour
//		System.out.println("Reading land " + id + ", color " + color);
	}

}
