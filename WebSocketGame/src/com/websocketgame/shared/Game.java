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
			new Land(1, "GREY", new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0}), 
			new Land(2, "GREY", new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 80.0,10.0, 100.0,0.0}), 
			new Land(3, "GREY", new Double[]{100.0,0.0, 80.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0}),
			new Land(4, "GREY", new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0}), 
			new Land(5, "GREY", new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0}), 
			new Land(6, "GREY", new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0})));	

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
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 1:
					land.setColor("BLUE");
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 2:
					land.setColor("YELLOW");
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 3:
					land.setColor("GREEN");
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 4:
					land.setColor("ORANGE");
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 5:
					land.setColor("PURPLE");
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				}
		}	
	}
}








