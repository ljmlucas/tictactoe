package game;

import java.io.IOException;
import java.util.List;

import game.exceptions.InvalidPlaygroundSizeException;
import game.model.Player;
import game.utils.ConfigFileReader;
import game.utils.Constants;

public class Main {

	public static void main(String[] args) {
		ConfigFileReader configReader = new ConfigFileReader();
		
		List<Player> players;
		try {
			players = configReader.readPlayers(Constants.CONFIG_FILE);
			int size = configReader.readPlaygroundSize(Constants.CONFIG_FILE);
			new TicTacToe(size, players).start();
		} catch (IOException e) {
			System.err.format("Exception occurred trying to read '%s'.", Constants.CONFIG_FILE);
		} catch (InvalidPlaygroundSizeException e) {
			System.err.format(e.getMessage());
		}
		
	}	
	
}
