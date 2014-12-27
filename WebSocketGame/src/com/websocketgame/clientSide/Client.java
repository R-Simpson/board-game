package com.websocketgame.clientSide;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.PlayerMessage;
import com.websocketgame.shared.Unit;

public class Client extends Application {

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	static int playerid;
	private Pane pane;
	private ChatPane chatPane;
	private Unit selectedUnit;

	@Override
	public void start(Stage stage) throws Exception {
		
		pane = new Pane();
		chatPane = new ChatPane();
		chatPane.setDebug(true);

		// Connect to server
		updateDebug("Connecting...");
		try {	
			socket = new Socket("localhost",7777);
			updateDebug("Connected");

			// Set up input stream
			in = new ObjectInputStream(socket.getInputStream());

			// Set up output stream
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();

			playerid = in.readInt();
			updateDebug("Assigned Player Id : " + playerid);

			Game.INSTANCE.setGameState((List<Land>) in.readObject());

			updateDebug("Set gameState as defined by server");

			Input input = new Input(in, this);
			Thread inputThread = new Thread(input);
			inputThread.setDaemon(true); // so thread will terminate when closing stage
			inputThread.start();

			// Move following to GamePane class?
			Image image = new Image("file:res/got.jpg");
	        ImageView iv1 = new ImageView();
	        iv1.setImage(image);
	        pane.getChildren().add(iv1);
			
			for(Land land: Game.INSTANCE.getGameState())
			{	
				pane.getChildren().add(land.getPolygon());
				

				
				land.getPolygon().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {
						
						if (selectedUnit != null)
						{
							try {
								PlayerMessage message = new PlayerMessage(playerid, land.getLandId());					
								out.writeObject(message);
								updateDebug("Send message from client " + playerid + " to claim " + land.getLandId() 
										+ ", centroid: " + land.getCentroid()[0] + "," + land.getCentroid()[1] );

							} catch (IOException e1) {
								updateDebug("Unable to send message to server");
							}
						}
						else
						{
							updateChat("GAME: Select a unit to move first");
						}

					}
				});
			}

			pane.setStyle("-fx-background-color: teal;");
		
	        Slider slider = new Slider(0.36,1,0.36);
	        ZoomPane zoomPane = new ZoomPane(pane);
	        zoomPane.zoomFactorProperty().bind(slider.valueProperty());
	        
			ScrollPane scrollPane = new ScrollPane(zoomPane);				
			scrollPane.setMinWidth(540);
			
			refreshDisplay();
			
	        stage.setScene(new Scene(new BorderPane(scrollPane, slider, chatPane.getChatPane(), null, null)));
	        stage.setMinWidth(1040);
	        stage.setMinHeight(500);	        
	        stage.setWidth(1040);
	        stage.setHeight(1000);        
	        stage.show();
	        
		} catch (ConnectException e1) {
			System.out.println("Unable to reach server");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	void refreshDisplay()	// package private
	{
		// Better than before, but need to remove old unit / shape?
		System.out.println("Refreshing Display");
		for(Land land: Game.INSTANCE.getGameState())
		{					
			if (land.getUnit() != null)
			{
				if (!pane.getChildren().contains(land.getUnit().getShape()))
				{
					updateDebug("Adding a unit for land " + land.getLandId());
					
					Unit unit = land.getUnit();
					Shape shape = unit.getShape();
					
					pane.getChildren().add(shape);
					
					shape.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
						public void handle(MouseEvent e) {
							if (shape instanceof Circle)
							{
								if (((Circle) shape).getRadius() == 20.0)
								{
									((Circle) shape).setRadius(10.0);
									deselectUnit(unit);
								}
								else
								{
									((Circle) shape).setRadius(20.0); 
									selectUnit(unit);
								}
							}
							updateDebug("UNIT CLICKED");
						}
					});	
				}
			}
		}
	}

	void updateChat(String text)
	{
		chatPane.writeToChatPane(text);
	}
	
	void updateDebug(String string)
	{
		chatPane.writeDebug(string);
	}
	
	public void selectUnit(Unit unit)
	{
		this.selectedUnit = unit;
		updateDebug("Selected land " + unit.getLand().getLandId());
	}
	
	public void deselectUnit(Unit unit)
	{
		this.selectedUnit = null;
		updateDebug("Unselected land " + unit.getLand().getLandId());
	}
	
	public Unit getSelectedUnit()
	{
		return selectedUnit;
	}
	
}


