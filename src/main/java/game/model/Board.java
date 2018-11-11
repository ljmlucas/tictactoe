package game.model;

import game.exceptions.InvalidPlaygroundSizeException;
import game.utils.Constants;

public class Board {

	private Position[][] positions;
	private int size;

	public Board(int size) {
		if(size < Constants.MIN_SIZE || size > Constants.MAX_SIZE) {
			throw new InvalidPlaygroundSizeException(Constants.MIN_SIZE, Constants.MAX_SIZE);
		}
		
		this.positions = new Position[size][size];
		this.size = size;

		buildEmptyPositions();
	}

	private void buildEmptyPositions() {
		for (int row = 0; row < size; ++row) {
			for (int column = 0; column < size; ++column) {
				positions[row][column] = new Position(row, column);
			}
		}
	}

	public Boolean isWinner(Player currentPlayer) {
		return isRowComplete(currentPlayer) || isColumnComplete(currentPlayer)
				|| isOppositeDiagonalComplete(currentPlayer) || isDiagonalComplete(currentPlayer);
	}

	public Boolean isDiagonalComplete(Player currentPlayer) {
		for (int position = 0; position < size; position++) {
			if (isPlayerNotInPosition(position, position, currentPlayer)) {
				return false;
			}
		}
		return true;
	}

	public Boolean isOppositeDiagonalComplete(Player currentPlayer) {
		int row = 0;
		for (int column = size - 1; column >= 0; column--) {
			if (isPlayerNotInPosition(row, column, currentPlayer)) {
				return false;
			}
			row++;
		}
		return true;
	}

	public Boolean isRowComplete(Player currentPlayer) {
		for (int row = 0; row < size; row++) {
			boolean valid = true;
			for (int column = 0; column < size; column++) {
				if (isPlayerNotInPosition(row, column, currentPlayer)) {
					valid = false;
				}
			}
			if (valid) {
				return true;
			}
		}
		return false;
	}

	public Boolean isColumnComplete(Player currentPlayer) {
		for (int column = 0; column < size; column++) {
			boolean valid = true;
			for (int row = 0; row < size; row++) {
				if (isPlayerNotInPosition(row, column, currentPlayer)) {
					valid = false;
				}
			}
			if (valid) {
				return true;
			}
		}
		return false;
	}

	private Boolean isPlayerNotInPosition(int row, int column, Player currentPlayer) {
		return getPosition(row, column).getPlayer() == null
				|| !currentPlayer.getSymbol().equals(getPosition(row, column).getPlayer().getSymbol());
	}

	public Position getPosition(int row, int column) {
		return getPositions()[row][column];
	}

	public Position[][] getPositions() {
		return positions;
	}

	public int getSize() {
		return size;
	}

}
