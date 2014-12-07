package com.websocketgame.shared;

import java.io.Serializable;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Unit implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	private int owner;
	private int type; // footman, knight, catapult , boat
	
	
	private Circle circle;
	
	public Unit(Land land)
	{
		this.circle = new Circle();
		this.circle.setCenterX(land.getCentroid()[0]);
		this.circle.setCenterY(land.getCentroid()[1]);					
		this.circle.setRadius(5.0f);
		this.circle.setFill(Color.BLACK);

		this.circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {	
			circle.setFill(Color.ANTIQUEWHITE);
			}
		});
	}

	public Circle getCircle() {
		return circle;
	}
	
}
