package game.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import game.model.Position;

public class PositionTest {

	@Test
	public void shouldBuildWithNullPlayer() {
		Position position = new Position(1, 2);
		assertNotNull(position);
		assertNull(position.getPlayer());
	}
	
}
