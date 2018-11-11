package game.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import game.exceptions.InvalidPlaygroundSizeException;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class BoardTest {

	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldBuildWithValidSizeAndCallBuildEmptyPositions(int size) {
		Board board = new Board(size);

		assertSame(size, board.getSize());
		assertNotNull(board.getPositions());
		
		for (int row = 0; row < size; ++row) {
			for (int column = 0; column < size; ++column) {
				assertNotNull(board.getPosition(row, column));
			}
		}
	}
	
	@Parameters({"-2", "1", "11", "48"})
	@Test(expected = InvalidPlaygroundSizeException.class)
	public void shouldNotBuildWithInvalidSize(int sizePlayground) {
		new Board(sizePlayground);
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnFalseIfThereIsNotWinner(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		Board spyBoard= Mockito.spy(board);
		Mockito.when(spyBoard.isDiagonalComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isOppositeDiagonalComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isRowComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isColumnComplete(player)).thenReturn(false);
		assertFalse(spyBoard.isWinner(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnTrueThereIsDiagonalWinner(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		Board spyBoard= Mockito.spy(board);
		Mockito.when(spyBoard.isDiagonalComplete(player)).thenReturn(true);
		Mockito.when(spyBoard.isOppositeDiagonalComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isRowComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isColumnComplete(player)).thenReturn(false);
		assertTrue(spyBoard.isWinner(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnThereIsOppositeDiagonalWinner(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		Board spyBoard= Mockito.spy(board);
		Mockito.when(spyBoard.isDiagonalComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isOppositeDiagonalComplete(player)).thenReturn(true);
		Mockito.when(spyBoard.isRowComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isColumnComplete(player)).thenReturn(false);
		assertTrue(spyBoard.isWinner(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnThereIsRowlWinner(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		Board spyBoard= Mockito.spy(board);
		Mockito.when(spyBoard.isDiagonalComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isOppositeDiagonalComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isRowComplete(player)).thenReturn(true);
		Mockito.when(spyBoard.isColumnComplete(player)).thenReturn(false);
		assertTrue(spyBoard.isWinner(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnThereIsColumnWinner(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		Board spyBoard= Mockito.spy(board);
		Mockito.when(spyBoard.isDiagonalComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isOppositeDiagonalComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isRowComplete(player)).thenReturn(false);
		Mockito.when(spyBoard.isColumnComplete(player)).thenReturn(true);
		assertTrue(spyBoard.isWinner(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnTrueIfDiagonalIsCompleteWithSamePlayer(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		for(int position = 0; position < board.getSize(); position++) {
			board.getPosition(position, position).setPlayer(player);
		}
		assertTrue(board.isDiagonalComplete(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnTrueIfOppositeDiagonalIsCompleteWithSamePlayer(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		int row = 0;
		for (int column = board.getSize() - 1; column >= 0; column--) {
			board.getPosition(row, column).setPlayer(player);
			row++;
		}
		assertTrue(board.isOppositeDiagonalComplete(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnTrueIfRowIsCompleteWithSamePlayer(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		for (int column = 0; column< board.getSize(); column++) {
			board.getPosition(0, column).setPlayer(player);
		}
		assertTrue(board.isRowComplete(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnFalseIfRowIsNotCompleteWithSamePlayer(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		board.getPositions()[0][0].setPlayer(new Player("Y", true));
		for (int column = 1; column< board.getSize(); column++) {
			board.getPosition(0, column).setPlayer(player);
		}
		assertFalse(board.isRowComplete(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnTrueIfColumnIsCompleteWithSamePlayer(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		for (int row = 0; row < board.getSize(); row++) {
			board.getPosition(row, 0).setPlayer(player);
		}
		assertTrue(board.isColumnComplete(player));
	}
	
	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
	@Test
	public void shouldReturnFalseIfColumnIsNotCompleteWithSamePlayer(int size) {
		Player player = new Player("X", true);
		Board board = new Board(size);
		board.getPositions()[0][0].setPlayer(new Player("Y", true));
		for (int row = 1; row< board.getSize(); row++) {
			board.getPosition(row, 0).setPlayer(player);
		}
		assertFalse(board.isColumnComplete(player));
	}
	
//	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
//	@Test
//	public void shouldReturnFalseIfPositionIsNotFilled(int size) {
//		Board board = new Board(size);
//		assertFalse(board.isPositionFilled(0, 0));
//		assertFalse(board.isPositionFilled(size-1, size-1));
//	}
//	
//	@Parameters({"3|1", "4|1", "5|1", "6|1", "7|1", "8|1", "9|1", "10|1"})
//	@Test
//	public void shouldReturnTrueIfPositionIsFilled(int size, int position) {
//		Board board = new Board(size);
//		board.getPosition(position, position).setPlayer(new Player("X", true));
//		assertTrue(board.isPositionFilled(position, position));
//	}
	
//	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
//	@Test
//	public void shouldReturnTrueIfPlaygroudContainsPosition() {
//		Board board = new Board(3);
//		assertTrue(board.isPositionInThePlaygroud(1, 0));
//	}
//	
//	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
//	@Test
//	public void shouldReturnFalseIfPlaygroudDoesntContainsRowPosition() {
//		Board board = new Board(3);
//		assertFalse(board.isPositionInThePlaygroud(5, 0));
//	}
//	
//	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
//	@Test
//	public void shouldReturnFalseIfPlaygroudDoesntContainsColumnPosition() {
//		Board board = new Board(3);
//		assertFalse(board.isPositionInThePlaygroud(0, 5));
//	}
//	
//	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
//	@Test
//	public void shouldReturnFalseIfPlaygroudDoesntContainsNegativeRowPosition() {
//		Board board = new Board(3);
//		assertFalse(board.isPositionInThePlaygroud(-1, 2));
//	}
//	
//	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
//	@Test
//	public void shouldReturnFalseIfPlaygroudDoesntContainsNegativeColumnPosition() {
//		Board board = new Board(3);
//		assertFalse(board.isPositionInThePlaygroud(2, -1));
//	}
//	
//	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
//	@Test
//	public void shouldReturnFalseIfInputDoenstMatchRegex() {
//		Board board = new Board(3);
//		assertFalse(board.getInputPosition("Axcwe:132", new Player("X", true)));
//	}
//	
//	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
//	@Test
//	public void shouldReturnFalseIfInputMatchesRegexButPositionIsNotEmpty() {
//		Board board = new Board(3);
//		Board spyBoard= Mockito.spy(board);
//		Mockito.when(spyBoard.isPositionEmpty(2, 2)).thenReturn(true);
//		assertFalse(spyBoard.getInputPosition("2,2", new Player("X", true)));
//	}
//	
//	@Parameters({"3", "4", "5", "6", "7", "8", "9", "10"})
//	@Test
//	public void shouldReturnTrueIfInputMatchesRegexAndPositionIsEmpty() {
//		Board board = new Board(3);
//		Board spyBoard= Mockito.spy(board);
//		Mockito.when(spyBoard.isPositionEmpty(2, 2)).thenReturn(false);
//		assertTrue(spyBoard.getInputPosition("2,2", new Player("X", true)));
//	}
	
}
