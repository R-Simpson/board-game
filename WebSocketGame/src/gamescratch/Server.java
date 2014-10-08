package gamescratch;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;


public class Server {

	static ServerSocket serverSocket;
	static Socket socket;
	static DataOutputStream out;
	static DataInputStream in;
	static ServerLand[] lands = new ServerLand[6];	// 6 lands
	static User[] user = new User[3];	// 3 players

	public static void main(String[] args) throws Exception
	{
		System.out.println("Starting Server...");
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		
		List<ServerLand> lands = new ArrayList<ServerLand>(Arrays.asList(new ServerLand(1,1),new ServerLand(2,1),new ServerLand(3,2),new ServerLand(4,2),new ServerLand(5,3),new ServerLand(6,3)));	
		// 6 lands divided up among the 3 players to start
		
		
		while(true)
		{
			socket = serverSocket.accept();
			for (int i = 0; i < 3; i++)
			{
				System.out.println("Connection from " + socket.getInetAddress());
				
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());

				if(user[i] == null)
				{
					user[i] = new User(out, in, user, i);
					Thread thread = new Thread(user[i]);
					thread.start();
					break;
				}
			}
		}
	}
}


class User implements Runnable{

	DataOutputStream out;
	DataInputStream in;
	User[] user = new User[3];
	int assignid;
	int playerid;
	int move;


	public User(DataOutputStream out, DataInputStream in, User[] user, int assignid) {
		this.out = out;
		this.in = in;
		this.user = user;
		this.assignid = assignid;
	}

	public void run() {
		try {
			out.writeInt(assignid);
			for (ServerLand land : lands)
			{
				
			}
			
		} catch (IOException e1) {
			System.out.println("Failed to send PlayerId");
		}
		while(true)
		{
			try {
				playerid = in.readInt();
				move = in.readInt();
				
				for(int i = 0; i < 3; i++)
				{
					if(user[i] != null)
					{
						user[i].out.writeInt(playerid);
						user[i].out.writeInt(move);
					}
				}

			} catch (IOException e) {
				user[assignid] = null;

			}
		}
	}
}