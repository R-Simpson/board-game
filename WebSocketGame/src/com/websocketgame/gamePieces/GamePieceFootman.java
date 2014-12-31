package com.websocketgame.gamePieces;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

import com.websocketgame.shared.Land;
import com.websocketgame.shared.Unit;

// this class was created due to problems with the event handler detailed in 
// ScratchProject/scratch/GamePaneEventHandlerIssue in the hope that being able to
// get a handler on the parent unit or land from a shape would be of some assistance. 
// Ended up with a different solution, this class is somewhat redundant at the moment
// but could be useful for adding extra behaviour to Shape in future.

public class GamePieceFootman extends Circle {
	
	private Unit unit;
	private Land land;
	
	public GamePieceFootman(Unit unit)
	{
		this.unit = unit;
		this.land = unit.getLand();
		this.setCenterX(land.getCentroid()[0]);
		this.setCenterY(land.getCentroid()[1]);					
		this.setRadius(10.0);
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
