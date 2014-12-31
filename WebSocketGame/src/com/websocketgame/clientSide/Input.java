package com.websocketgame.clientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javafx.application.Platform;

import com.websocketgame.messaging.ChatMessage;
import com.websocketgame.messaging.GameMessage;
import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;

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

				// can't check for List<Land> - not sending any other lists at the moment though...
				if (object instanceof List<?>) 
				{

					Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - run at 'some unspecified time in the future'
						@Override
						public void run() {
							// Load GameState from Server
							Game.INSTANCE.setGameState((List<Land>) object);
							client.refreshDisplay();
						}
					});			
				}
				// sending gamestate now instead of GameMessage - this shouldn't get called
				else if (object instanceof GameMessage)
				{
					GameMessage message = (GameMessage)object;

					System.out.println("Message received from player : " + message.getPlayerId() + " - Order : " + message.getPlayerOrder());

					Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - run at 'some unspecified time in the future'
						@Override
						public void run() {
							Game.INSTANCE.updateGameState(message.getPlayerId(), message.getUnitMoved(), message.getPlayerOrder());
							client.refreshDisplay();
						}
					});				
				}
				else if (object instanceof ChatMessage)
				{
					ChatMessage message = (ChatMessage)object;
					
					String messageString = message.getPlayerId() + "-" + message.getName() + " : " 
					+ message.getChat();
					
					Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - run at 'some unspecified time in the future'
						@Override
						public void run() {
							client.updateChat(messageString);
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
				System.out.println("Object received was not of expected type");
				break;
			} 		
		}
	}
}

