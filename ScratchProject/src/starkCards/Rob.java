package starkCards;

import com.got.cards.Card;

public class Rob extends Card 
{
	public Rob()
	{
		setStrength(3);
		setSwords(0);
		setForts(0);
	}

	@Override
	public void onVictory() {
		//TODO: Choose where opponent retreats
	}
	
}
