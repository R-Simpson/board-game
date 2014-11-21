package chatTutorial;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


public class ChatClient {

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	
	int playerId;
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connected");
		

		
		in = new ObjectInputStream(socket.getInputStream());
		int playerId = in.readInt();
		System.out.println("Player ID set as " + playerId);
		
		out = new ObjectOutputStream(socket.getOutputStream());	
		out.flush();

		
		Input input = new Input(in);
		Thread thread = new Thread(input);
		thread.start();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name");
		String name = sc.nextLine();
		out.writeUTF(name);
		
		while(true)
		{
			String sendMessage = sc.nextLine();
			out.writeUTF(sendMessage);
		}
		
	}
}

class Input implements Runnable{
	ObjectInputStream in;
	public Input(ObjectInputStream in){
		this.in = in;
	}
	public void run() {
		while(true){
			String message;
			try {
				message = in.readUTF();
				System.out.println(message);
			} catch (IOException e) {
				System.out.println("Server Disconnected");
				e.printStackTrace();
				break;
			}
		}
	}
}