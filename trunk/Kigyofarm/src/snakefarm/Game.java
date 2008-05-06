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
	private int stepsLimit;
	private int stepsElapsed=0;
	private boolean isPlaying = true;

	/**
	 * A jatek konstruktora.
	 */
	public Game(GameCreator gameCreator) {
		gameField = new GameField(this, gameCreator.getGameFieldCreator());
		stepsLimit=gameCreator.getStepsLimit();
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
	public boolean checkEnd() {
		int countAlive = 0;
		if (stepsElapsed == stepsLimit) return true;
		for (Iterator i = players.iterator(); i.hasNext();) {
			Player player = (Player) i.next();
			if (player.hasSnake()) {
				countAlive++;
			}
		}
		if (countAlive <= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Leptet egyet minden jatekoson.
	 */
	public void step() {
		if (isPlaying) {
			for (java.util.Iterator i = players.iterator(); i.hasNext();) {
				((Player) (i.next())).step();
				stepsElapsed++;
			}
			checkEnd();
		}
	}

	/**
	 * Veget vet a jateknak.
	 */
	public LinkedList<Player> getWinners() {
		LinkedList<Player> winners = new LinkedList<Player>();
		Collections.sort((List) players);
		for (Iterator i = players.iterator(); i.hasNext();) {
			Player player = (Player) i.next();
			if (player.compareTo(players.get(0)) == 0) {
				winners.add(player);
			}
		}
		return winners;
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
