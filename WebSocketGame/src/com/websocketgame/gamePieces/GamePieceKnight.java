package com.websocketgame.gamePieces;

import com.websocketgame.shared.Land;
import com.websocketgame.shared.Unit;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

//this class was created due to problems with the event handler detailed in 
//ScratchProject/scratch/GamePaneEventHandlerIssue in the hope that being able to
//get a handler on the parent unit or land from a shape would be of some assistance. 
//Ended up with a different solution, this class is somewhat redundant at the moment
//but could be useful for adding extra behaviour to Shape in future.

public class GamePieceKnight extends Rectangle {

	private Unit unit;
	private Land land;
	
	public GamePieceKnight(Unit unit)
	{
		this.unit = unit;
		this.land = unit.getLand();
		this.setLayoutX(land.getCentroid()[0]);
		this.setLayoutY(land.getCentroid()[1]);
		this.setWidth(20.0);
		this.setHeight(20.0);
		this.setStroke(Color.BLACK);
		this.setStrokeType(StrokeType.OUTSIDE);
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
	public Land getLand() {
		return land;
	}
	
	public void setLand(Land land) {
		this.land = land;
	}
}
