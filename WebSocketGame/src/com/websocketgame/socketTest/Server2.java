package com.websocketgame.socketTest;

import java.io.*;
import java.net.*;

public class Server2 {

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
		while(true)
		{
			socket = serverSocket.accept();
			for (int i = 0; i < 6; i++)
			{
				System.out.println("Connection from " + socket.getInetAddress());
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());

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

	ObjectOutputStream out;
	ObjectInputStream in;
	Users[] user = new Users[6];
	int pid;

	
	public Users(ObjectOutputStream out, ObjectInputStream in, Users[] user, int pid) {
		this.out = out;
		this.in = in;
		this.user = user;
		this.pid = pid;
	}

	public void run() {
		while(true)
		{
			try {
				PlayerMessage test = (PlayerMessage)in.readObject();
				
				PlayerOrder[] orders = test.getOrders();
				
				System.out.println("Message received from player " + test.getPlayerId() + " with chat message :" + test.getChat());
				
				int orderNumber = 0;
				for (PlayerOrder order : orders)
				{
					System.out.println("Order #" + ++orderNumber + " area: " + 	order.getAreaWhereOrderIsPlace() + " order Type: " + order.getOrderType());
				}

			} catch (IOException e) {
				this.out = null;
				this.in = null;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

