package com.websocketgame.socketTest;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

import com.websocketgame.view.GameBoard;

public class Client extends Application implements Runnable {

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	int playerid;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connected");
		
		in = new ObjectInputStream(socket.getInputStream());
		playerid = in.readInt();
		
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
	
		Input input = new Input(in, this);
		Thread inputThread = new Thread(input);
		inputThread.start();

		Output output = new Output(out);
		Thread outputThread = new Thread(output);
		outputThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}



}






