package com.objectstreamgame.scratch;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameClient extends Application {

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connected");
		
		in = new ObjectInputStream(socket.getInputStream());
		
		out = new ObjectOutputStream(socket.getOutputStream());	
		out.flush();

		Input input = new Input(in, this);
		Thread thread = new Thread(input);
		thread.start();
		
		Scanner sc = new Scanner(System.in);
	
		while(true)
		{
			GameMessage message = new GameMessage (sc.nextInt(), "Test String");
			out.writeObject(message);
			out.flush();
		}		
	}
	
	public class Input implements Runnable{

		ObjectInputStream in;
		GameClient client;

		public Input(ObjectInputStream in, GameClient client){
			this.in = in;
			this.client = client;
		}

		public void run() {
			while(true){
				GameMessage message;
				try {
					message = (GameMessage) in.readObject();
					System.out.println("Message received from player : " + message.getSentValue() + " " + message.getSentString());
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 		
			}
		}
	}
}


