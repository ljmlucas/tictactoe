package game.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import game.model.Position;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class ConsoleInOutTest {
	
	@Test
	public void shoulReturnTrueIfInputIsValid() {
		ConsoleInOut console = new ConsoleInOut();
		assertTrue(console.isInputValid("3,2"));
	}

	@Test
	public void shouldReturnFalseIfInputIsNotValid() {
		ConsoleInOut console = new ConsoleInOut();
		assertTrue(console.isInputValid("wfas3,das 2"));
	}

	@Test
	public void shouldReturnTrueIfPositionIsValid() {
		ConsoleInOut console = new ConsoleInOut();
		Position position = new Position(1, 3);
		assertTrue(console.isPositionValid(position));
	}

	@Test
	public void shouldReturnFalseIfPositionIsNotValid() {
		ConsoleInOut console = new ConsoleInOut();
		Position position = new Position(72, 11);
		assertFalse(console.isPositionValid(position));
	}
	
	@Test
	public void shouldReturnFalseIfRowPositionIsNegative() {
		ConsoleInOut console = new ConsoleInOut();
		Position position = new Position(-2, 3);
		assertFalse(console.isPositionValid(position));
	}
	
	@Test
	public void shouldReturnFalseIfRowPositionGreaterThanMaxSize() {
		ConsoleInOut console = new ConsoleInOut();
		Position position = new Position(Constants.MAX_SIZE + 1, 3);
		assertFalse(console.isPositionValid(position));
	}
	
	@Test
	public void shouldReturnFalseIfColumnPositionIsNegative() {
		ConsoleInOut console = new ConsoleInOut();
		Position position = new Position(2, -5);
		assertFalse(console.isPositionValid(position));
	}
	
	@Test
	public void shouldReturnFalseIfColumnPositionGreaterThanMaxSize() {
		ConsoleInOut console = new ConsoleInOut();
		Position position = new Position(2, Constants.MAX_SIZE + 1);
		assertFalse(console.isPositionValid(position));
	}
	
	@Test
	public void shouldReturnPositionWithRowAndColumn() {
		ConsoleInOut console = new ConsoleInOut();
		Position position = console.extractPosition("2,2");
		assertEquals(2, position.getRow());
		assertEquals(2, position.getColumn());
	}

}
