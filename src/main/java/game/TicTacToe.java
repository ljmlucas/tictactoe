package game;

import java.util.List;
import java.util.Random;

import game.model.Board;
import game.model.Player;
import game.model.Position;
import game.utils.ConsoleInOut;

public class TicTacToe {

	private	ConsoleInOut console;
	private Board board;
	private List<Player> players;
	private Player winner;
	private int maxMoves;
	private int moves;

	public TicTacToe(int size, List<Player> players) {
		this.console = new ConsoleInOut();
		this.board = new Board(size);
		this.players = players;
		this.maxMoves = size * size;
		this.moves = 0;
		this.winner = null;
	}

	protected void start() {
		getConsole().showIntructions(this.players);

		Player currentPlayer = players.get(0);
		play(currentPlayer);

		if (getWinner() != null) {
			getConsole().showWinner(getWinner());
		} else {
			getConsole().showDraw();
		}

	}

	protected void play(Player currentPlayer) {
		getConsole().showBoard(board);
		if (currentPlayer.isHuman()) {
			playerPosition(currentPlayer);
		} else {
			computerPosition(currentPlayer);
		}

		if (!hasEnded(currentPlayer)) {
			play(getNextPlayer(currentPlayer));
		}
	}

	protected Player getNextPlayer(Player currentPlayer) {
		if (currentPlayer.getTurn() == players.size() - 1) {
			return players.get(0);
		} else {
			return players.get(currentPlayer.getTurn() + 1);
		}
	}

	protected Boolean hasEnded(Player currentPlayer) {
		if (board.isWinner(currentPlayer)) {
			this.winner = currentPlayer;
			return true;
		}
		return isDraw();
	}

	protected Boolean isDraw() {
		return getMoves() == getMaxMoves();
	}

	public void playerPosition(Player player) {
		Position askedPosition = getConsole().askPosition(player.getSymbol(), board.getSize());
		Position boardPosition = board.getPosition(askedPosition.getRow(), askedPosition.getColumn());
		if(!boardPosition.isFilled()) {
			boardPosition.setPlayer(player);
			this.moves++;
		} else {
			getConsole().showPositionFilled(boardPosition);
			playerPosition(player);
		}
	}

	private void computerPosition(Player player) {
		getConsole().computerTurn(player.getSymbol());
		Random random = new Random();
		boolean valid = false;
		while (!valid) {
			int row = random.nextInt(board.getSize());
			int column = random.nextInt(board.getSize());
			Position position = board.getPosition(row, column);
			if (!position.isFilled()) {
				position.setPlayer(player);
				valid = true;
				this.moves++;
			}
		}
	}
	
	protected Integer getMoves() {
		return this.moves;
	}

	protected Integer getMaxMoves() {
		return this.maxMoves;
	}
	
	protected Board getBoard() {
		return this.board;
	}
	
	protected Player getWinner() {
		return this.winner;
	}

	protected ConsoleInOut getConsole() {
		return this.console;
	}
	
}
