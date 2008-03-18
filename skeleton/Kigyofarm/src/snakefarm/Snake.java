package snakefarm;

import java.util.List;

/**
 * Kigyo osztaly, amely osszefoglalja az egy kigyohoz tartozo kigyo
 * egysegeket. Tarolja es valtoztatni tudja a kigyo iranyat.
 */
public class Snake {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Snake";
	private static final int sawCounterMax = 20;
	private Player player;
	private Direction direction;
	private List<SnakeUnit> units;
	private int sawCounter = 0;
	private boolean isAlive = true;

	/**
	 * A kigyo konstruktora.
	 *
	 * @param player a kigyot vezerlo jatekos
	 * @param field a mezo amelyre a kigyo kerul
	 */
	public Snake(Player player, Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "Snake(Player, Field)");
		this.player = player;
		units = Skeleton.getCurrentLevel().createSnake(this, id);
		direction = Skeleton.getCurrentLevel().setDirection(this);
		Skeleton.exitMethod(type, id, "Snake(Player, Field)");
	}

	/**
	 * Uj farok hozzaadasa a kigyohoz.
	 *
	 * @param tail az uj farok, amely a regi farok utan lesz fuzve
	 */
	public void addSnakeUnit(SnakeUnit tail) {
		Skeleton.enterMethod(type, id, "addSnakeUnit(SnakeUnit)");
		units.add(tail);
		Skeleton.exitMethod(type, id, "addSnakeUnit(SnakeUnit)");
	}

	/**
	 * Kigyoelem torlese a kigyobol.
	 *
	 * @param unit melyik elem legyen torolve
	 */
	public void removeSnakeUnit(SnakeUnit unit) {
		Skeleton.enterMethod(type, id, "removeSnakeUnit(SnakeUnit)");
		units.remove(unit);
		Skeleton.exitMethod(type, id, "removeSnakeUnit(SnakeUnit)");
	}

	/**
	 * Megadja, hogy melyik jatekoshoz tartozik a kigyo.
	 *
	 * @return a jatekos
	 */
	public Player getPlayer() {
		Skeleton.enterMethod(type, id, "getPlayer()");
		Skeleton.exitMethod(type, id, "getPlayer()");
		return player;
	}

	/**
	 * Beallitja a kigyo furesz-szamlalojat, tehat hogy meg hany
	 * lepesik van furesz modban a kigyo.
	 */
	public void setSawCounter() {
		Skeleton.enterMethod(type, id, "setSawCounter()");
		sawCounter = sawCounterMax;
		Skeleton.exitMethod(type, id, "setSawCounter()");
	}

	/**
	 * Balra forditja a kigyot.
	 */
	public void turnLeft() {
		Skeleton.enterMethod(type, id, "turnLeft()");
		direction.turnLeft();
		Skeleton.exitMethod(type, id, "turnLeft()");
	}

	/**
	 * Jobbra forditja a kigyot.
	 */
	public void turnRight() {
		Skeleton.enterMethod(type, id, "turnRight()");
		direction.turnRight();
		Skeleton.exitMethod(type, id, "turnRight()");
	}

	/**
	 * Megmondja, hogy furesz modban van-e a kigyo.
	 *
	 * @return logikai ertek a kerdes megvalaszolasara
	 */
	public boolean isSaw() {
		Skeleton.enterMethod(type, id, "isSaw()");
		Skeleton.exitMethod(type, id, "isSaw()");
		return (sawCounter > 0);
	}

	/**
	 * Leptet egyet a kigyon.
	 */
	public void step() {
		Skeleton.enterMethod(type, id, "step()");
		if (isAlive) {
			int control = Skeleton.askForControl();
			if (control == 1) {
				turnLeft();
			}
			if (control == -1) {
				turnRight();
			}
			if (sawCounter > 0) {
				sawCounter--;
			}
			SnakeUnit head = units.get(0);
			Field next = head.getNextField(direction);
			head.step(next, false, false);
		}
		Skeleton.exitMethod(type, id, "step()");
	}

	/**
	 * Megoli a kigyot.
	 */
	public void die() {
		Skeleton.enterMethod(type, id, "die()");
		if (isAlive) {
			isAlive = false;
			/*for (Iterator i = units.iterator(); i.hasNext();) {
			((SnakeUnit) (i.next())).die();
			}*/
			player.removeSnake(this);
		}
		Skeleton.exitMethod(type, id, "die()");
	}
}
