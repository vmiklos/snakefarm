package snakefarm;

public class SnakeUnit extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private SkeletonInterface si;
	private static final String type = "SnakeUnit";
	private Snake snake;
	private SnakeUnit prevUnit = null;
	private SnakeUnit nextUnit = null;
	private boolean eatenFieldBerry = false;
	private boolean eatenStoneBerry = false;
	private boolean isAlive = true;
	private boolean isStone;

	public SnakeUnit(SkeletonInterface si, Snake snake, Field field) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "SnakeUnit(Snake, Field)");
		this.snake = snake;
		this.field = field;
		if (!field.setObject(this)) {
		//para van!!! elvileg nem kellene, hogy ilyen legyen, erre kell valami
		}
		si.exitMethod(type, id, "SnakeUnit(Snake, Field)");
	}

	public void collideWith2(Wall wall) {
		si.enterMethod(type, id, "collideWith2(Wall)");
		die();
		si.exitMethod(type, id, "collideWith2(Wall)");
	}

	public void collideWith2(SawBerry sawBerry) {
		si.enterMethod(type, id, "collideWith2(SawBerry)");
		snake.setSawCounter();
		si.exitMethod(type, id, "collideWith2(SawBerry)");
	}

	public void collideWith2(FieldBerry fieldBerry) {
		si.enterMethod(type, id, "collideWith2(FieldBerry)");
		eatenFieldBerry = true;
		si.exitMethod(type, id, "collideWith2(FieldBerry)");
	}

	public void collideWith2(StoneBerry stoneBerry) {
		si.enterMethod(type, id, "collideWith2(StoneBerry)");
		eatenStoneBerry = true;
		si.exitMethod(type, id, "collideWith2(StoneBerry)");
	}

	public void collideWith2(SnakeUnit snakeUnit) {
		si.enterMethod(type, id, "collideWith2(SnakeUnit)");
		if (!snake.isSaw()) {
			die();
		}
		si.exitMethod(type, id, "collideWith2(SnakeUnit)");
	}

	public void collideWith2Stone(SnakeUnit snakeUnit) {
		si.enterMethod(type, id, "collideWith2Stone(SnakeUnit)");
		die();
		si.exitMethod(type, id, "collideWith2Stone(SnakeUnit)");
	}

	/*public SnakeUnit getNextUnit() {
	si.enterMethod(type, id, "getNextUnit()");
	si.exitMethod(type, id, "getNextUnit()");
	return nextUnit;
	}
	public SnakeUnit getPrevUnit() {
	si.enterMethod(type, id, "getPrevUnit()");
	si.exitMethod(type, id, "getPrevUnit()");
	return prevUnit;
	}*/
	public void setNextUnit(SnakeUnit snakeUnit) {
		si.enterMethod(type, id, "setNextUnit(SnakeUnit)");
		nextUnit = snakeUnit;
		si.exitMethod(type, id, "setNextUnit(SnakeUnit)");
	}

	public void setPrevUnit(SnakeUnit snakeUnit) {
		si.enterMethod(type, id, "setPrevUnit(SnakeUnit)");
		prevUnit = snakeUnit;
		si.exitMethod(type, id, "setPrevUnit(SnakeUnit)");
	}

	/*public Snake getSnake() {
	si.enterMethod(type, id, "getSnake()");
	si.exitMethod(type, id, "getSnake()");
	return snake;
	}
	public Field getField() {
	si.enterMethod(type, id, "getField()");
	si.exitMethod(type, id, "getField()");
	return field;
	}*/
	public boolean step(Field nextField, boolean isToGrow, boolean receivesStone) {
		si.enterMethod(type, id, "step(Field, boolean, boolean)");
		boolean rejectStone = false, nextRejectsStone;
		Field prevField = field;

		/* uressegbe lepunk-e */
		if (nextField == null) {
			die();
		}

		/* utkozunk */
		if (isAlive) {
			field.stepOut(this);
			Collidable collidee = nextField.stepOn(this);
			if (collidee != null) {
				if (snake.isSaw()) {
					collidee.collideWithSaw(this);
				} else {
					collidee.collideWith(this);
				}
			}
		}

		if (isAlive) {
			/* mostmar nincs akadaly, tehat lepunk */
			nextField.stepOn(this);
			field = nextField;

			/* ha nincs kovetkezo */
			if (nextUnit == null) {
				/* megnezzuk, kell-e novekedni */
				if (isToGrow || eatenFieldBerry) {
					SnakeUnit newTail = new SnakeUnit(si, snake, prevField);
					newTail.setPrevUnit(this);
					setNextUnit(newTail);
					snake.addSnakeUnit(newTail);
					eatenFieldBerry = false;
				}
				/* megnezzuk, az elozonek kuld-e vissza kovet */
				rejectStone = isStone && (receivesStone || eatenStoneBerry);
				/* megnezzuk, ko lesz-e */
				isStone = isStone || receivesStone || eatenStoneBerry;
				eatenStoneBerry = false;
			}

			/* ha van kovetkezo */
			if (nextUnit != null) {
				/* szolunk neki is, es a kovet is tovabbadjuk */
				nextRejectsStone = nextUnit.step(prevField, eatenFieldBerry || isToGrow, isStone);
				eatenFieldBerry = false;
				/* megnezzuk, az elozonek kuld-e vissza kovet */
				rejectStone = isStone && nextRejectsStone && (receivesStone || eatenStoneBerry);
				/* megnezzuk, ko lesz-e */
				isStone = receivesStone || eatenStoneBerry || (nextRejectsStone && isStone);
				eatenStoneBerry = false;
			}
		}

		si.exitMethod(type, id, "step(Field, boolean, boolean)");
		return rejectStone;
	}

	public void setStone(boolean has) {
		si.enterMethod(type, id, "setStone(boolean)");
		isStone = has;
		si.exitMethod(type, id, "setStone(boolean)");
	}

	public void die() {
		si.enterMethod(type, id, "die()");
		if (isAlive) {
			isAlive = false;
			snake.removeSnakeUnit(this);
			if (prevUnit == null) {
				snake.die();
			} else if (nextUnit != null) {
				nextUnit.die();
			}
			field.setObject(null);
		}
		si.exitMethod(type, id, "die()");
	}

	public Field getNextField(Direction dir) {
		si.enterMethod(type, id, "getNextField(Direction)");
		Field next = field.getNext(dir);
		si.exitMethod(type, id, "getNextField(Direction)");
		return next;
	}
}
