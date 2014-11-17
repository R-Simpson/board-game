package com.objectstreamgame.scratch;
import java.io.*;
import java.net.*;


public class GameServer {

	static ServerSocket serverSocket;

	// static Users[] user = new Users[6];
	

	
	public static void main(String[] args) throws Exception
	{
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		Game game = new Game(0);
		
		while(true)
		{
			for (int i = 0; i < 6; i++)
			{
				Socket socket = serverSocket.accept();
				System.out.println("Connection from " + socket.getInetAddress());
				GameService gameService  = new GameService(socket, game);
				Thread thread = new Thread(gameService);
				thread.start();
			}
		}
	}
}

