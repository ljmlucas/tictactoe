package game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock ;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import game.model.Player;
import game.utils.ConsoleInOut;

public class TicTacToeTest {

	private List<Player> buildPlayers() {
		List<Player> players = new ArrayList<Player>();
		players.add(new Player("X", true, 0));
		players.add(new Player("Y", true, 1));
		players.add(new Player("W", false, 2));
		return players;
	}

	@Test
	public void shouldReturnFirstPlayerIfCurrentPlayerIsTheLastOne() {
		List<Player> players = buildPlayers();
		TicTacToe tictactoe = new TicTacToe(3, players);
		Player lastPlayer = players.get(players.size() - 1);
		assertEquals(players.get(0), tictactoe.getNextPlayer(lastPlayer));
	}

	@Test
	public void shouldReturnNextPlayerIfCurrentPlayerIsNotTheLastOne() {
		List<Player> players = buildPlayers();
		TicTacToe tictactoe = new TicTacToe(3, players);
		Player firstPlayer = players.get(0);
		assertEquals(players.get(1), tictactoe.getNextPlayer(firstPlayer));
	}

	@Test
	public void shouldReturnFalseIfIsNotADraw() {
		List<Player> players = buildPlayers();
		TicTacToe tictactoe = new TicTacToe(3, players);
		assertFalse(tictactoe.isDraw());
	}

	@Test
	public void shouldReturnFalseIfThereIsNoWinner() {
		List<Player> players = buildPlayers();
		TicTacToe tictactoe = new TicTacToe(3, players);

		assertFalse(tictactoe.hasEnded(players.get(0)));
	}

	@Test
	public void shouldReturnTrueIfThereIsWinnerAndNoDraw() {
		List<Player> players = buildPlayers();
		TicTacToe tictactoe = new TicTacToe(3, players);
		Player winner = players.get(0);
		for (int row = 0; row < tictactoe.getBoard().getSize(); row++) {
			tictactoe.getBoard().getPosition(row, 0).setPlayer(winner);
		}
		assertTrue(tictactoe.hasEnded(winner));
		assertEquals(winner, tictactoe.getWinner());
	}

	@Test
	public void shouldShowDrawIfThereIsNoWinner() {
		List<Player> players = buildPlayers();
		TicTacToe tictactoe = new TicTacToe(3, players);
		TicTacToe spyTictactoe = spy(tictactoe);
		doNothing().when(spyTictactoe).play(players.get(0));
		when(spyTictactoe.getConsole()).thenReturn(mock(ConsoleInOut.class));
		
		spyTictactoe.start();
		
		verify(spyTictactoe.getConsole(), times(1)).showDraw();
	}
	
	@Test
	public void shouldShowWinnerIfThereIsWinner() {
		List<Player> players = buildPlayers();
		TicTacToe tictactoe = new TicTacToe(3, players);
		TicTacToe spyTictactoe = spy(tictactoe);
		Player winner = players.get(0);
		doNothing().when(spyTictactoe).play(winner);
		when(spyTictactoe.getWinner()).thenReturn(winner);
		when(spyTictactoe.getConsole()).thenReturn(mock(ConsoleInOut.class));

		spyTictactoe.start();
		
		verify(spyTictactoe.getConsole(), times(1)).showWinner(winner);
	}
	
//	@Test
//	public void shouldPlayerPlayIfIsHuman() {
//		List<Player> players = buildPlayers();
//		TicTacToe tictactoe = mock(TicTacToe.class);//new TicTacToe(3, players);
//		TicTacToe spyTictactoe = spy(tictactoe);
//		Player currentPlayer = players.get(0);
//		doNothing().when(spyTictactoe).play(currentPlayer);
//
//		spyTictactoe.play(currentPlayer);
//
//		verify(spyTictactoe, times(1)).playerPosition(currentPlayer);
//	}

}
