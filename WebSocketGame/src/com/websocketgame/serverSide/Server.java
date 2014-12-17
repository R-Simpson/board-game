package com.websocketgame.serverSide;

import java.net.ServerSocket;
import java.net.Socket;

import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.Unit;


public class Server {

	static ServerSocket serverSocket;
	static UserService[] user = new UserService[6];
	// static Game game;

	
	public static void main(String[] args) throws Exception
	{
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		
		// set starting units - move this to it's own method or class
		for(Land land: Game.INSTANCE.getGameState())
		{
			if (land.getLandId() == 0)
			{
				land.addUnit(new Unit(1,1, land));
				System.out.println("Adding unit 1,1 to land 1");	
			}
			if (land.getLandId() == 7)
			{
				land.addUnit(new Unit(2,2, land));
				System.out.println("Adding unit 2,2 to land 8");
			}
		}
			
		
		while(true)
		{
			Socket socket = serverSocket.accept();
			for (int i = 0; i < 6; i++)
			{
				if(user[i] == null)
				{
					System.out.println("Connection from " + socket.getInetAddress());
					user[i] = new UserService(socket, Game.INSTANCE, user, i);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			}
		}
	}
}

