package starkCards;

import com.got.cards.Card;

public class Bolton extends Card 
{
	public Bolton()
	{
		setStrength(2);
		setSwords(0);
		setForts(0);
	}

	@Override
	public void onDefeat() {
		//TODO: Return discard pile to hand
	}

}
