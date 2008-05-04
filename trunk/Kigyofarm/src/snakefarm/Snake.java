package snakefarm;

import java.util.*;
import snakefarm.creators.SnakeCreator;

/**
 * Kigyo osztaly, amely osszefoglalja az egy kigyohoz tartozo kigyo
 * egysegeket. Tarolja es valtoztatni tudja a kigyo iranyat.
 */
public class Snake {

	private int sawCounterMax;
	private Player player;
	private Direction direction = new Direction(0);
	private List<SnakeUnit> units;
	private int sawCounter = 0;
	private boolean isAlive = true;
	private int controlPhase = 0;
	private int controlSpeed = 0;
	private int stonePhase = 0;
	private int stoneSpeed = 0;
	private boolean turnedInThisStep = false;

	/**
	 * A kigyo konstruktora.
	 *
	 * @param player a kigyot vezerlo jatekos
	 */
	public Snake(Player player, SnakeCreator snakeCreator) {
		snakeCreator.setSnake(this);
		this.player = player;
		stoneSpeed = snakeCreator.getStoneSpeed();
		controlSpeed = snakeCreator.getControlSpeed();
		stonePhase = snakeCreator.getStonePhase();
		controlPhase = snakeCreator.getControlPhase();
		sawCounterMax = snakeCreator.getSawCounterMax();
		sawCounter = snakeCreator.getSawCounter();
		direction = snakeCreator.getDirection();
		units = snakeCreator.getSnakeUnits();
	}

	/**
	 * Uj farok hozzaadasa a kigyohoz.
	 *
	 * @param tail az uj farok, amely a regi farok utan lesz fuzve
	 */
	public void addSnakeUnit(SnakeUnit tail) {
		if (units.size() > 0) {
			SnakeUnit su = (SnakeUnit) units.get(units.size() - 1);
			su.setNextUnit(tail);
			tail.setPrevUnit(su);
		}
		units.add(tail);
		tail.setSnake(this);
		//tail.setId(units.size());
		SnakeUnit i = tail.getNextUnit();
		while (i != null) {
			units.add(i);
			i.setSnake(this);
			i = i.getNextUnit();
		}
	}

	/**
	 * Kigyoelem torlese a kigyobol.
	 *
	 * @param unit melyik elem legyen torolve
	 */
	public void removeSnakeUnit(SnakeUnit unit) {
		units.remove(unit);
		if (unit.getPrevUnit() != null) {
			unit.getPrevUnit().setNextUnit(null);
			unit.setPrevUnit(null);
		}
		SnakeUnit i = unit.getNextUnit();
		while (i != null) {
			units.remove(i);
			i = i.getNextUnit();
		}
	}

	/**
	 * Megadja, hogy melyik jatekoshoz tartozik a kigyo.
	 *
	 * @return a jatekos
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Beallitja a kigyo furesz-szamlalojat, tehat hogy meg hany
	 * lepesik van furesz modban a kigyo.
	 */
	public void setSawCounter(int sawCounter) {
		this.sawCounter = sawCounter;
	}

	/**
	 * Beallitja a kigyo iranyat.
	 *
	 * @param direction az uj irany
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Megadja a kigyo iranyat
	 *
	 * @return a kigyo iranya
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * Beallitja a kigyo sebesseget
	 *
	 * @param controlSpeed az uj sebesseg.
	 */
	public void setControlSpeed(int controlSpeed) {
		this.controlSpeed = controlSpeed;
	}

	/**
	 * Beallitja a kovek sebesseget
	 *
	 * @param az uj sebesseg
	 */
	public void setStoneSpeed(int stoneSpeed) {
		this.stoneSpeed = stoneSpeed;
	}

	/**
	 * Balra forditja a kigyot.
	 */
	public void turnLeft() {
		if (!turnedInThisStep) direction.turnLeft();
		turnedInThisStep = true;
	}

	/**
	 * Jobbra forditja a kigyot.
	 */
	public void turnRight() {
		if (!turnedInThisStep) direction.turnRight();
		turnedInThisStep = true;
	}

	/**
	 * Megmondja, hogy furesz modban van-e a kigyo.
	 *
	 * @return logikai ertek a kerdes megvalaszolasara
	 */
	public boolean isSaw() {
		return (sawCounter > 0);
	}

	/**
	 * Leptet egyet a kigyon.
	 */
	public void step() {
		if (isAlive) {
			boolean stepped = false;
			SnakeUnit head = units.get(0);
			controlPhase++;
			if (controlPhase == controlSpeed) {
				controlPhase = 0;
				stepped = true;
				Field next = head.getNextField(direction);
				head.step(next, false);
			}
			if (stepped && sawCounter > 0) {
				sawCounter--;
			}
			stonePhase++;
			if (isAlive && stonePhase == stoneSpeed) {
				stonePhase = 0;
				head.stoneStep(false);
			}
			turnedInThisStep = false;
		}
	}

	/**
	 * Megoli a kigyot.
	 */
	public void die() {
		if (isAlive) {
			isAlive = false;
			player.removeSnake(this);
		}
	}

	/**
	 * Megadja a kigyo hosszat.
	 *
	 * @return a kigyo hossza
	 */
	public int getLength() {
		return units.size();
	}

	/**
	 * Megadja a kigyo kohosszat, tehat hogy hany ko van a kigyoban.
	 *
	 * @return a kohossz
	 */
	public int getStoneLength() {
		int ret = 0;
		for (Iterator i = units.listIterator(); i.hasNext();) {
			SnakeUnit su = (SnakeUnit) i.next();
			if (su.hasStone()) {
				ret++;
			}
		}
		return ret;
	}

	/**
	 * Megadja a kigyo sebesseget.
	 *
	 * @return a sebesseg
	 */
	public int getControlSpeed() {
		return controlSpeed;
	}

	/**
	 * Megadja a kigyo koveinek sebesseget.
	 *
	 * @return a sebesseg.
	 */
	public int getStoneSpeed() {
		return stoneSpeed;
	}

	/**
	 * Megadja a kigyo furesszamlalojat, tehat hogy hany lepesig
	 * lesz meg fureszmodban.
	 *
	 * @return a szamlalo allasa
	 */
	public int getSawCounter() {
		return sawCounter;
	}

	/**
	 * Megadja a kigyo furesszamlalojanak maximalis erteket
	 *
	 * @return a szamlalo maximuma
	 */
	public int getSawCounterMax() {
		return sawCounterMax;
	}
}
