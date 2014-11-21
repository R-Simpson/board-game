package com.websocketgame.clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;

import com.websocketgame.messaging.PlayerMessage;
import com.websocketgame.serverSide.Game;

public class Input implements Runnable{

	ObjectInputStream in;
	Client client;

	public Input(ObjectInputStream in, Client client){
		this.in = in;
		this.client = client;
	}

	public void run() {
		while(true){
			PlayerMessage message;
			try {
				
				message = (PlayerMessage) in.readObject();
				
				System.out.println("Message received from player : " + message.getPlayerId() + " - Order : " + message.getPlayerOrder());
				
				Game.INSTANCE.updateBoard(message.getPlayerId(), message.getPlayerOrder());
				
				// client.board = GameState.INSTANCE.getGameState();
				
				// client.updateBoard(message.getPlayerId(), message.getPlayerOrder());
				
			}  catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 		
		}
	}
}
