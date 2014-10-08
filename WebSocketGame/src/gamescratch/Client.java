package gamescratch;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


public class Client extends Application {

	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	
	int playerId;
	
	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		root.setMinSize(150, 150);
		
		
		
		lands[1].getLand(1).polygon.getPoints().addAll(new Double[]{0.0,0.0, 50.0,0.0, 40.0,20.0, 50.0,40.0, 0.0,50.0});
		getLand(2).polygon.getPoints().addAll(new Double[]{50.0,0.0, 40.0,20.0, 50.0,40.0, 100.0,50.0, 90.0,40.0, 100.0,20.0, 80.0,10.0, 100.0,0.0});
		getLand("L3").polygon.getPoints().addAll(new Double[]{100.0,0.0, 80.0,10.0, 100.0,20.0, 90.0,40.0, 100.0,50.0, 150.0,70.0, 150.0,0.0});
		getLand("L4").polygon.getPoints().addAll(new Double[]{0.0,50.0, 50.0,40.0, 100.0,50.0, 90.0,100.0, 0.0,100.0});
		getLand("L5").polygon.getPoints().addAll(new Double[]{100.0,50.0, 150.0,70.0, 150.0,150.0, 120.0,150.0, 90.0,100.0});
		getLand("L6").polygon.getPoints().addAll(new Double[]{0.0,100.0, 90.0,100.0, 120.0,150.0, 0.0,150.0});
		
		List<Polygon> polyList = new ArrayList<Polygon>();
		root.getChildren().addAll(polyList);

		stage.setScene(new Scene(root));
		stage.show();	
		
		
		
		
	}

	public static void main(String[] args) throws Exception
	{
		System.out.println("Connecting...");
		socket = new Socket("localhost",7777);
		System.out.println("Connected");
		
		in = new DataInputStream(socket.getInputStream());
		int playerId = in.readInt();
		System.out.println("Player ID set as " + playerId);
		out = new DataOutputStream(socket.getOutputStream());
		
		Input input = new Input(in);
		Thread thread = new Thread(input);
		thread.start();
		
		
	}
}

class Input implements Runnable{
	DataInputStream in;
	public Input(DataInputStream in){
		this.in = in;
	}
	public void run() {
		while(true){
			int name;
			int move;
			try {
				name = in.readInt();	
				move = in.readInt();
				
				for(int i = 0; i < 6; i++)
				{
					if(user[i] != null)
					{
						user[i].out.writeInt(opponent);
						user[i].out.writeInt(move);
					}
				}
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}