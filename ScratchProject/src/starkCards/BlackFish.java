package starkCards;

import com.got.cards.Card;

public class BlackFish extends Card 
{
	public BlackFish()
	{
		setStrength(1);
		setSwords(0);
		setForts(0);
	}

	@Override
	public void onVictory() {
		//TODO: No casualties
	}

	@Override
	public void onDefeat() {
		//TODO: No casualties
	}
	
}
