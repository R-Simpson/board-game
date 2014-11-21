package com.got.runnable;

import com.got.mechanics.*;

public class RunGame {

	public static void main(String[] args)
	{
		Combat combat = new Combat();
		
		int winner = combat.resolveCombat(7, 8);
		
		System.out.println("Player " + winner + " is victorious");
	}
}