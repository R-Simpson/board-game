package com.objectstreamgame.scratch;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class GameService implements Runnable {

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Game game;
	private GameService[] user;
	
	public GameService(Socket socket, Game game, GameService[] user) throws Exception
	{
		out = new ObjectOutputStream(socket.getOutputStream());
		
		in = new ObjectInputStream(socket.getInputStream());
		
		this.game = game;
		this.user = user;
		System.out.println("Game Service created");
	}
	
	@Override
	public void run() {
		
		while(true)
		{
			try {
				int message = in.readInt();
				System.out.println("Received value " + message + ", adding to game Value");
				game.add(message);
				
				
				for(int i = 0; i < 6; i++)
				{
					if(user[i] != null)
					{
						user[i].out.writeInt(game.getValue());
						user[i].out.flush();
						System.out.println("Updated game value and sent back to client " + i);
					}
				}
							} catch (IOException e) {
				// do nothing
			} 
		}
	}
}
