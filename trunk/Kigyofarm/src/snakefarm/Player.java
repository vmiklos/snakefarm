package snakefarm;

import java.util.*;

/**
 * Jatekos alaposztaly. Tartalmazza a jatekos kigyoit. Felel a sajat
 * kigyoinak a letrehozasaert.
 */
public class Player implements Comparable {

	private static int lastid = 0;
	private int id;
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
	 * Megadja egy jatekos azonositojat.
	 *
	 * @return az azonosito.
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Letrehoz egy uj kigyot, es a jatekoshoz, valamint a megadott
	 * mezohoz rendeli.
	 *
	 * @param id ha 0 akkor automatikusan kap azonositot a kigyo, ha
	 * nagyobb akkor a kivant azonosito
	 * @return az uj kigyo azonositoja
	 */
	public int addSnake(int id) {
		if(id>0)
			lastid = id;
		else
			id = ++lastid;
		snakes.add(new Snake(this, id));
		return id;
	}

	/**
	 * Elmenti a jatekos kigyoit.
	 */
	public void saveSnakes()
	{
		for (Iterator i = snakes.listIterator(); i.hasNext();) {
			Snake snake = (Snake) i.next();
			snake.save();
		}
	}

	/**
	 * Megadja a jatekos leghosszabb kigyojanak hosszat.
	 *
	 * @return a leghosszabb kigyo hossza
	 */
	public int getMaxLength()
	{
		int max = 0;
		for (Iterator i = snakes.listIterator(); i.hasNext();) {
			Snake snake = (Snake) i.next();
			if (snake.getLength() > max)
				max = snake.getLength();
		}
		return max;
	}

	/**
	 * Megadja a jatekos legkevesebb kovet tartalmazo kigyojaban
	 * levo kovek szamat.
	 *
	 * @return a kovek szama.
	 */
	public int getMinStoneLength()
	{
		int min = 0;
		for (Iterator i = snakes.listIterator(); i.hasNext();) {
			Snake snake = (Snake) i.next();
			if (min == 0 || snake.getStoneLength() < min)
				min = snake.getStoneLength();
		}
		return min;
	}

	/**
	 * Megmutat egy kigyot
	 *
	 * @param id a kigyo azonositoja
	 */
	public void showSnake(int id)
	{
		if (snakes != null)
		{
			for (Iterator i = snakes.listIterator(); i.hasNext();) {
				Snake snake = (Snake) i.next();
				if(snake.getId() == id)
					snake.show();
			}
		}
	}

	/**
	 * Visszaadja a jatekos egy kigyojat az azonositoja alapjan.
	 *
	 * @param id az azonosito
	 * @return null ha a jatekosnak nincs ilyen azonositoju kigyoja,
	 * vagy a kigyo referenciaja, ha van.
	 */
	public Snake getSnakeById(int id) {
		if (snakes != null)
		{
			for (Iterator i = snakes.listIterator(); i.hasNext();) {
				Snake snake = (Snake) i.next();
				if (snake.getId() == id) {
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
	 * Megmutatja a jatekost.
	 */
	public void show()
	{
		Proto.out.println("Player " + id);
		if (snakes != null)
		{
			for (Iterator i = snakes.listIterator(); i.hasNext();) {
				Snake snake = (Snake) i.next();
				int saw;
				if(snake.isSaw())
					saw = 1;
				else
					saw = 0;
				Proto.out.println(snake.getId() + " " + snake.getLength() + " " + saw + " " +
						snake.getControlSpeed() + " " + snake.getStoneSpeed());
			}
		}
		Proto.out.println("endplayer");
	}

	/**
	 * Eltavolit egy kigyot a jatekostol.
	 */
	public void removeSnake(Snake snake) {
		snakes.remove(snake);
	}

	/**
	 * Megmondja, hogy van-e a jatekosnak kigyoja.
	 *
	 * @return a fenti allitas igazsaga
	 */
	public boolean hasSnake()
	{
		if (snakes.size() > 0)
			return true;
		else
			return false;
	}

	/**
	 * Nyeresre szolitja fel a jatekost.
	 */
	public void win()
	{
		if(!Proto.debug)
			Proto.out.println("Event Win " + id);
	}

	/**
	 * A jatekos egy kigyojat balra forgatja.
	 *
	 * @param snakeId a kigyo azonositoja.
	 */
	public void turnLeft(int snakeId) {
		if (snakes != null)
		{
			for (Iterator i = snakes.listIterator(); i.hasNext();) {
				Snake snake = (Snake) i.next();
				if (snake.getId() == snakeId) {
					snake.turnLeft();
				}
			}
		}
	}

	/**
	 * A jatekos egy kigyojat jobbra forgatja.
	 *
	 * @param snakeId a kigyo azonositoja.
	 */
	public void turnRight(int snakeId) {
		if (snakes != null)
		{
			for (Iterator i = snakes.listIterator(); i.hasNext();) {
				Snake snake = (Snake) i.next();
				if (snake.getId() == snakeId) {
					snake.turnRight();
				}
			}
		}
	}

	/**
	 * Osszehasonlitja a jatekost egy masik jatekossal. Ha egy masik
	 * jatekos nagyobb az aztjelenti, hogy rosszabbul all, tehat
	 * vagy rovidebb vagy ugyanolyan hosszu mint mi, de tobb kove
	 * van.
	 *
	 * @param o a masik jatekos
	 * @return -1, 0, 1 aszerint, hogy melyik jatekos a nagyobb.
	 */
	public int compareTo (Object o)
	{
		Player b = (Player) o;
		List<Player> ret = new LinkedList<Player>();
		if(getMaxLength() != b.getMaxLength())
		{
			if(getMaxLength() > b.getMaxLength())
				return -1;
			else
				return 1;
		}
		else if(getMinStoneLength() != b.getMinStoneLength())
		{
			if(getMinStoneLength() > b.getMinStoneLength())
				return 1;
			else
				return -1;
		}
		else
			return 0;
	}
}
