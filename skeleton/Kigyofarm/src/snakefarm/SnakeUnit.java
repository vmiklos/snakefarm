package snakefarm;

public class SnakeUnit extends Collidable {
	private boolean stone;
	private Snake unnamed_Snake_;

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
		throw new UnsupportedOperationException();
	}

	public void setStone(boolean has) {
		throw new UnsupportedOperationException();
	}

	public void die() {
		throw new UnsupportedOperationException();
	}

	public Field getNextField(Direction dir) {
		throw new UnsupportedOperationException();
	}
}