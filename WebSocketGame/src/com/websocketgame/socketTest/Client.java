package com.websocketgame.socketTest;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import com.websocketgame.model.Continent;

public class Client extends Application {

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	int playerid;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		// Connect to server
		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connected");
		
		// Set up input stream
		in = new ObjectInputStream(socket.getInputStream());
		playerid = in.readInt();
		Input input = new Input(in, this);
		Thread inputThread = new Thread(input);
		inputThread.start();

		// Set up output stream
		out = new ObjectOutputStream(socket.getOutputStream());
		Output output = new Output(out);
		Thread outputThread = new Thread(output);
		outputThread.start();
		
	}
}






