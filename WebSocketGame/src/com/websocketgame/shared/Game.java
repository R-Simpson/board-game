package com.websocketgame.shared;

import java.util.List;


public enum Game {
	INSTANCE;

	private List<Land> gameState =  Board.INSTANCE.getGameBoard();
	
	private int playerTurn = 1;
	
	private int players = 6; // entered by server on start up
	
	public int getPlayerTurn()
	{
		return playerTurn;
	}
	
	public int nextPlayerTurn()
	{
		playerTurn = (int) (((playerTurn+1)>players) ?  1 : playerTurn+1); 
		// add player 'active' boolean to skip players who have been removed from game
		return playerTurn;
	}
		
	public List<Land> getGameState()
	{
		return gameState;
	}

	public void setGameState(List<Land> gameState)
	{
		this.gameState = gameState;
	}

	public void updateGameState(int pid, Unit unit, int area)
	{
		
		int unitOrigin = unit.getLand().getLandId();	// need to do by id, not obj reference, will be different from client to server
		for(Land land : gameState){
			if(land.getLandId() ==  area)
			{	
				land.setOwner(pid);
				land.addUnit(new Unit(pid,unit.getType(),land));

				System.out.println("Adding unit to land " + land.getLandId() + " owned by player " + pid + " of type " + unit.getType());
				System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
			}
			if(land.getLandId() == unitOrigin)
			{
				land.removeUnit(unit);
				System.out.println("Removing unit from land " + unitOrigin + " unit is null? " + (null==land.getUnit()));	
			}
		}	
	}
}








