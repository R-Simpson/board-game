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
	}
}






