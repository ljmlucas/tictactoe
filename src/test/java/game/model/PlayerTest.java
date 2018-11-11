package game.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import game.model.Player;

public class PlayerTest {

	@Test
	public void shouldBuildWithTurnAndSymbol() {
		Player player = new Player("X", true);
		assertNotNull(player);
		assertEquals("X", player.getSymbol());
		assertEquals(true, player.isHuman());
	}
	
}
