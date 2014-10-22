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
				PlayerMessage message = (PlayerMessage)in.readObject();
				
				PlayerOrder[] orders = message.getOrders();
				
				System.out.println("Message received from player " + message.getPlayerId() + " with chat message :" + message.getChat());
				
				int orderNumber = 0;
				for (PlayerOrder order : orders)
				{
					System.out.println("Order #" + ++orderNumber + " area: " + 	order.getAreaWhereOrderIsPlace() + " order Type: " + order.getOrderType());
				}
				
				for(int i = 0; i < 6; i++)
				{
					if(user[i] != null)
					{
						user[i].out.writeObject(message);
					}
				}

			} catch (IOException e) {
				user[pid] = null;
			} catch (ClassNotFoundException e) {
				System.out.println("Server received Object that was not PlayerMessage");
				e.printStackTrace();
			}
		}
	}
}

