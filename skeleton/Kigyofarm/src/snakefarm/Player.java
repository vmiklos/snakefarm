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
	 * Megmondja, hogy egy mezorol, hogy a rajta levo kigyo a
	 * jatekoshoz tartozik.
	 *
	 * @param field a mezo
	 */
	public void addSnake(Field field) {
		/* FIXME: ez baromsag, ha egyszer mezot adunk meg akkor
		 * miert addSnake.. */
		Skeleton.enterMethod(type, id, "addSnake(Field)");
		snakes.add(new Snake(this, field));
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
