package com.websocketgame.socketTest;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Input implements Runnable{

	ObjectInputStream in;
	Client client;

	public Input(ObjectInputStream in, Client client){
		this.in = in;
		this.client = client;
		
	}

	public void run() {
		while(true){
			PlayerMessage message;
			try {
				message = (PlayerMessage)in.readObject();	
				PlayerOrder[] orders = message.getOrders();
				System.out.println("Message received from player " + message.getPlayerId() + " with chat message :" + message.getChat());
				int orderNumber = 0;
				for (PlayerOrder order : orders)
				{
					System.out.println("Order #" + ++orderNumber + " area: " + 	order.getAreaWhereOrderIsPlace() + " order Type: " + order.getOrderType());
				}
				in.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Client received Object that was not PlayerMessage");
				e.printStackTrace();
			}
			

		}
	}
}