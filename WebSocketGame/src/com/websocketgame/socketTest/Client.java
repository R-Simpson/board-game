package com.websocketgame.socketTest;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import com.websocketgame.model.Land;

public class Client extends Application {

	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	static int playerid;

	List<Land> board;

	@Override
	public void start(Stage stage) throws Exception {

		// Connect to server
		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connected");

		// Set up input stream
		in = new DataInputStream(socket.getInputStream());
		playerid = in.readInt();

		Input input = new Input(in, this);
		Thread inputThread = new Thread(input);
		inputThread.start();

		// Set up output stream
		out = new DataOutputStream(socket.getOutputStream());
		out.flush();
		//		Output output = new Output(out);
		//		Thread outputThread = new Thread(output);
		//		outputThread.start();

		Pane root = new Pane();
		root.setMinSize(150, 150);

		board = new ArrayList<Land>(Arrays.asList(
				new Land(1, Color.GREY, new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0}), 
				new Land(2, Color.GREY, new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 80.0,10.0, 100.0,0.0}), 
				new Land(3, Color.GREY, new Double[]{100.0,0.0, 80.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0}),
				new Land(4, Color.GREY, new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0}), 
				new Land(5, Color.GREY, new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0}), 
				new Land(6, Color.GREY, new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0})));	

		List<Polygon> polyList = new ArrayList<Polygon>();

		for(Land land: board)
		{
			polyList.add(land.getLand());

			land.polygon.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {	
					try {
						out.writeInt(playerid);
						out.writeInt(land.getLandId());
						System.out.println("Send message from client " + playerid + " to claim " + land.getLandId());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

		root.getChildren().addAll(polyList);

		stage.setScene(new Scene(root));
		stage.show();		
	}

	public void updateBoard(int pid, int area)
	{
		Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - this does that at 'some unspecified time in the future
			@Override
			public void run() {
				for(Land land : board){
					if(land.getLandId() ==  area)
						switch (pid){
						case 0:
							land.polygon.setFill(Color.RED);
							System.out.println("Land " + land.getLandId() + " claimed by player" + pid);
							break;
						case 1:
							land.polygon.setFill(Color.BLUE);
							System.out.println("Land " + land.getLandId() + " claimed by player" + pid);
							break;
						case 2:
							land.polygon.setFill(Color.YELLOW);
							System.out.println("Land " + land.getLandId() + " claimed by player" + pid);
							break;
						case 3:
							land.polygon.setFill(Color.GREEN);
							System.out.println("Land " + land.getLandId() + " claimed by player" + pid);
							break;
						case 4:
							land.polygon.setFill(Color.ORANGE);
							System.out.println("Land " + land.getLandId() + " claimed by player" + pid);
							break;
						case 5:
							land.polygon.setFill(Color.PURPLE);
							System.out.println("Land " + land.getLandId() + " claimed by player" + pid);
							break;
						}
				}	
			}			
		});
	}

	public static void main(String[] args) {

		launch(args);
	}
}


