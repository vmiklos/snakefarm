package snakefarm;

import java.util.*;
import snakefarm.creators.PlayerCreator;

/**
 * Jatekos alaposztaly. Tartalmazza a jatekos kigyoit. Felel a sajat
 * kigyoinak a letrehozasaert.
 */
public class Player implements Comparable {

	private int id = 0;
	private Game game;
	private int numberOfSnakes;
	private List<Snake> snakes = new LinkedList<Snake>();
	private List<Snake> temp = new LinkedList<Snake>();

	/**
	 * A jatekos osztaly konstruktora.
	 *
	 * @param game a jatek amelyben a jatekos letrejon
	 */
	public Player(Game game, PlayerCreator playerCreator) {
		this.game = game;
		numberOfSnakes = playerCreator.getNumberOfSnakes();
		for (int i=0; i<numberOfSnakes; i++) {
			snakes.add(new Snake(this, playerCreator.getSnakeCreator(i)));
		}
		//this.id = id;
	}

	/**
	 * Megadja egy jatekos azonositojat.
	 *
	 * @return az azonosito.
	 */
	public int getId() {
		return id;
	}

	public void addSnake(Snake snake) {
		if (!snakes.contains(snake)) {
			snakes.add(snake);
			numberOfSnakes++;
		}
	}

	/**
	 * Megadja a jatekos leghosszabb kigyojanak hosszat.
	 *
	 * @return a leghosszabb kigyo hossza
	 */
	public int getMaxLength() {
		int max = 0;
		for (Iterator i = snakes.listIterator(); i.hasNext();) {
			Snake snake = (Snake) i.next();
			if (snake.getLength() > max) {
				max = snake.getLength();
			}
		}
		return max;
	}

	/**
	 * Megadja a jatekos legkevesebb kovet tartalmazo kigyojaban
	 * levo kovek szamat.
	 *
	 * @return a kovek szama.
	 */
	public int getMinStoneLength() {
		int min = 0;
		for (Iterator i = snakes.listIterator(); i.hasNext();) {
			Snake snake = (Snake) i.next();
			if (min == 0 || snake.getStoneLength() < min) {
				min = snake.getStoneLength();
			}
		}
		return min;
	}

	/**
	 * Visszaadja a jatekos egy kigyojat az azonositoja alapjan.
	 *
	 * @param id az azonosito
	 * @return null ha a jatekosnak nincs ilyen azonositoju kigyoja,
	 * vagy a kigyo referenciaja, ha van.
	 */
	public Snake getSnakeById(int id) {
		if (snakes != null) {
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
			// ezt azert kell igy, mert lehet, hogy kozben mar meghalt...
			Snake s = (Snake) i.next();
			if (snakes.contains(s)) {
				s.step();
			}
		}
		temp.clear();
	}

	/**
	 * Eltavolit egy kigyot a jatekostol.
	 */
	public void removeSnake(Snake snake) {
		if (snakes.contains(snake)) {
			snakes.remove(snake);
			numberOfSnakes--;
		}
	}

	/**
	 * Megmondja, hogy van-e a jatekosnak kigyoja.
	 *
	 * @return a fenti allitas igazsaga
	 */
	public boolean hasSnake() {
		if (snakes.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A jatekos egy kigyojat balra forgatja.
	 *
	 * @param snakeId a kigyo azonositoja.
	 */
	public void turnLeft(int snakeId) {
		if (snakes != null) {
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
		if (snakes != null) {
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
	public int compareTo(Object o) {
		Player b = (Player) o;
		//List<Player> ret = new LinkedList<Player>();
		if (getMaxLength() != b.getMaxLength()) {
			if (getMaxLength() > b.getMaxLength()) {
				return -1;
			} else {
				return 1;
			}
		} else if (getMinStoneLength() != b.getMinStoneLength()) {
			if (getMinStoneLength() > b.getMinStoneLength()) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return 0;
		}
	}

	public java.awt.Color getColor() {
		/*FIXME szin szamitas megirasa*/
		return java.awt.Color.green;
		//throw new UnsupportedOperationException("Not yet implemented");
	}
}
