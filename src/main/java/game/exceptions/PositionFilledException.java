package game.exceptions;

public class PositionFilledException extends RuntimeException {

	private static final long serialVersionUID = 1379746141355906002L;

	public PositionFilledException(int row, int column) {
		super("The position " + row + "," + column + " is already filled.");
	}
	
}
