package com.got.mechanics;

public class Combat {
	
	public int resolveCombat(int player1, int player2)
	{
		if (player1 > player2)
		{
			return 1;
		}
		else if (player2 > player1)
		{
			return 2;
		}
		else
		{
			return 0;
		}
	}

}
