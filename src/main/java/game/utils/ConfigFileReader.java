package game.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.model.Player;

public class ConfigFileReader {

	public List<Player> readPlayers(String fileName) throws IOException {
		List<Player> players = new ArrayList<Player>();
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String labelPlayerId = "player";
		String labelComputerId = "computer";
		String line;
		while ((line = reader.readLine()) != null) {
			String[] playerLine = line.split(":");
			if (playerLine[0].contains(labelPlayerId)) {
				players.add(new Player(playerLine[1].trim(), true));
			} else if (playerLine[0].contains(labelComputerId)) {
				players.add(new Player(playerLine[1].trim(), false));
			}
		}
		reader.close();

		Collections.shuffle(players);
		players.forEach(player -> player.setTurn(players.indexOf(player)));

		return players;
	}

	public int readPlaygroundSize(String fileName) throws IOException {
		int size = 3;
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		String labelId = "size:";
		while ((line = reader.readLine()) != null && line.contains(labelId)) {
			size = Integer.parseInt(line.split(":")[1].trim());
		}
		reader.close();
		return size;
	}

}
