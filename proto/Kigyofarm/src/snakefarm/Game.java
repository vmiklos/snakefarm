package snakefarm;

import java.util.*;

/**
 * Osszefogja a jatekteret es a jatekosokat, valamint inicializalja a
 * jatekot.
 * <p>
 * Megvalositja az utemezest is.
 */
public class Game {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Game";
	public GameField gameField;
	private List<Player> players = new LinkedList<Player>();

	/**
	 * A jatek konstruktora.
	 */
	public Game() {
		lastid++;
		gameField = new GameField(this);
	}

	/**
	 * Letrehoz egy uj jatekost a jatekban.
	 */
	public void newPlayer(int id) {
		Player player = new Player(this, id);
		players.add(player);
	}

	/**
	 * Leptet egyet minden jatekoson.
	 */
	public void step() {
		for (java.util.Iterator i = players.iterator(); i.hasNext();) {
			((Player) (i.next())).step();
		}
	}

	public Player getPlayerById(int id) {
		if (players != null)
		{
			for (Iterator i = players.listIterator(); i.hasNext();) {
				Player player = (Player) i.next();
				if (player.id == id) {
					return player;
				}
			}
		}
		return null;
	}

}
