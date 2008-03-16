package snakefarm;

import java.util.HashMap;

public class Field {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Field";
	
	private GameField gameField;
	private HashMap<Direction, Field> neighbours=new HashMap<Direction, Field>(Direction.max+1);
	private Collidable objectOnField=null;
	
	public Field(UserInterface ui, GameField gameField) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Field(GameField)");
		this.gameField=gameField;
		ui.exitMethod(type, id, "Field(GameField)");
	}

	public Collidable stepOn(SnakeUnit snakeUnit) {
		ui.enterMethod(type, id, "stepOn(SnakeUnit)");
		ui.exitMethod(type, id, "stepOn(SnakeUnit)");
		if (objectOnField==null) {
			objectOnField=snakeUnit;
			return null;
		} else return objectOnField;
	}

	public void stepOut(SnakeUnit snakeUnit) {
		ui.enterMethod(type, id, "stepOut(SnakeUnit)");
		objectOnField=null;
		ui.exitMethod(type, id, "stepOut(SnakeUnit)");
	}

	public Field getNext(Direction dir) {
		ui.enterMethod(type, id, "getNext(Direction)");
		ui.exitMethod(type, id, "getNext(Direction)");
		return neighbours.get(dir);
	}

	public boolean setObject(Collidable c) {
		ui.enterMethod(type, id, "setObject(Collidable)");
		boolean flag=true;
		if (objectOnField==null) objectOnField=c;
		else flag=false;
		ui.exitMethod(type, id, "setObject(Collidable)");
		return flag;
	}

	public void unsetObject(Collidable c) {
		ui.enterMethod(type, id, "unsetObject(Collidable)");
		if (objectOnField.equals(c)) objectOnField=null;
		ui.exitMethod(type, id, "unsetObject(Collidable)");
	}

	public boolean isEmpty() {
		ui.enterMethod(type, id, "isEmpty()");
		ui.exitMethod(type, id, "isEmpty()");
		return (objectOnField==null);
	}
	
	public void setNeighbour(Direction dir, Field field) {
		ui.enterMethod(type, id, "setNeighbour(Direction, Field)");
		neighbours.put(dir, field);
		field.neighbours.put(dir.reverse(), this);
		ui.exitMethod(type, id, "setNeighbour(Direction, Field)");
	}
}