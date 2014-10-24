package com.websocketgame.socketTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Users implements Runnable {
	ObjectOutputStream out;
	ObjectInputStream in;
	Users[] user = new Users[6];
	int pid;
	int remotePid;

	
	public Users(ObjectOutputStream out, ObjectInputStream in, Users[] user, int pid) {
		this.out = out;
		this.in = in;
		this.user = user;
		this.pid = pid;
	}

	public void run() {
		try {
			System.out.println("Writing playerId to client");
			out.writeInt(pid);
		} catch (IOException e1) {
			System.out.println("Failed to write playerId to client");
			e1.printStackTrace();
		}
		
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
