package game.utils;

import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import game.model.Board;
import game.model.Player;
import game.model.Position;

public class ConsoleInOut {

	private static Scanner in = new Scanner(System.in);

	public void showBoard(Board board) {
		System.out.println();
		for (int column = 0; column < board.getSize(); ++column) {
			System.out.print("  " + column + " ");
		}
		System.out.println();
		for (int row = 0; row < board.getSize(); ++row) {
			System.out.print(row);
			for (int column = 0; column < board.getSize(); ++column) {
				Player player = board.getPositions()[row][column].getPlayer();
				if (player != null) {
					System.out.print(" " + player.getSymbol() + " ");
				} else {
					System.out.print("   ");
				}
				if (column < board.getSize() - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (row < board.getSize() - 1) {
				System.out.println(StringUtils.repeat("----", board.getSize()));
			}
		}
		System.out.println();
	}

	public Position askPosition(String symbol, int size) {
		System.out.println("Player " + symbol + ", enter a position: ");
		
		String positionLine = readPosition();
		Position position = extractPosition(positionLine);
		
		while(!isPositionValid(position)) {
			System.out.println("The input format is not valid, the input should be row,colum (1,2):");
			positionLine = readPosition();
			position = extractPosition(positionLine);
		}

		return position;
	}

	private String readPosition() {
		String positionLine = in.next();
		while(!isInputValid(positionLine)) {
			System.out.println("The input format is not valid, the input should be row,colum (1,2):");
			positionLine = in.next();
		}
		return positionLine;
	}
	
	public Position extractPosition(String positionLine) {
		int row = Integer.valueOf(positionLine.split(",")[0]);
		int column = Integer.valueOf(positionLine.split(",")[1]);
		return new Position(row, column);
	}
	
	public boolean isPositionValid(Position position) {
		return position.getRow() >= 0 && position.getRow() < Constants.MAX_SIZE && position.getColumn() >= 0 && position.getColumn() < Constants.MAX_SIZE;
	}
	
	public boolean isInputValid(String positionIn) {
		return positionIn.matches(Constants.VALID_INPUT_POSITION);
	}

	public void showWinner(Player winner) {
		System.out.println("We have a WINNER: " + winner.getSymbol());
	}

	public void showDraw() {
		System.out.println("Oh!!! It is a draw.");
	}

	public void showTryAgain(String message) {
		System.out.println(message + " Try again...");
	}

	public void showIntructions(List<Player> players) {
		System.out.println("TIC TAC TOE");
		System.out.println("Players:");
		players.forEach(player -> showListOfPlayers(player));
	}

	private void showListOfPlayers(Player player) {
		String label = player.isHuman() ? "" : " (CPU)"; 
		System.out.println((player.getTurn() + 1) + " - " + player.getSymbol() + label);
	}

	public void computerTurn(String symbol) {
		System.out.println("Computer Turn " + symbol);
	}

	public void showPositionFilled(Position position) {
		System.out.println("The position " + position.getRow() + "," + position.getColumn()+ " is already filled. Try another position...");
	}

}
