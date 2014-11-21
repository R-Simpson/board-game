package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import com.got.mechanics.*;

public class combatTest {

	@Test
	public void test() {
		Combat combat = new Combat();
		
		assertEquals("Player 1 victory (1)", 1, combat.resolveCombat(8,7));
		assertEquals("Player 2 victory (2)", 2, combat.resolveCombat(7,8));
		assertEquals("Tie (0)", 0, combat.resolveCombat(8,8));
		
	}

}
