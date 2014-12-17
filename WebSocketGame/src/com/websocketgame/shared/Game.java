package com.websocketgame.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public enum Game {
	INSTANCE;

	/*
	private List<Land> gameState  = new ArrayList<Land>(Arrays.asList(
			new Land(0, "GREY", new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0}, new int[]{2,4}), 
			new Land(1, "GREY", new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 90.0,10.0, 100.0,0.0}, new int[]{1,3,4}), 
			new Land(2, "GREY", new Double[]{100.0,0.0, 90.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0}, new int[]{2,5}),
			new Land(3, "GREY", new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0}, new int[]{1,2,5,6}), 
			new Land(4, "GREY", new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0}, new int[]{3,4,6,8}), 
			new Land(5, "GREY", new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0}, new int[]{4,5,7,8}),
			new Land(6, "GREY", new Double[]{0.0,150.0, 80.0,150.0, 90.0,170.0, 70.0,200.0, 0.0, 200.00}, new int[]{6,8}),
			new Land(7, "GREY", new Double[]{100.0,150.0, 150.0,150.0, 150.0,200.0, 70.0,200.0, 90.0,170.00, 80.0,150.0}, new int[]{5,6,7})));
	*/
	
	private List<Land> gameState  = new ArrayList<Land>(Arrays.asList(
			new Land(0, "GREY", new Double[]{680.0,0.0,630.0,60.0,640.0,80.0,590.0,80.0,550.0,130.0,570.0,160.0,560.0,210.0,530.0,260.0,660.0,240.0,720.0,180.0,760.0,170.0,790.0,150.0,850.0,180.0,860.0,150.0,910.0,130.0,960.0,160.0,950.0,70.0,920.0,30.0,920.0,0.0,780.0,0.0}, new int[]{2,4}), 
			new Land(1, "GREY", new Double[]{970.0,160.0,1010.0,170.0,1040.0,170.0,1080.0,190.0,1100.0,180.0,1140.0,180.0,1150.0,210.0,1150.0,260.0,1130.0,310.0,1140.0,340.0,1120.0,340.0,1110.0,310.0,1090.0,300.0,1080.0,270.0,1070.0,310.0,1070.0,360.0,1070.0,400.0,1030.0,400.0,1030.0,410.0,1000.0,410.0,970.0,380.0,960.0,340.0,950.0,290.0,930.0,250.0,890.0,240.0,850.0,180.0,900.0,120.0,950.0,150.0}, new int[]{1,3,4}), 
			new Land(2, "GREY", new Double[]{230.0,240.0,260.0,220.0,300.0,270.0,320.0,230.0,350.0,220.0,380.0,220.0,400.0,240.0,410.0,280.0,430.0,270.0,400.0,220.0,440.0,260.0,460.0,260.0,490.0,250.0,530.0,240.0,600.0,250.0,660.0,240.0,720.0,170.0,750.0,160.0,780.0,150.0,840.0,170.0,890.0,240.0,930.0,250.0,950.0,300.0,970.0,380.0,960.0,400.0,980.0,420.0,960.0,430.0,980.0,440.0,920.0,430.0,900.0,400.0,880.0,400.0,850.0,380.0,770.0,470.0,740.0,470.0,700.0,570.0,710.0,600.0,660.0,660.0,690.0,720.0,600.0,740.0,500.0,750.0,480.0,720.0,400.0,740.0,430.0,700.0,500.0,540.0,450.0,480.0,400.0,470.0,380.0,390.0,340.0,380.0,280.0,400.0,270.0,370.0,230.0,360.0,280.0,350.0,270.0,300.0,280.0,270.0,220.0,230.0}, new int[]{2,5}),
			new Land(3, "GREY", new Double[]{140.0,630.0,150.0,480.0,170.0,450.0,210.0,450.0,230.0,460.0,260.0,470.0,260.0,440.0,250.0,440.0,260.0,420.0,260.0,410.0,240.0,400.0,280.0,400.0,350.0,380.0,380.0,400.0,400.0,470.0,440.0,480.0,460.0,500.0,490.0,530.0,500.0,540.0,450.0,650.0,420.0,690.0,430.0,700.0,410.0,710.0,380.0,750.0,340.0,740.0,350.0,700.0,320.0,740.0,280.0,760.0,240.0,760.0,240.0,730.0,210.0,710.0,210.0,670.0,190.0,610.0,200.0,580.0,190.0,580.0,180.0,600.0,140.0,630.0}, new int[]{1,2,5,6}), 
			new Land(4, "GREY", new Double[]{1010.0,500.0,1000.0,470.0,960.0,460.0,960.0,430.0,920.0,430.0,890.0,400.0,840.0,370.0,760.0,470.0,730.0,470.0,690.0,580.0,700.0,610.0,650.0,660.0,690.0,720.0,740.0,690.0,740.0,680.0,760.0,710.0,770.0,750.0,760.0,790.0,790.0,830.0,830.0,840.0,860.0,810.0,880.0,770.0,910.0,770.0,940.0,710.0,930.0,700.0,890.0,700.0,890.0,670.0,840.0,650.0,850.0,620.0,830.0,610.0,860.0,580.0,870.0,550.0,890.0,530.0,920.0,500.0,980.0,510.0,1010.0,500.0}, new int[]{3,4,6,8}), 
			new Land(5, "GREY", new Double[]{1080.0,650.0,1050.0,660.0,940.0,620.0,950.0,660.0,930.0,700.0,880.0,700.0,880.0,680.0,830.0,650.0,860.0,580.0,880.0,530.0,920.0,490.0,970.0,520.0,1000.0,540.0,1030.0,570.0,1030.0,590.0,1020.0,620.0,1040.0,630.0,1080.0,650.0}, new int[]{4,5,7,8})));
	
	
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








