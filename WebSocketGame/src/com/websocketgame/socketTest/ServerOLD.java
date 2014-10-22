package com.websocketgame.socketTest;
/* 
package com.websocketgame.socketTest;

import java.io.*;
import java.net.*;

import com.websocketgame.model.Continent;

public class Server {

	static ServerSocket serverSocket;
	static Socket socket;
	static ObjectOutputStream out;
	static ObjectInputStream in;
	static Users[] user = new Users[6];

	public static void main(String[] args) throws Exception
	{
		System.out.println("Starting Server...");
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		
		Continent continent = new Continent();
		
		while(true)
		{
			socket = serverSocket.accept();
			for (int i = 0; i < 6; i++)
			{
				System.out.println("Connection from " + socket.getInetAddress());
				
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());

				if(user[i] == null)
				{
					user[i] = new Users(out, in, user, i);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			}
		}
	}
}


class Users implements Runnable{

	DataOutputStream out;
	DataInputStream in;
	Users[] user = new Users[6];
	String name;
	int playerId;
	int opponent;
	int moveIn;


	public Users(DataOutputStream out, DataInputStream in, Users[] user, int pid) {
		this.out = out;
		this.in = in;
		this.user = user;
		this.playerId = pid;
	}

	public void run() {
		try {
			out.writeInt(playerId);
		} catch (IOException e1) {
			System.out.println("Failed to send PlayerId");
		}
		while(true)
		{
			try {
				opponent = in.readInt();
				moveIn = in.readInt();
				
				for(int i = 0; i < 6; i++)
				{
					if(user[i] != null)
					{
						user[i].out.writeInt(opponent);
						user[i].out.writeInt(moveIn);
					}
				}

			} catch (IOException e) {
				user[playerId] = null;

			}
		}
	}
}
*/