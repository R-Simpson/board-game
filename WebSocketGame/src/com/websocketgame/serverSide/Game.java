package com.websocketgame.serverSide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public enum Game {
    INSTANCE;
    
    private List<Land> gameState  = new ArrayList<Land>(Arrays.asList(
			new Land(1, Color.GREY, new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0}), 
			new Land(2, Color.GREY, new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 80.0,10.0, 100.0,0.0}), 
			new Land(3, Color.GREY, new Double[]{100.0,0.0, 80.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0}),
			new Land(4, Color.GREY, new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0}), 
			new Land(5, Color.GREY, new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0}), 
			new Land(6, Color.GREY, new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0})));	
 
    public List<Land> getGameState()
    {
    	return gameState;
    }
    
    public List<Polygon> getGameBoard()
    {
    	List<Polygon> gameBoard = new ArrayList<Polygon>();
    	for(Land land: gameState)
		{	
    		gameBoard.add(land.getLand());
		}
    	return gameBoard;
    }

	public void updateBoard(int pid, int area)
	{
		Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - this does that at 'some unspecified time in the future
			@Override
			public void run() {
				for(Land land : gameState){
					if(land.getLandId() ==  area)
						switch (pid){
						case 0:
							land.setColor(Color.RED);
							System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
							break;
						case 1:
							land.setColor(Color.BLUE);
							System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
							break;
						case 2:
							land.setColor(Color.YELLOW);
							System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
							break;
						case 3:
							land.setColor(Color.GREEN);
							System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
							break;
						case 4:
							land.setColor(Color.ORANGE);
							System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
							break;
						case 5:
							land.setColor(Color.PURPLE);
							System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
							break;
						}
				}	
			}			
		});
	}
}


	


	
	

