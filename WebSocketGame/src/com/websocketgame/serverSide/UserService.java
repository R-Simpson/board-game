package com.websocketgame.serverSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.websocketgame.shared.Game;
import com.websocketgame.shared.PlayerMessage;


public class UserService implements Runnable {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private UserService[] user =  new UserService[6];
	private int pid;

	public UserService(Socket socket, Game game, UserService[] user, int pid) throws Exception
	{
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		this.user = user;
		this.pid = pid;
	}

	public void run() 
	{
		// WRITE #1
		// Assigning player id
		System.out.println("Writing playerId to client");
		try {
			this.out.writeInt(pid);
			this.out.flush();
		} catch (IOException e1) {
			System.out.println("Failed to write playerId to client");
			e1.printStackTrace();
		}

		// WRITE #2
		// Sending current gamestate
		System.out.println("Writing gameState to client");
		try {
			this.out.writeObject(Game.INSTANCE.getGameState());
			this.out.flush();
		} catch (IOException e1) {
			System.out.println("Failed to write gameState to client");
			e1.printStackTrace();
		}

		// Loop until server shutdown, listen for input from Client, validate and send back updates
		while(true)
		{
			try {

				PlayerMessage message = null;

				Object object = in.readObject();

				if (object instanceof PlayerMessage)
				{
					message = (PlayerMessage)object;
				}

				if (message.getPlayerId() == Game.INSTANCE.getPlayerTurn())
				{
					Game.INSTANCE.updateGameState(message.getPlayerId(), message.getPlayerOrder());
					int playerTurn = Game.INSTANCE.nextPlayerTurn();
					
					for(int i = 0; i < 6; i++)	// replace '6' with player count set by server on start up
					{
						if(user[i] != null)
						{
							// create new message from game state and send back, don't just parrot playerMessage
							user[i].out.writeObject(message);
							user[i].out.flush();
							
							user[i].out.writeObject("GAME: It is now player " + playerTurn + "'s turn");
							
							System.out.println("Updated game value and sent back to client " + i);
						}
					}
				}
				else
				{
					user[message.getPlayerId()].out.writeObject("GAME: It's not your turn yet, waiting for player " + Game.INSTANCE.getPlayerTurn());
				}

			} catch (IOException e) {
				user[pid] = null;
				System.out.println("Player " + pid + " disconnected");
				break;
			} catch (ClassNotFoundException e) {
				System.out.println("Object received was not PlayerMessage");
				break;
			}
		}
	}
}
