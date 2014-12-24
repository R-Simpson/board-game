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
import javafx.stage.Stage;

import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.PlayerMessage;

public class Client extends Application {

	static Socket socket;
	static ObjectInputStream in;
	static ObjectOutputStream out;
	static int playerid;
	private Pane pane;
	private TextArea textArea;


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

			pane = new Pane();
	
			Image image = new Image("file:res/got.jpg");
	        ImageView iv1 = new ImageView();
	        iv1.setImage(image);
	        pane.getChildren().add(iv1);
			
			for(Land land: Game.INSTANCE.getGameState())
			{	
				pane.getChildren().add(land.getPolygon());
				
				land.getPolygon().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent e) {	
						try {
							PlayerMessage message = new PlayerMessage(playerid, land.getLandId());
							
							out.writeObject(message);

							System.out.println("Send message from client " + playerid + " to claim " + land.getLandId() 
									+ ", centroid: " + land.getCentroid()[0] + "," + land.getCentroid()[1] );

						} catch (IOException e1) {
							System.out.println("Unable to send message to server");
						}
					}
				});
			}
			
			refreshDisplay();
			
			pane.setStyle("-fx-background-color: teal;");

			
	        Slider slider = new Slider(0.36,1,0.36);
	        ZoomPane zoomPane = new ZoomPane(pane);
	        zoomPane.zoomFactorProperty().bind(slider.valueProperty());
	        
			ScrollPane scrollPane = new ScrollPane(zoomPane);				
			scrollPane.setMinWidth(540);

			// adding another Pane for chat
			
			
			final TextField textField = new TextField();

			Button sendButton = new Button();
			sendButton.setText("Send");
			sendButton.setDefaultButton(true);  
			
			HBox hbox = new HBox();
			hbox.getChildren().addAll(textField,sendButton);
		    hbox.setHgrow(textField, Priority.ALWAYS);


			textArea = new TextArea();
			textArea.setEditable(false);
			textArea.setFocusTraversable(false);
			textArea.setWrapText(true);
			textArea.setPrefHeight(1000);
			

			VBox vbox = new VBox();
			vbox.getChildren().addAll(textArea, hbox);
			
			
			StackPane stackPane = new StackPane();

			sendButton.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {

					if (textField.getLength() > 0)
					{
						textArea.appendText(textField.getText() + "\n");
						textField.clear();
					}
				}
			});
			
			stackPane.getChildren().add(vbox);
			
			// end: adding another pane for chat
			
						
	        stage.setScene(new Scene(new BorderPane(scrollPane, slider, stackPane, null, null)));	
	        stage.setMinWidth(1040);
	        stage.setMinHeight(500);
	        
	        stage.setWidth(1040);
	        stage.setHeight(1000);
	        
	        stage.show();
	        
			// not helping
			/*
		    final StackPane stack = new StackPane();
		    stack.getChildren().addAll(root);
		    StackPane.setAlignment(root, Pos.TOP_LEFT);
		    stack.setStyle("-fx-background-color: blue;");
			*/
			 
		} catch (ConnectException e1) {
			System.out.println("Unable to reach server");
		}
	}

	public static void main(String[] args) {

		launch(args);
	}
	

	void refreshDisplay()	// package private
	{
		// Clearing and rebuilding every time - seems dumb. How to update & refresh?
		// horrible performance - out of memory errors
		//root.getChildren().clear();
					
		// better, but need to remove old unit / shape?
		
		for(Land land: Game.INSTANCE.getGameState())
		{			
			if (land.getUnit() != null)
			{
				if (!pane.getChildren().contains(land.getUnit().getShape()))
				{
					System.out.println("Adding a unit for land " + land.getLandId());
					pane.getChildren().add(land.getUnit().getShape());
				}
			}
		}
	}
	
	void updateChat(String string)
	{
		textArea.appendText(string + "\n");
	}
}


