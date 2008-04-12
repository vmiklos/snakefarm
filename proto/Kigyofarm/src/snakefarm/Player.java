package snakefarm;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Jatekos alaposztaly. Tartalmazza a jatekos kigyoit. Felel a sajat
 * kigyoinak a letrehozasaert.
 */
public class Player {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Player";
	private Game game;
	private List<Snake> snakes = new LinkedList<Snake>();
	private List<Snake> temp = new LinkedList<Snake>();

	/**
	 * A jatekos osztaly konstruktora.
	 *
	 * @param game a jatek amelyben a jatekos letrejon
	 */
	public Player(Game game) {
		lastid++;
		Skeleton.enterMethod(type, id, "Player(Game)");
		this.game = game;
		Skeleton.exitMethod(type, id, "Player(Game)");
	}

	/**
	 * Letrehoz egy uj kigyot, es a jatekoshoz, valamint a megadott
	 * mezohoz rendeli.
	 *
	 * @param container mezo, ahonnan indul majd a kigyo
	 */
	public void addSnake(Field container) {
		Skeleton.enterMethod(type, id, "addSnake(Field)");
		snakes.add(new Snake(this, container));
		Skeleton.exitMethod(type, id, "addSnake(Field)");
	}

	/**
	 * Lepteti a jatekost.
	 * <p>
	 * Ez konkretan azt jelenti, hogy a jatekos minden kigyojat
	 * lepteti.
	 */
	public void step() {
		Skeleton.enterMethod(type, id, "step()");
		temp.addAll(snakes);
		for (Iterator i = temp.iterator(); i.hasNext();) {
			((Snake) i.next()).step();
		}
		temp.clear();
		Skeleton.exitMethod(type, id, "step()");
	}

	/**
	 * Eltavolit egy kigyot a jatekostol.
	 */
	public void removeSnake(Snake snake) {
		Skeleton.enterMethod(type, id, "removeSnake(Snake)");
		snakes.remove(snake);
		Skeleton.exitMethod(type, id, "removeSnake(Snake)");
	}
}
