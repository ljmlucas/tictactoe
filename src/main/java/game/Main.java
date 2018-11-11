package game;

import java.io.IOException;
import java.util.List;

import game.exceptions.InvalidPlaygroundSizeException;
import game.model.Player;
import game.utils.ConfigFileReader;

public class Main {

	public static void main(String[] args) {
		ConfigFileReader configReader = new ConfigFileReader();
		
		String fileName = "config.txt";
		List<Player> players;
		try {
			players = configReader.readPlayers(fileName);
			int size = configReader.readPlaygroundSize(fileName);
			new TicTacToe(size, players).start();
		} catch (IOException e) {
			System.err.format("Exception occurred trying to read '%s'.", fileName);
		} catch (InvalidPlaygroundSizeException e) {
			System.err.format(e.getMessage());
		}
		
	}	
	
}
