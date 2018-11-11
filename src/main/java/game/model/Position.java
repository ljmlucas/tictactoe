package game.model;

public class Position {

	private Player player;
	private int row;
	private int column;
	
	public Position(int row, int column) {
		this.player = null;
		this.row = row;
		this.column = column;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public boolean isFilled() {
		return this.player != null;
	}

}
