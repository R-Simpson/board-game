package com.websocketgame.clientSide;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
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
		
		Game.INSTANCE.setGameState((List<Land>) in.readObject());
		System.out.println("Set gameState as defined by server");

		Input input = new Input(in, this);
		Thread inputThread = new Thread(input);
		inputThread.setDaemon(true); // so thread will terminate when closing stage
		inputThread.start();

		Pane root = new Pane();
		root.setMinSize(140, 140);
		root.setMaxSize(140, 140);

		List<Polygon> boardGUI = new ArrayList<Polygon>();
		
		for(LandPoly land: GameBoard.INSTANCE.getGameBoard())
		{
			boardGUI.add(land.getPolygon());
			
			land.getPolygon().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {	
					try {
						PlayerMessage message = new PlayerMessage(playerid, land.getLand().getLandId());
						out.writeObject(message);

						System.out.println("Send message from client " + playerid + " to claim " + land.getLand().getLandId() 
								+ ", centroid: " + land.getLand().getCentroid()[0] + "," + land.getLand().getCentroid()[1] );
	
						// Draw 'gamepiece' test
						Circle circle = new Circle();
						circle.setCenterX(land.getLand().getCentroid()[0]);
						circle.setCenterY(land.getLand().getCentroid()[1]);					
						circle.setRadius(5.0f);
						circle.setFill(Color.BLACK);
						
						circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
							public void handle(MouseEvent e) {	
							circle.setFill(Color.ANTIQUEWHITE);
							}
						});
						
						root.getChildren().add(circle);
						// End Draw 'gamepiece' test
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

		root.getChildren().addAll(boardGUI);

		stage.setScene(new Scene(root));
		stage.setResizable(false);
		stage.show();	
		

		
	}

	public static void main(String[] args) {

		launch(args);
	}
}


