package chatTutorial;

import gamescratch.ServerLand;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.paint.Color;


public class ChatServer {

	static ServerSocket serverSocket;
	static Socket socket;
	static ObjectOutputStream out;
	static ObjectInputStream in;
	static User[] user = new User[3];

	public static void main(String[] args) throws Exception
	{
		System.out.println("Starting Server...");
		serverSocket = new ServerSocket(7777);
		System.out.println("Server Started...");
		
		while(true)
		{
			socket = serverSocket.accept();
			for (int i = 0; i < 3; i++)
			{
				System.out.println("Connection from " + socket.getInetAddress());
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
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

	ObjectOutputStream out;
	ObjectInputStream in;
	User[] user = new User[3];
	String name;
	int playerId;
	int messageSenderId;
	
	public User(ObjectOutputStream out, ObjectInputStream in, User[] user, int playerId) {
		this.out = out;
		this.in = in;
		this.user = user;
		this.playerId = playerId;

	}

	public void run() {
		try {
			out.flush();
			out.writeObject(playerId);
			name = in.readUTF();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(true)
		{
			try {
				// messageSenderId = in.readInt();
				String message = in.readUTF();
				for(int i = 0; i < 3; i++)
				{
					if(user[i] != null)
					{
						user[i].out.writeInt(messageSenderId);
						user[i].out.writeUTF(playerId + " " + name + " : " + message);
					}
				}

			} catch (IOException e) {
				user[playerId] = null;
			}
		}
	}
}