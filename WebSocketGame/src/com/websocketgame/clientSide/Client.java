package com.websocketgame.clientSide;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import com.websocketgame.messaging.PlayerMessage;
import com.websocketgame.serverSide.Game;
import com.websocketgame.serverSide.Land;

public class Client extends Application {

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	static int playerid;
	
	@Override
	public void start(Stage stage) throws Exception {

		// Connect to server
		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connected");
		
		// Set up input stream
		in = new ObjectInputStream(socket.getInputStream());

		// Set up output stream
		out = new ObjectOutputStream(socket.getOutputStream());
		out.flush();
		
		playerid = in.readInt();
		System.out.println("Assigned Player Id : " + playerid);
		
		Input input = new Input(in, this);
		Thread inputThread = new Thread(input);
		inputThread.setDaemon(true); // so thread will terminate when closing stage
		inputThread.start();

		Pane root = new Pane();
		root.setMinSize(140, 140);
		root.setMaxSize(140, 140);
		
		for(Land land: Game.INSTANCE.getGameState())
		{
			land.getLand().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {	
					try {
						PlayerMessage message = new PlayerMessage(playerid, land.getLandId());
						out.writeObject(message);
						System.out.println("Send message from client " + playerid + " to claim " + land.getLandId() 
								+ ", centroid: " + land.getCentroidX() + "," + land.getCentroidY() );
	

						Circle circle = new Circle();
						circle.setCenterX(land.getCentroidX());
						circle.setCenterY(land.getCentroidY());					
						circle.setRadius(5.0f);
						circle.setFill(Color.BLACK);
						
						circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
							public void handle(MouseEvent e) {	
							circle.setFill(Color.ANTIQUEWHITE);
							}
						});

						
						root.getChildren().add(circle);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

		root.getChildren().addAll(Game.INSTANCE.getGameBoard());

		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.show();	
		

		
	}

	public static void main(String[] args) {

		launch(args);
	}
}


