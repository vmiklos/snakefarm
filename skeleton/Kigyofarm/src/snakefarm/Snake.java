package snakefarm;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class Snake {

	private static int lastid = 0;
	private int id = lastid;
	private SkeletonInterface si;
	private static final String type = "Snake";
	private static final int sawCounterMax = 20;
	private Player player;
	private Direction direction;
	private List<SnakeUnit> units = new LinkedList<SnakeUnit>();
	private int sawCounter = 0;
	private boolean isAlive = true;

	public Snake(SkeletonInterface si, Player player, Field field) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "Snake(Player, Field)");
		this.player = player;
		direction = new Direction(si, 0);
		units.add(new SnakeUnit(si, this, field));
		si.exitMethod(type, id, "Snake(Player, Field)");
	}

	public void addSnakeUnit(SnakeUnit tail) {
		si.enterMethod(type, id, "addSnakeUnit(SnakeUnit)");
		units.add(tail);
		si.exitMethod(type, id, "addSnakeUnit(SnakeUnit)");
	}

	public void removeSnakeUnit(SnakeUnit unit) {
		si.enterMethod(type, id, "removeSnakeUnit(SnakeUnit)");
		units.remove(unit);
		si.exitMethod(type, id, "removeSnakeUnit(SnakeUnit)");
	}

	public Player getPlayer() {
		si.enterMethod(type, id, "getPlayer()");
		si.exitMethod(type, id, "getPlayer()");
		return player;
	}

	public void setSawCounter() {
		si.enterMethod(type, id, "setSawCounter()");
		sawCounter = sawCounterMax;
		si.exitMethod(type, id, "setSawCounter()");
	}

	public void turnLeft() {
		si.enterMethod(type, id, "turnLeft()");
		direction.turnLeft();
		si.exitMethod(type, id, "turnLeft()");
	}

	public void turnRight() {
		si.enterMethod(type, id, "turnRight()");
		direction.turnRight();
		si.exitMethod(type, id, "turnRight()");
	}

	public boolean isSaw() {
		si.enterMethod(type, id, "isSaw()");
		si.exitMethod(type, id, "isSaw()");
		return (sawCounter > 0);
	}

	public void step() {
		si.enterMethod(type, id, "step()");
		if (isAlive) {
			if (sawCounter > 0) {
				sawCounter--;
			}
			SnakeUnit head = units.get(0);
			Field next = head.getNextField(direction);
			head.step(next, false, false);
		}
		si.exitMethod(type, id, "step()");
	}

	public void die() {
		si.enterMethod(type, id, "die()");
		if (isAlive) {
			isAlive = false;
			for (Iterator i = units.iterator(); i.hasNext();) {
				((SnakeUnit) (i.next())).die();
			}
			player.removeSnake(this);
		}
		si.exitMethod(type, id, "die()");
	}

	/*public Field getNext() {
	si.enterMethod(type, id, "getNext()");
	si.error("METHOD NOT IMPLEMENTED");
	si.exitMethod(type, id, "getNext()");
	return null;
	}*/
}
