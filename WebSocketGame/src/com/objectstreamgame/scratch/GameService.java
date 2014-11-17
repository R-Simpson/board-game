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
	
	public GameService(Socket socket, Game game) throws Exception
	{
		out = new ObjectOutputStream(socket.getOutputStream());
		
		in = new ObjectInputStream(socket.getInputStream());
		
		this.game = game;
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
				out.writeInt(game.getValue());
				out.flush();
				System.out.println("Updated game value and sent back to client");
			} catch (IOException e) {
				// do nothing
			} 
		}
	}
}
