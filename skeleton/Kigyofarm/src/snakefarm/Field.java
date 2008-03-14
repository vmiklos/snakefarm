package snakefarm;


import java.util.List;
import java.util.LinkedList;

public class Field {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Field";
	private GameField gameField;
	private List<Field> neighbours=new LinkedList<Field>();
	private Collidable objectOnField=null;
	
	public Field(UserInterface ui, GameField gameField) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Field(GameField)");
		this.gameField=gameField;
		ui.exitMethod(type, id, "Field(GameField)");
	}

	public void stepOn(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	public void stepOut(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	public Field getNext(Direction dir) {
		ui.enterMethod(type, id, "getNext(Direction)");
		ui.exitMethod(type, id, "getNext(Direction)");
		return null;
	}

	public boolean setObject(Collidable c) {
		throw new UnsupportedOperationException();
	}

	public void unsetObject(Collidable c) {
		throw new UnsupportedOperationException();
	}

	public boolean isEmpty() {
		ui.enterMethod(type, id, "isEmpty()");
		ui.exitMethod(type, id, "isEmpty()");
		return (objectOnField==null);
	}
}