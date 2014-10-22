package com.websocketgame.socketTest;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import com.websocketgame.view.GameBoard;

public class Client {

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;

	public static void main(String[] args) throws Exception
	{
		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connected");
		
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
	
		Input input = new Input(in);
		Thread inputThread = new Thread(input);
		inputThread.start();

		Output output = new Output(out);
		Thread outputThread = new Thread(output);
		outputThread.start();
	
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name");
		String name = sc.nextLine();
		out.writeUTF(name);
		
		while(true)
		{
			String sendMessage = sc.nextLine();
			out.writeUTF(sendMessage);
		}
		*/
	}
}


class Output implements Runnable{

	ObjectOutputStream out;

	public Output(ObjectOutputStream out){
		this.out = out;
	}

	public void run() {
	//	while(true){
			
			PlayerMessage message = new PlayerMessage();
			PlayerOrder order1 = new PlayerOrder();
			PlayerOrder order2 = new PlayerOrder();
			
			order1.setAreaWhereOrderIsPlace(1);
			order1.setOrderType(2);
			order2.setAreaWhereOrderIsPlace(3);
			order2.setOrderType(4);
			message.setPlayerId(5);
			message.setOrders(new PlayerOrder[]{order1, order2});
			message.setChat("Hello worlds!");
			
			try {
				out.writeObject(message);;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	//	}
	}
}


class Input implements Runnable{

	ObjectInputStream in;

	public Input(ObjectInputStream in){
		this.in = in;
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
