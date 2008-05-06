package snakefarm;

import java.util.*;
import snakefarm.creators.PlayerCreator;
import snakefarm.creators.SnakeCreator;
import java.awt.Color;

/**
 * Jatekos alaposztaly. Tartalmazza a jatekos kigyoit. Felel a sajat
 * kigyoinak a letrehozasaert.
 */
public class Player implements Comparable {

	int cnt=0;
	private int currentSnake = 0;
	private Game game;
	private int numberOfSnakes;
	private LinkedList<Snake> snakes = new LinkedList<Snake>();
	private LinkedList<Snake> temp = new LinkedList<Snake>();
	private Color color;
	private int turn = 0;

	/**
	 * A jatekos osztaly konstruktora.
	 *
	 * @param game a jatek amelyben a jatekos letrejon
	 */
	public Player(Game game, PlayerCreator playerCreator) {
		this.game = game;
		numberOfSnakes = playerCreator.getNumberOfSnakes();
		for (int i = 0; i < numberOfSnakes; i++) {
			snakes.add(new Snake(this, playerCreator.getSnakeCreator(i)));
		}
		color = playerCreator.getColor();
	}

	public void addSnake(SnakeCreator snakeCreator) {
		snakes.addLast(new Snake(this, snakeCreator));
		numberOfSnakes++;
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
	 * Lepteti a jatekost.
	 * <p>
	 * Ez konkretan azt jelenti, hogy a jatekos minden kigyojat
	 * lepteti.
	 */
	public synchronized void step() {
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
			if (currentSnake == snakes.indexOf(snake)) {
				switchToNextSnake();
			} else if (currentSnake > snakes.indexOf(snake)) {
				currentSnake--;
			}
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
		return (numberOfSnakes > 0);
	}

	/**
	 * A jatekos aktualis kigyojat balra forgatja.
	 * 
	 */
	public synchronized void turnLeft() {
		if (numberOfSnakes == 0) return;
		Snake snake = snakes.get(currentSnake);
		if (snake != null) {
			snake.turnLeft();
		}
	}

	/**
	 * A jatekos aktualis kigyojat jobbra forgatja.
	 * 
	 */
	public synchronized void turnRight() {
		if (numberOfSnakes == 0) return;
		Snake snake = snakes.get(currentSnake);
		if (snake != null) {
			snake.turnRight();
		} else System.out.println("snake is null, " + currentSnake);
	}

	public synchronized void switchToNextSnake() {
		if (numberOfSnakes == 0) {
			currentSnake = 0;
		} else {
			currentSnake = (currentSnake + 1) % numberOfSnakes;
		}
	}

	public synchronized void switchToPrevSnake() {
		if (numberOfSnakes == 0) {
			currentSnake = 0;
		} else {
			currentSnake = (currentSnake + numberOfSnakes - 1) % numberOfSnakes;
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
		return color;
	}
}
