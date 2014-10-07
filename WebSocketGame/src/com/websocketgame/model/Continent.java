package com.websocketgame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Continent 
{
	List<Land> westeros;
	
	public Continent()
	{
		westeros = new ArrayList<Land>(Arrays.asList(
				new Land("L1", Color.GREEN), 
				new Land("L2", Color.RED), 
				new Land("L3", Color.BLUE),
				new Land("L4", Color.YELLOW), 
				new Land("L5", Color.ORANGE), 
				new Land("L6", Color.PURPLE)));	
		
		getLand("L1").polygon.getPoints().addAll(new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0});
		getLand("L2").polygon.getPoints().addAll(new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 80.0,10.0, 100.0,0.0});
		getLand("L3").polygon.getPoints().addAll(new Double[]{100.0,0.0, 80.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0});
		getLand("L4").polygon.getPoints().addAll(new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0});
		getLand("L5").polygon.getPoints().addAll(new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0});
		getLand("L6").polygon.getPoints().addAll(new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0});
	}
	
	public Land getLand(String landName)
	{
		for(Land land: westeros)
		{
			if(landName.equals(land.getLandName()))
			{
				return land;
			}
		}
		return null;
	}
	
	public List<Polygon> getContinent()
	{
		List<Polygon> polyList = new ArrayList<Polygon>();
		for(Land land: westeros)
		{
			polyList.add(land.getLand());
		}
		return polyList;
	}
}


