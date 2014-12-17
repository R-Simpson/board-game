package com.websocketgame.clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.application.Platform;

import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.PlayerMessage;

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
				
				Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - rund st 'some unspecified time in the future
					@Override
					public void run() {
						Game.INSTANCE.updateGameState(message.getPlayerId(), message.getPlayerOrder());
						client.refreshDisplay();
						
						// yikes at this code duplciation - need to move this loop through lands elsewhere or find another solution
						// also this will likely draw the 'unit' over existing units? Need to clear first?
						// Clearing and rebuilding every time - seems dumb. How to update & refresh?
						// Land 8 is unaffected too - due to counting from '1'?
						/*
						for(Land land: Game.INSTANCE.getGameState()){	
							//client.root.getChildren().clear();
							//client.root.getChildren().add(land.getPolygon());
							
							client.root.getChildren().setAll(land.getPolygon());
							
							if (land.getUnit() != null)
							{
								System.out.println("Adding a unit for land " + land.getLandId());

								client.root.getChildren().add(land.getUnit().getShape());
							}	
						}
						*/
					}
				});				
			}  catch (IOException e) {
				System.out.println("Server disconnected");
				// Need to stop thread / close client
				break;
			} catch (ClassNotFoundException e) {
				System.out.println("Object received was not PlayerMessage");
				break;
			} 		
		}
	}
}

