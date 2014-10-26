package com.websocketgame.socketTest;

import java.io.IOException;
import java.io.DataInputStream;

public class Input implements Runnable{

	DataInputStream in;
	Client client;

	public Input(DataInputStream in, Client client){
		this.in = in;
		this.client = client;
	}

	public void run() {
		while(true){
			int pid;
			int message;
			try {
				pid = in.readInt();
				message = in.readInt();
				System.out.println("Message received from player : " + message);
				client.updateBoard(pid, message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 		
		}
	}
}