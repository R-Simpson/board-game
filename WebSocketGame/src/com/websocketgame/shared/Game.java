package com.websocketgame.shared;

import java.util.List;
import java.util.Random;


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
				boolean victory;
				if (land.getUnit() != null)
				{
					victory = resolveCombatStub(unit, land);
				}
				else
				{
					victory = true;	// no occupying (enemy?) units
				}
				if (victory)
				{
					land.setOwner(pid);
					land.addUnit(new Unit(pid,unit.getType(),land));
					System.out.println("Victory. Adding unit to land " + land.getLandId() + " owned by player " + pid + " of type " + unit.getType());
					System.out.println("Land " + land.getLandId() + " claimed by player " + pid);
				}
				else
				{
					System.out.println("Defeat, attacking unit lost");
				}
			}
			if(land.getLandId() == unitOrigin)
			{
				land.removeUnit(unit);
				System.out.println("Removing unit from land " + unitOrigin + " unit is null? " + (null==land.getUnit()));	
			}
		}	
	}

	private boolean resolveCombatStub(Unit unit, Land land)
	{
		// Stub in place of real mechanics, a footman has a strength of '1', a knight '2'
		// Multiply a 1-10 diceroll by the unit strength, draws go to defender

		Unit attacker = unit;
		Unit defender = land.getUnit();

		Random random = new Random();

		int attackDiceRoll = random.nextInt(10)+1;
		attackDiceRoll = attackDiceRoll * attacker.getType();

		int defendDiceRoll = random.nextInt(10)+1;
		defendDiceRoll = defendDiceRoll * defender.getType();

		if (attackDiceRoll > defendDiceRoll)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
    protected Object readResolve() {
        return INSTANCE;
    }

}








