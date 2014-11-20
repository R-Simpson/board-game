package com.websocketgame.socketTest;

import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	static ServerSocket serverSocket;
	static UserService[] user = new UserService[6];
	static GameState game = new GameState();

	
	public static void main(String[] args) throws Exception
	{
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		
		while(true)
		{
			Socket socket = serverSocket.accept();
			for (int i = 0; i < 6; i++)
			{
				if(user[i] == null)
				{
					System.out.println("Connection from " + socket.getInetAddress());
					user[i] = new UserService(socket, game, user, i);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			}
		}
	}
}

