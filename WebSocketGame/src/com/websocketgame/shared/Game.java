package com.websocketgame.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public enum Game {
	INSTANCE;

	private List<Land> gameState =  Board.INSTANCE.getGameBoard();
	
	private int playerTurn = 0;
	
	private int players = 6; // entered by server on start up
	
	public int getPlayerTurn()
	{
		return playerTurn;
	}
	
	public int nextPlayerTurn()
	{
		playerTurn = (int) (((playerTurn+1)==players) ?  0 : playerTurn+1); 
		// add player 'active' boolean to skip players who have been removed from game
		return playerTurn;
	}
	
	
	public static int switchPlayer(int currentPlayer, boolean[] playerList) {
	    // if the current player + 1 = length (size) of array,
	    // start back at the beginning and find the first player still playing
	    if(currentPlayer + 1 == playerList.length) {
	        for(int i = 0; i < playerList.length; i++) {
	            if(playerList[i] == true) {    // if player is still in the game
	                currentPlayer = i;         // currentPlayer = current index of array
	                break;
	            }
	        }
	    }
	    // otherwise the current player number + 1 is not at the end of the array
	    // i.e. it is less than the length (size) of the array, so find the next player
	    // still playing
	    else {
	        for(int i = (currentPlayer+1); i < playerList.length; i++) {
	            if(playerList[i] == true) {
	                currentPlayer = i;
	                break;
	            }
	        }
	    }
	    return currentPlayer;
	}
	
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
					System.out.println("Added unit " + pid + ",1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 1:
					land.setColor("BLUE");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit " + pid + ",1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 2:
					land.setColor("YELLOW");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit " + pid + ",1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 3:
					land.setColor("GREEN");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit " + pid + ",1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 4:
					land.setColor("BLACK");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit " + pid + ",1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				case 5:
					land.setColor("PURPLE");
					land.addUnit(new Unit(pid,1,land));
					System.out.println("Added unit " + pid + ",1 to land " + land.getLandId());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
					break;
				}

		}	
	}
}








