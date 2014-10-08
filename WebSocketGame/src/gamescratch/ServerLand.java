package gamescratch;

public class ServerLand {

	private int name;
	private int owner;
	private int[] adjacentLands; 	// used for validation down the line

	public ServerLand(int name, int owner)
	{
		this.name = name;
		this.owner = owner;
	}
	
	public int getName()
	{
		return name;
	}
	
	public int getOwner()
	{
		return owner;
	}

	public void setOwner(int owner)
	{
		this.owner = owner;
	}

}
