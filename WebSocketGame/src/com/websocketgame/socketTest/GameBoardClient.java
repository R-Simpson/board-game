/*
package com.websocketgame.socketTest;

import java.applet.Applet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class GameBoardClient extends Applet implements Runnable {
	
	

	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	
	int playerId;
	
	int[] 

	public void init()
	{
		setSize(150,150);
		
		try{
		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connected");
		
		in = new DataInputStream(socket.getInputStream());
		playerId = in.readInt();
		out = new DataOutputStream(socket.getOutputStream());
		
		Input input = new Input(in, this);
		Thread thread = new Thread(input);
		thread.start();
		Thread thread2 = new Thread(this);
		thread2.start();

		
		}catch(Exception e)	{System.out.println("Unable to start client");
		}

	}
	
	public void updateMap(int playerId, int moveIn)
	{
		
	}


	public void run() {
		while(true){
			try{
				Thread.sleep(400);
			} catch(InterruptedException e)
			{
				e.printStackTrace();
			}

		}
		
	}
	
}

class Input implements Runnable{

	DataInputStream in;
	GameBoardClient client;

	public Input(DataInputStream in, GameBoardClient c){
		this.in = in;
		this.client = c;
	}

	public void run() {
		while(true){
			String message;
			try {
				int playerId = in.readInt();
				int moveIn = in.readInt();
				client.updateMap(playerId, moveIn)
				{
					
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
*/