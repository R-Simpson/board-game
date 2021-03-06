package com.websocketgame.shared;
// eventually have a separate serverMessage & a clientMessage rather than a shared message

import java.io.Serializable;

public class PlayerMessage implements Serializable {
	

	private static final long serialVersionUID = 0;
	
	private int playerId;
	private Unit unit;
	private int playerOrder;
	
	public PlayerMessage(int playerId, Unit unit, int playerOrder)
	{
		this.playerId = playerId;
		this.unit = unit;
		this.playerOrder = playerOrder;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public Unit getUnitMoved() {
		return unit;
	}
	
	public void setUnitMoved(Unit unit) {
		this.unit = unit;
	}
	
	public int getPlayerOrder() {
		return playerOrder;
	}
	public void setPlayerOrder(int playerOrder) {
		this.playerOrder = playerOrder;
	}
	
}
