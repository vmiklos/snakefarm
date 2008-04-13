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
	public int id;
	private static final String type = "Player";
	private Game game;
	private List<Snake> snakes = new LinkedList<Snake>();
	private List<Snake> temp = new LinkedList<Snake>();

	/**
	 * A jatekos osztaly konstruktora.
	 *
	 * @param game a jatek amelyben a jatekos letrejon
	 */
	public Player(Game game, int id) {
		this.game = game;
		this.id = id;
	}

	/**
	 * Letrehoz egy uj kigyot, es a jatekoshoz, valamint a megadott
	 * mezohoz rendeli.
	 */
	public void addSnake(int id) {
		snakes.add(new Snake(this, id));
	}

	public void saveSnakes()
	{
		for (Iterator i = snakes.listIterator(); i.hasNext();) {
			Snake snake = (Snake) i.next();
			snake.save();
		}
	}

	public void showSnake(int id)
	{
		if (snakes != null)
		{
			for (Iterator i = snakes.listIterator(); i.hasNext();) {
				Snake snake = (Snake) i.next();
				if(snake.id == id)
					snake.show();
			}
		}
	}
	public Snake getSnakeById(int id) {
		if (snakes != null)
		{
			for (Iterator i = snakes.listIterator(); i.hasNext();) {
				Snake snake = (Snake) i.next();
				if (snake.id == id) {
					return snake;
				}
			}
		}
		return null;
	}
	/**
	 * Lepteti a jatekost.
	 * <p>
	 * Ez konkretan azt jelenti, hogy a jatekos minden kigyojat
	 * lepteti.
	 */
	public void step() {
		temp.addAll(snakes);
		for (Iterator i = temp.iterator(); i.hasNext();) {
			((Snake) i.next()).step();
		}
		temp.clear();
	}

	/**
	 * Eltavolit egy kigyot a jatekostol.
	 */
	public void removeSnake(Snake snake) {
		snakes.remove(snake);
	}

	public boolean hasSnake()
	{
		if (snakes.size() > 0)
			return true;
		else
			return false;
	}

	public void win()
	{
		if(!Proto.debug)
			Proto.out.println("Event Win " + id);
	}
	public void turnLeft(int snakeId) {
		if (snakes != null)
		{
			for (Iterator i = snakes.listIterator(); i.hasNext();) {
				Snake snake = (Snake) i.next();
				if (snake.id == id) {
					snake.turnLeft();
				}
			}
		}
	}
}
