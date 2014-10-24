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
				new Land(1, Color.GREEN, new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0}), 
				new Land(2, Color.RED, new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 80.0,10.0, 100.0,0.0}), 
				new Land(3, Color.BLUE, new Double[]{100.0,0.0, 80.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0}),
				new Land(4, Color.YELLOW, new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0}), 
				new Land(5, Color.ORANGE, new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0}), 
				new Land(6, Color.PURPLE, new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0})));	
	}
	
	public Land getLand(int landId)
	{
		for(Land land: westeros)
		{
			if(landId == (land.getLandId()))
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


