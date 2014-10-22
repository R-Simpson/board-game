package chatTutorial;

import gamescratch.ServerLand;

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
	String name;

	public User(DataOutputStream out, DataInputStream in, User[] user, int assignid) {
		this.out = out;
		this.in = in;
		this.user = user;
		// this.assignid = assignid;
	}

	public void run() {
		try {
			name = in.readUTF();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while(true)
		{
			try {
				String message = in.readUTF();
				
				for(int i = 0; i < 3; i++)
				{
					if(user[i] != null)
					{
						user[i].out.writeUTF(message);
					}
				}

			} catch (IOException e) {
				this.out = null;
				this.in = null;
				// user[assignid] = null;

			}
		}
	}
}