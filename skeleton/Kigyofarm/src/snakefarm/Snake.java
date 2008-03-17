package snakefarm;

import java.util.List;

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

	public Snake(Player player, Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "Snake(Player, Field)");
		this.player = player;
		units = Skeleton.getCurrentLevel().createSnake(this, id);
		direction = Skeleton.getCurrentLevel().setDirection(this);
		Skeleton.exitMethod(type, id, "Snake(Player, Field)");
	}

	public void addSnakeUnit(SnakeUnit tail) {
		Skeleton.enterMethod(type, id, "addSnakeUnit(SnakeUnit)");
		units.add(tail);
		Skeleton.exitMethod(type, id, "addSnakeUnit(SnakeUnit)");
	}

	public void removeSnakeUnit(SnakeUnit unit) {
		Skeleton.enterMethod(type, id, "removeSnakeUnit(SnakeUnit)");
		units.remove(unit);
		Skeleton.exitMethod(type, id, "removeSnakeUnit(SnakeUnit)");
	}

	public Player getPlayer() {
		Skeleton.enterMethod(type, id, "getPlayer()");
		Skeleton.exitMethod(type, id, "getPlayer()");
		return player;
	}

	public void setSawCounter() {
		Skeleton.enterMethod(type, id, "setSawCounter()");
		sawCounter = sawCounterMax;
		Skeleton.exitMethod(type, id, "setSawCounter()");
	}

	public void turnLeft() {
		Skeleton.enterMethod(type, id, "turnLeft()");
		direction.turnLeft();
		Skeleton.exitMethod(type, id, "turnLeft()");
	}

	public void turnRight() {
		Skeleton.enterMethod(type, id, "turnRight()");
		direction.turnRight();
		Skeleton.exitMethod(type, id, "turnRight()");
	}

	public boolean isSaw() {
		Skeleton.enterMethod(type, id, "isSaw()");
		Skeleton.exitMethod(type, id, "isSaw()");
		return (sawCounter > 0);
	}

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
