package com.websocketgame.messaging;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	private int playerId;
	private String name;
	private String chat;
	
	public ChatMessage(int playerId, String name, String chat)
	{
		this.playerId = playerId;
		this.setName(name);
		this.setChat(chat);
	}
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

}
