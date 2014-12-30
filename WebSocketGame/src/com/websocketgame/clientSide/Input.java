package com.websocketgame.clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.application.Platform;

import com.websocketgame.shared.Game;
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

			try {			

				Object object = in.readObject();

				if (object instanceof PlayerMessage)
				{
					PlayerMessage message = (PlayerMessage)object;

					System.out.println("Message received from player : " + message.getPlayerId() + " - Order : " + message.getPlayerOrder());

					Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - run at 'some unspecified time in the future'
						@Override
						public void run() {
							Game.INSTANCE.updateGameState(message.getPlayerId(), message.getUnitMoved(), message.getPlayerOrder());
							client.refreshDisplay();
						}
					});				
				}
				
				else if (object instanceof String)
				{
					String string = (String)object;
					
					Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - run at 'some unspecified time in the future'
						@Override
						public void run() {
							client.updateChat(string);
						}
					});		

					System.out.println(object);
				}
				
				
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

