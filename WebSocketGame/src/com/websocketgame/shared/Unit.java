package com.websocketgame.shared;

import java.io.IOException;
import java.io.Serializable;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Unit implements Serializable {
	
	private static final long serialVersionUID = 0;
	
	private int owner;
	private int type;
	private Land land;
	private transient Shape shape;
	
	public Unit(int owner, int type, Land land)
	{
		this.owner = owner;
		this.type = type;
		this.land = land;
		setUpShape(owner, type, land);
	}
	
	private void setUpShape(int owner, int type, Land land)
	{
		if (type==1)
		{
			Circle circle = new Circle();
			circle.setCenterX(land.getCentroid()[0]);
			circle.setCenterY(land.getCentroid()[1]);					
			circle.setRadius(5.0);
			circle.setStroke(Color.BLACK);
			this.shape = circle;
		}
		else if (type==2)
		{
			Rectangle square = new Rectangle();
			square.setLayoutX(land.getCentroid()[0]);
			square.setLayoutY(land.getCentroid()[1]);
			square.setWidth(10.0);
			square.setHeight(10.0);
			square.setStroke(Color.BLACK);
			this.shape = square;
		}
	
		switch (owner){
		case 0:
			this.shape.setFill(Color.RED);
			break;
		case 1:
			this.shape.setFill(Color.BLUE);
			break;
		case 2:
			this.shape.setFill(Color.YELLOW);
			break;
		case 3:
			this.shape.setFill(Color.GREEN);
			break;
		case 4:
			this.shape.setFill(Color.GREY);
			break;
		case 5:
			this.shape.setFill(Color.PURPLE);
			break;
		}
		
		this.shape.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e) {
			if (shape instanceof Circle)
			{
				((Circle) shape).setRadius(10.0); 
			}
			else if (shape instanceof Rectangle)
			{
				((Rectangle) shape).setWidth(20.0);
				((Rectangle) shape).setHeight(20.0);
			}
			}
		});
	}

	public Shape getShape() {
		return shape;
	}
	
	public Unit getUnit()
	{
		return this;
	}
	
    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
    	stream.writeInt(owner);
    	stream.writeInt(type);
    	stream.writeObject(land);
    	System.out.println("Writing unit with owner " + owner + ", type " + type);
    }
	
    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {   	
    	this.owner =  stream.readInt();
    	this.type =  stream.readInt();
    	this.land = (Land) stream.readObject();
    	setUpShape(owner, type, land);
	}
	
}
