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
	private List<LandPoly> gameBoard = new ArrayList<LandPoly>();

	public List<LandPoly> getGameBoard()
	{
		for(Land land: gameState)
		{	
			Polygon polygon = new Polygon();
			polygon.setFill(Color.valueOf(land.getColor())); 
			polygon.setStroke(Color.BLACK); 
			polygon.getPoints().addAll(land.getLandBounds());
			LandPoly landPoly = new LandPoly(land, polygon);
			gameBoard.add(landPoly);
		}
		return gameBoard;
	}

	public void updateBoard(int pid, int area)
	{   	
		Platform.runLater(new Runnable() {	// GUI updates MUST be run on the JavaFX thread - this does that at 'some unspecified time in the future
			@Override
			public void run() {
				Game.INSTANCE.updateGameState(pid, area);
				for (LandPoly landPoly : gameBoard)
				{
					landPoly.getPolygon().setFill(Color.valueOf(landPoly.getLand().getColor()));
				}
			}
		});
	}
}