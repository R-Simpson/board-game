package com.websocketgame.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public enum Game {
	INSTANCE;

	private List<Land> gameState  = new ArrayList<Land>(Arrays.asList(
			new Land(0, "GREY", new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0}, new int[]{2,4}), 
			new Land(1, "GREY", new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 90.0,10.0, 100.0,0.0}, new int[]{1,3,4}), 
			new Land(2, "GREY", new Double[]{100.0,0.0, 90.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0}, new int[]{2,5}),
			new Land(3, "GREY", new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0}, new int[]{1,2,5,6}), 
			new Land(4, "GREY", new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0}, new int[]{3,4,6,8}), 
			new Land(5, "GREY", new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0}, new int[]{4,5,7,8}),
			new Land(6, "GREY", new Double[]{0.0,150.0, 80.0,150.0, 90.0,170.0, 70.0,200.0, 0.0, 200.00}, new int[]{6,8}),
			new Land(7, "GREY", new Double[]{100.0,150.0, 150.0,150.0, 150.0,200.0, 70.0,200.0, 90.0,170.00, 80.0,150.0}, new int[]{5,6,7})));
	
	public List<Land> getGameState()
	{
		return gameState;
	}

	public void setGameState(List<Land> gameState)
	{
		this.gameState = gameState;
	}

	public void updateGameState(int pid, int area)
	{
		for(Land land : gameState){
			if(land.getLandId() ==  area)
				switch (pid){
				case 0:
					land.setColor("RED");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 1:
					land.setColor("BLUE");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 2:
					land.setColor("YELLOW");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 3:
					land.setColor("GREEN");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 4:
					land.setColor("ORANGE");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 5:
					land.setColor("PURPLE");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit 1,1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				}

		}	
	}
}








