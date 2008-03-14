package snakefarm;

public class SnakeUnit extends Collidable {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="SnakeUnit";
	private boolean stone;
	private Snake unnamed_Snake_;
	Field current;
	
	public SnakeUnit(UserInterface ui, Snake snake) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "SnakeUnit(Snake)");
		unnamed_Snake_=snake;
		ui.exitMethod(type, id, "SnakeUnit(Snake)");
	}

	public void collideWith2(Wall wall) {
		throw new UnsupportedOperationException();
	}

	public void collideWith2(SawBerry sawBerry) {
		throw new UnsupportedOperationException();
	}

	public void collideWith2(FieldBerry fieldBerry) {
		throw new UnsupportedOperationException();
	}

	public void collideWith2(StoneBerry stoneBerry) {
		throw new UnsupportedOperationException();
	}

	public void collideWith2(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	public void collideWith2Stone(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	public SnakeUnit getNextUnit() {
		throw new UnsupportedOperationException();
	}

	public SnakeUnit getPrevUnit() {
		throw new UnsupportedOperationException();
	}

	public void setNextUnit(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	public void setPrevUnit(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	public Snake getSnake() {
		throw new UnsupportedOperationException();
	}

	public Field getField() {
		throw new UnsupportedOperationException();
	}

	public boolean step(Field next, boolean inc, boolean stone) {
		ui.enterMethod(type, id, "step(Field, boolean, boolean)");
		ui.exitMethod(type, id, "step(Field, boolean, boolean)");
		return false;
	}

	public void setStone(boolean has) {
		throw new UnsupportedOperationException();
	}

	public void die() {
		throw new UnsupportedOperationException();
	}

	public Field getNextField(Direction dir) {
		ui.enterMethod(type, id, "getNextField(Direction)");
		Field next=current.getNext(dir);
		ui.exitMethod(type, id, "getNextField(Direction)");
		return next;
	}
}