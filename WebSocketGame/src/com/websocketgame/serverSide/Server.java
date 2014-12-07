package com.websocketgame.serverSide;

import java.net.ServerSocket;
import java.net.Socket;

import com.websocketgame.shared.Game;


public class Server {

	static ServerSocket serverSocket;
	static UserService[] user = new UserService[6];
	// static Game game;

	
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
					user[i] = new UserService(socket, Game.INSTANCE, user, i);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			}
		}
	}
}

