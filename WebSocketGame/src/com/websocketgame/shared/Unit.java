package com.websocketgame.shared;

import java.io.IOException;
import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;

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
		if (type==1)	// Footman, weaker unit type, represented by a circle
		{
			Circle circle = new Circle();
			circle.setCenterX(land.getCentroid()[0]);
			circle.setCenterY(land.getCentroid()[1]);					
			circle.setRadius(10.0);
			circle.setStroke(Color.BLACK);
			circle.setStrokeType(StrokeType.OUTSIDE);
			this.shape = circle;
		}
		else if (type==2) // Knight, stronger unit typ, represented by a square
		{
			Rectangle square = new Rectangle();
			square.setLayoutX(land.getCentroid()[0]);
			square.setLayoutY(land.getCentroid()[1]);
			square.setWidth(20.0);
			square.setHeight(20.0);
			square.setStroke(Color.BLACK);
			square.setStrokeType(StrokeType.OUTSIDE);
			this.shape = square;
		}

		switch (owner)
		{
			case 0:	this.shape.setFill(Color.WHITE); 		break;
			case 1:	this.shape.setFill(Color.BLUE); 		break;
			case 2:	this.shape.setFill(Color.RED);			break;
			case 3:	this.shape.setFill(Color.YELLOW);		break;
			case 4:	this.shape.setFill(Color.PURPLE);		break;
			case 5:	this.shape.setFill(Color.GREEN);		break;
			case 6:	this.shape.setFill(Color.DARKORANGE);	break;
		}
	}

	public Unit getUnit()
	{
		return this;
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getOwner()
	{
		return owner;
	}
	
	public Land getLand()
	{
		return land;
	}
	
	public Shape getShape() {
		return shape;
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
