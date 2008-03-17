package snakefarm;

import java.util.HashMap;

public class Field {

	private static int lastid = 0;
	private int id = lastid;
	private SkeletonInterface si;
	private static final String type = "Field";
	private GameField gameField;
	private HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>(Direction.max + 1);
	private Collidable objectOnField = null;

	public Field(SkeletonInterface si, GameField gameField) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "Field(GameField)");
		this.gameField = gameField;
		si.exitMethod(type, id, "Field(GameField)");
	}

	public Collidable stepOn(SnakeUnit snakeUnit) {
		si.enterMethod(type, id, "stepOn(SnakeUnit)");
		si.exitMethod(type, id, "stepOn(SnakeUnit)");
		if (objectOnField == null) {
			objectOnField = snakeUnit;
			return null;
		} else {
			return objectOnField;
		}
	}

	public void stepOut(SnakeUnit snakeUnit) {
		si.enterMethod(type, id, "stepOut(SnakeUnit)");
		objectOnField = null;
		si.exitMethod(type, id, "stepOut(SnakeUnit)");
	}

	public Field getNext(Direction dir) {
		si.enterMethod(type, id, "getNext(Direction)");
		si.exitMethod(type, id, "getNext(Direction)");
		return neighbours.get(dir);
	}

	public boolean setObject(Collidable c) {
		si.enterMethod(type, id, "setObject(Collidable)");
		boolean flag = true;
		if (objectOnField == null) {
			objectOnField = c;
		} else {
			flag = false;
		}
		si.exitMethod(type, id, "setObject(Collidable)");
		return flag;
	}

	public void unsetObject(Collidable c) {
		si.enterMethod(type, id, "unsetObject(Collidable)");
		if (objectOnField.equals(c)) {
			objectOnField = null;
		}
		si.exitMethod(type, id, "unsetObject(Collidable)");
	}

	public boolean isEmpty() {
		si.enterMethod(type, id, "isEmpty()");
		si.exitMethod(type, id, "isEmpty()");
		return (objectOnField == null);
	}

	public void setNeighbour(Direction dir, Field field) {
		si.enterMethod(type, id, "setNeighbour(Direction, Field)");
		neighbours.put(dir, field);
		field.neighbours.put(dir.reverse(), this);
		si.exitMethod(type, id, "setNeighbour(Direction, Field)");
	}
}
