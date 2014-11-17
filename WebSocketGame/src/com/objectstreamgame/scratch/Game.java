package com.objectstreamgame.scratch;

public class Game {
	
	private int value;
	
	public Game(int startingValue)
	{
		this.value = startingValue;
	}
	
	public void add(int value)
	{
		this.value = this.value + value;
	}
	
	public int getValue()
	{
		return value;
	}

}
