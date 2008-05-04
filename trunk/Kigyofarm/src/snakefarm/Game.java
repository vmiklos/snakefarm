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
			//checkEnd();
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
	 * Balra forgatja a megadott jatekos aktualis kigyojat
	 * 
	 * @param playerId a jatekos azonositoja
	 */
	public void turnLeft(int playerId) {
		Player player = players.get(playerId);
		if (player != null) player.turnLeft();
	}

	/**
	 * Jobbra forgatja a megadott jatekos aktualis kigyojat
	 * 
	 * @param snakeId a jatekos azonositoja
	 */
	public void turnRight(int playerId) {
		Player player = players.get(playerId);
		if (player != null) player.turnRight();
	}
	
	public void switchToNextSnake(int playerId) {
		Player player = players.get(playerId);
		if (player != null) player.switchToNextSnake();
	}
	
	public void switchToPrevSnake(int playerId) {
		Player player = players.get(playerId);
		if (player != null) player.switchToPrevSnake();
	}
}
