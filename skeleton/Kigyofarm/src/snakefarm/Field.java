package snakefarm;


import java.util.List;
import java.util.LinkedList;

public class Field {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Field";
	private GameField unnamed_GameField_;
	private Field unnamed_Field_;
	private List<Field> neighbours = new LinkedList<Field>();
	private Collidable unnamed_Collidable_;
	
	public Field(UserInterface ui) {
		this.ui=ui;
		lastid++;
	}

	public void stepOn(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	public void stepOut(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	public Field getNext(Direction dir) {
		throw new UnsupportedOperationException();
	}

	public boolean setObject(Collidable c) {
		throw new UnsupportedOperationException();
	}

	public void unsetObject(Collidable c) {
		throw new UnsupportedOperationException();
	}

	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}
}