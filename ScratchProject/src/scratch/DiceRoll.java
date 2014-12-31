package scratch;

import java.util.Random;

public class DiceRoll {

	public static void main(String[] args)
	{
		
	    Random random = new Random();
	    
	    for (int i = 0; i < 100; i++)
	    {
	    int diceRoll = random.nextInt(10)+1;
	    System.out.println(diceRoll);
	    }
	}

}
