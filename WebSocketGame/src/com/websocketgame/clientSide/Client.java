package com.websocketgame.clientSide;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.websocketgame.shared.Game;
import com.websocketgame.shared.Land;
import com.websocketgame.shared.Unit;

public class Client extends Application {

	Socket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	private int playerid;
	private String playerName;
	private ChatPane chatPane;
	private GamePane gamePane;
	private Unit selectedUnit;

	@Override
	public void start(Stage stage) throws Exception {
		
		setPlayerName("NewPlayer");
		
		// Initialise chat pane here so we can write connection debug statements to it
		chatPane = new ChatPane(this);
		chatPane.setDebug(true);
		
		// Connect to server
		updateDebug("Connecting...");
		try {	
			// Localhost for testing, client should be able to enter IP, port, password
			socket = new Socket("localhost",7777);
			updateDebug("Connected");

			// Set up input stream
			in = new ObjectInputStream(socket.getInputStream());

			// Set up output stream
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			
			// READ #0
			// Read array of available playerId
			// Would need to separate pid (userService[pid]) from player id (which family you want to play as)
			// Fix chat first, then this
			// ArrayList<Integer> availablePlayerIds = new ArrayList<Integer>();

			// READ #1
			// Read player ID assigned, should make this a client choice from those left available
			playerid = in.readInt();
			updateDebug("Assigned Player Id : " + playerid);

			// READ #2
			// Load GameState from Server
			Game.INSTANCE.setGameState((List<Land>) in.readObject());
			updateDebug("Set gameState as defined by server");
			
			// Initialise game pane now that GameState is loaded from server
			gamePane = new GamePane(this);

			// Start the input thread listening for messages from the server
			Input input = new Input(in, this);
			Thread inputThread = new Thread(input);
			inputThread.setDaemon(true); // set 'true' so thread will terminate when closing stage / game window
			inputThread.start();

			// Refresh display to show units on the board
			refreshDisplay();
			
	        stage.setScene(new Scene(new BorderPane(gamePane.getGamePane(), gamePane.getSlider(), chatPane.getChatPane(), null, null)));
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
		gamePane.refreshDisplay();
	}

	public int getPlayerId() {
		return playerid;
	}
	public void setPlayerId(int playerid) {
		this.playerid = playerid;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public boolean selectUnit(Unit unit)
	{
		boolean validSelection = false;
		if (unit.getOwner() == playerid)
		{
			this.selectedUnit = unit;	
			updateDebug("Selected unit at land " + unit.getLand().getLandId());
			validSelection = true;
		}
		return validSelection;
	}
	public Unit getSelectedUnit()
	{
		return selectedUnit;
	}
	public void deselectUnit(Unit unit)
	{
		this.selectedUnit = null;
		updateDebug("Deselected unit at land " + unit.getLand().getLandId());
	}
	
	void updateChat(String text)
	{
		chatPane.writeToChatPane(text);
	}
	
	void updateDebug(String string)
	{
		chatPane.writeDebug(string);
	}
	
	
	
}


