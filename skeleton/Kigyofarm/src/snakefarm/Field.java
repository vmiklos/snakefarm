package snakefarm;

import java.util.HashMap;

public class Field {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Field";
	private HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>(Direction.numberOfDirections);
	private Collidable objectOnField = null;

	public Field() {
		lastid++;
		Skeleton.enterMethod(type, id, "Field(GameField)");
		Skeleton.exitMethod(type, id, "Field(GameField)");
	}

	public Collidable stepOn(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "stepOn(SnakeUnit)");
		Skeleton.exitMethod(type, id, "stepOn(SnakeUnit)");
		if (objectOnField == null) {
			objectOnField = snakeUnit;
			return null;
		} else {
			return objectOnField;
		}
	}

	public void stepOut(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "stepOut(SnakeUnit)");
		objectOnField = null;
		Skeleton.exitMethod(type, id, "stepOut(SnakeUnit)");
	}

	public Field getNext(Direction dir) {
		Skeleton.enterMethod(type, id, "getNext(Direction)");
		Skeleton.exitMethod(type, id, "getNext(Direction)");
		return neighbours.get(dir);
	}

	public boolean setObject(Collidable c) {
		Skeleton.enterMethod(type, id, "setObject(Collidable)");
		boolean flag = true;
		if (objectOnField == null) {
			objectOnField = c;
			objectOnField.setField(this);
		} else {
			flag = false;
		}
		Skeleton.exitMethod(type, id, "setObject(Collidable)");
		return flag;
	}

	public void unsetObject(Collidable c) {
		Skeleton.enterMethod(type, id, "unsetObject(Collidable)");
		if (objectOnField != null) {
			if (objectOnField.equals(c)) {
				objectOnField.setField(null);
				objectOnField = null;
			}
		}
		Skeleton.exitMethod(type, id, "unsetObject(Collidable)");
	}

	public boolean isEmpty() {
		Skeleton.enterMethod(type, id, "isEmpty()");
		Skeleton.exitMethod(type, id, "isEmpty()");
		return (objectOnField == null);
	}

	public void setNeighbour(Direction dir, Field field) {
		Skeleton.enterMethod(type, id, "setNeighbour(Direction, Field)");
		neighbours.put(dir, field);
		field.neighbours.put(dir.reverse(), this);
		Skeleton.exitMethod(type, id, "setNeighbour(Direction, Field)");
	}
}
