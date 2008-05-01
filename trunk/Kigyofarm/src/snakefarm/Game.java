package snakefarm;

import java.util.*;
import snakefarm.creators.GameCreator;

/**
 * Osszefogja a jatekteret es a jatekosokat, valamint inicializalja a
 * jatekot.
 * <p>
 * Megvalositja az utemezest is.
 */
public class Game {

	private GameField gameField;
	private int numberOfPlayers;
	private List<Player> players = new LinkedList<Player>();

	/**
	 * A jatek konstruktora.
	 */
	public Game(GameCreator gameCreator) {
		gameField = new GameField(this, gameCreator.getGameFieldCreator());
		gameCreator.setGameField(gameField);
		numberOfPlayers = gameCreator.getNumberOfPlayers();
		for (int i=0; i<numberOfPlayers; i++) {
			players.add(new Player(this, gameCreator.getPlayerCreator(i)));
		}
	}

	/**
	 * Visszaadja a jatek jatekteret.
	 *
	 * @return a jatekter
	 */
	public GameField getGameField() {
		return gameField;
	}

	/**
	 * Megnezi, hogy vege van-e a jateknak.
	 */
	private boolean checkEnd() {
		int countAlive = 0;
		Player lastAlive = null;
		for (Iterator i = players.iterator(); i.hasNext();) {
			Player player = (Player) i.next();
			if (player.hasSnake()) {
				countAlive++;
				lastAlive = player;
			}
		}
		if (countAlive == 1) {
			//lastAlive.win();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Leptet egyet minden jatekoson.
	 */
	public void step() {
		for (java.util.Iterator i = players.iterator(); i.hasNext();) {
			((Player) (i.next())).step();
			checkEnd();
		}
	}

	/**
	 * Veget vet a jateknak.
	 */
	// WTF ez a metodus? FIXME csinalni vele valamit
	public void end() {
		Collections.sort((List) players);
		int max = players.get(0).getMaxLength();
		int minStone = players.get(0).getMinStoneLength();
		for (Iterator i = players.iterator(); i.hasNext();) {
			Player player = (Player) i.next();
			if (player.compareTo(players.get(0)) == 0) {
				//player.win();
			}
		}
	}

	/**
	 * Megkeres egy jatekost az azonositoja alapjan.
	 *
	 * @param id azonosito
	 * @return a jatekos
	 */
	public Player getPlayerById(int id) {
		if (players != null) {
			for (Iterator i = players.listIterator(); i.hasNext();) {
				Player player = (Player) i.next();
				if (player.getId() == id) {
					return player;
				}
			}
		}
		return null;
	}

	/**
	 * Balra forgat egy kigyot
	 *
	 * @param snakeId a kigyo azonositoja
	 */
	public void turnLeft(int snakeId) {
		if (players != null) {
			for (Iterator i = players.listIterator(); i.hasNext();) {
				Player player = (Player) i.next();
				player.turnLeft(snakeId);
			}
		}
	}

	/**
	 * Jobbra forgat egy kigyot
	 * 
	 * @param snakeId a kigyo azonositoja
	 */
	public void turnRight(int snakeId) {
		if (players != null) {
			for (Iterator i = players.listIterator(); i.hasNext();) {
				Player player = (Player) i.next();
				player.turnRight(snakeId);
			}
		}
	}
}
