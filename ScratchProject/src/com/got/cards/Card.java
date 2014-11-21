package com.got.cards;

public abstract class Card 
{
	private int strength;
	private int swords;
	private int forts;
	private boolean discard = false;
	
	public int getStrength()
	{
		return strength;
	}
	
	public void setStrength(int strength)
	{
		this.strength = strength;
	}
	
	public int getSwords()
	{
		return swords;
	}
	
	public void setSwords(int swords)
	{
		this.swords = swords;
	}
	
	public int getForts()
	{
		return forts;
	}
	
	public void setForts(int forts)
	{
		this.forts = forts;
	}
	
	public void onVictory()
	{
		// do nothing - overridden by subclass
	}
	
	public void onDefeat()
	{
		// do nothing - overridden by subclass
	}
	
	public void specialAbility()
	{
		// do nothing - overridden by subclass
	}

	//TODO: Other methods, doStartOfCombat, doEndOfCombat, doOptionalAbility ???
	//TODO: Remove individual classes for each card, create with constructor which takes name, strength, swords & forts - then decorate for other behaviours?
}
