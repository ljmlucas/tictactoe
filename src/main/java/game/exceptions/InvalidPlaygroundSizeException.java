package game.exceptions;

public class InvalidPlaygroundSizeException extends RuntimeException {

	private static final long serialVersionUID = -2218178777991205412L;

	public InvalidPlaygroundSizeException(int min, int max) {
		super("The size of playground is not valid. It should be between " + min + " and " + max );
	}
	
	
}
