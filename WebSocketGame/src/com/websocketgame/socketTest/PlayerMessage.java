package com.websocketgame.socketTest;

import java.io.Serializable;

public class PlayerMessage implements Serializable {

	private static final long serialVersionUID = 0;

	private int playerId;
	private PlayerOrder[] orders;
	private String chat;
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public PlayerOrder[] getOrders() {
		return orders;
	}
	public void setOrders(PlayerOrder[] orders) {
		this.orders = orders;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}

	
}
