package com.websocketgame.messaging;
// eventually have a separate serverMessage & a clientMessage rather than a shared message

import java.io.Serializable;

public class PlayerMessage implements Serializable {
	

	private static final long serialVersionUID = 0;
	
	private int playerId;
	private int playerOrder;
	
	public PlayerMessage(int playerId, int playerOrder)
	{
		this.playerId = playerId;
		this.playerOrder = playerOrder;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	public int getPlayerOrder() {
		return playerOrder;
	}
	public void setPlayerOrder(int playerOrder) {
		this.playerOrder = playerOrder;
	}
	
}
