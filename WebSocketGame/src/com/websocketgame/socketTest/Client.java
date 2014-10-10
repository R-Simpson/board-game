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
	
		/*
		Input input = new Input(in);
		Thread thread = new Thread(input);
		thread.start();
		*/
		
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
		
		out.writeObject(message);
		
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

/*
class Input implements Runnable{

	DataInputStream in;

	public Input(DataInputStream in){
		this.in = in;
	}

	public void run() {
		while(true){
			String message;
			try {
				message = in.readUTF();
				System.out.println(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
*/