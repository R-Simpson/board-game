/*
package com.websocketgame.clientSide;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import com.websocketgame.serverSide.Game;
import com.websocketgame.serverSide.Land;

public enum GameBoard {
	INSTANCE;

	private List<Land> gameState  = new ArrayList<Land>(Game.INSTANCE.getGameState());
	private List<Polygon> gameBoard = new ArrayList<Polygon>();

	public List<Polygon> getGameBoard()
	{
		for(Land land: gameState)
		{	
			Polygon polygon = land.getPolygon();
			gameBoard.add(polygon);
		}
		return gameBoard;
	}

	public void updateBoard(int pid, int area)
	{   	
		Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - this does that at 'some unspecified time in the future
			@Override
			public void run() {
				Game.INSTANCE.updateGameState(pid, area);
				for (Polygon polygon : gameBoard)
				{


				}
			}
		});
	}
}
*/