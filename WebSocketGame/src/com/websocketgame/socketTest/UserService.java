package com.websocketgame.socketTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class UserService implements Runnable {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private GameState game;
	private UserService[] user =  new UserService[6];
	private int pid;

	public UserService(Socket socket, GameState game, UserService[] user, int pid) throws Exception
	{
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		this.game = game;
		this.user = user;
		this.pid = pid;

	}

	public void run() 
	{
		System.out.println("Writing playerId to client");
		try {
			this.out.writeInt(pid);
			this.out.flush();
		} catch (IOException e1) {
			System.out.println("Failed to write playerId to client");
			e1.printStackTrace();
		}

		while(true)
		{
			try {
				PlayerMessage message = (PlayerMessage) in.readObject();
				// validate & alter game state
				for(int i = 0; i < 6; i++)
				{
					if(user[i] != null)
					{
						// create new message from game state and send back, don't just parrot playerMessage
						user[i].out.writeObject(message);
						user[i].out.flush();
						System.out.println("Updated game value and sent back to client " + i);
					}
				}
			} catch (IOException e) {
				user[pid] = null;
			} catch (ClassNotFoundException e) {
				System.out.println("Wrong class received");
			}
		}
	}
}
