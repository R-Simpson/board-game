package com.objectstreamgame.scratch;
import java.io.Serializable;

public class GameMessage implements Serializable {
	
	private static final long serialVersionUID = 0;
	private int sentValue;
	private String sentString;

	public GameMessage(int sentValue, String sentString) {
		this.sentValue = sentValue;
		this.sentString = sentString;
	}

	public int getSentValue() {
		return sentValue;
	}

	public void setSentValue(int sentValue) {
		this.sentValue = sentValue;
	}

	public String getSentString() {
		return sentString;
	}

	public void setSentString(String sentString) {
		this.sentString = sentString;
	}
	
}
