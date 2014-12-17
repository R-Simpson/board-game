package com.websocketgame.clientSide;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Units.AbstractUnit;
import Units.Footman;
import Units.Knight;

import com.websocketgame.shared.Game;
import com.websocketgame.shared.Unit;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.PlayerMessage;

public class Client extends Application {

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	static int playerid;

	@Override
	public void start(Stage stage) throws Exception {

		// Connect to server
		System.out.println("Connecting...");
		try {
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
			root.setMinSize(140, 190);
			root.setMaxSize(140, 190);

			//List<Polygon> board = new ArrayList<Polygon>();
			
			

			for(Land land: Game.INSTANCE.getGameState())
			{
				//board.add(land.getPolygon());	
				
				root.getChildren().add(land.getPolygon());
				
				if (land.getUnit() != null)
				{
					System.out.println("Adding a unit for land " + land.getLandId());
					root.getChildren().add(land.getUnit().getShape());
				}
				
				land.getPolygon().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {	
						try {
							PlayerMessage message = new PlayerMessage(playerid, land.getLandId());
							
							out.writeObject(message);

							System.out.println("Send message from client " + playerid + " to claim " + land.getLandId() 
									+ ", centroid: " + land.getCentroid()[0] + "," + land.getCentroid()[1] );
/*
							//Unit gamepiece = new Unit(playerid,1,land);						
							//root.getChildren().add(gamepiece.getShape());
						AbstractUnit footman = new Footman(land);
						AbstractUnit knight = new Knight(land);
						root.getChildren().add(footman.getText());
						root.getChildren().add(knight.getText());	
*/
						} catch (IOException e1) {
							System.out.println("Unable to send message to server");
						}
					}
				});
			}

		//	root.getChildren().addAll(board);
			stage.setScene(new Scene(root));
			stage.setResizable(false);
			stage.show();	

		} catch (ConnectException e1) {
			System.out.println("Unable to reach server");
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}


