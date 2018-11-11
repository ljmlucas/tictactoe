package game.model;

public class Player {

	private String symbol;
	private boolean human;
	private int turn;
	
	public Player(String symbol, boolean human) {
		this.symbol = symbol;
		this.human = human;
	}

	public Player(String symbol, boolean human, int turn) {
		this(symbol, human);
		this.turn = turn;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public boolean isHuman() {
		return human;
	}

	public void setHuman(boolean human) {
		this.human = human;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
	
}
