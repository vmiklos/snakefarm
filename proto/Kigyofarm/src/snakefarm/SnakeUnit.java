package snakefarm;

/**
 * A kigyot alkoto egyseg. Egy kigyohoz tobb ilyen egyseg is tartozhat,
 * attol fuggoen, hogy a kigyo milyen hosszu.
 */
public class SnakeUnit extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "SnakeUnit";
	private Snake snake;
	private SnakeUnit prevUnit = null;
	private SnakeUnit nextUnit = null;
	private boolean eatenFieldBerry = false;
	private boolean eatenStoneBerry = false;
	private boolean isAlive = true;
	private boolean isStone;

	/**
	 * A kigyoelem konstruktora.
	 *
	 * @param snake melyik kigyo eleme lesz
	 * @param field melyik mezon lesz
	 */
	public SnakeUnit(Snake snake, Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "SnakeUnit(Snake, Field)");
		this.snake = snake;
		this.field = field;
		field.setObject(this);
		Skeleton.exitMethod(type, id, "SnakeUnit(Snake, Field)");
	}

	/**
	 * Ket kigyoelem utkozteteset vegzi el.
	 *
	 * @param snakeUnit a masik kigyoelem
	 */
	public void collideWith(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		if (isStone) {
			snakeUnit.collideWith2Stone(this);
		} else {
			snakeUnit.collideWith2(this);
		}
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}

	/**
	 * Kigyoelem utkozteteset vegzi ugy, hogy tudjuk, hogy a masik
	 * kigyoelem furesz modban van.
	 *
	 * @param snakeUnit a masik kigyoelem
	 */
	@Override
	public void collideWithSaw(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWithSaw(SnakeUnit)");
		if (isStone) {
			snakeUnit.collideWith2Stone(this);
		} else {
			snakeUnit.collideWith2(this);
			die();
		}
		Skeleton.exitMethod(type, id, "collideWithSaw(SnakeUnit)");
	}

	/**
	 * Utkoztetes fallal
	 *
	 * @param wall az utkozo fal
	 */
	public void collideWith2(Wall wall) {
		Skeleton.enterMethod(type, id, "collideWith2(Wall)");
		die();
		Skeleton.exitMethod(type, id, "collideWith2(Wall)");
	}

	/**
	 * Utkozes fureszbogyoval.
	 *
	 * @param sawBerry az utkozo fureszbogyo
	 */
	public void collideWith2(SawBerry sawBerry) {
		Skeleton.enterMethod(type, id, "collideWith2(SawBerry)");
		snake.setSawCounter();
		Skeleton.exitMethod(type, id, "collideWith2(SawBerry)");
	}

	/**
	 * Utkozes mezei bogyoval.
	 *
	 * @param fieldBerry az utkozo mezei bogyo
	 */
	public void collideWith2(FieldBerry fieldBerry) {
		Skeleton.enterMethod(type, id, "collideWith2(FieldBerry)");
		eatenFieldBerry = true;
		Skeleton.exitMethod(type, id, "collideWith2(FieldBerry)");
	}

	/**
	 * Utkozes kobogyoval
	 *
	 * @param stoneBerry az utkozo kobogyo
	 */
	public void collideWith2(StoneBerry stoneBerry) {
		Skeleton.enterMethod(type, id, "collideWith2(StoneBerry)");
		eatenStoneBerry = true;
		Skeleton.exitMethod(type, id, "collideWith2(StoneBerry)");
	}

	/**
	 * Utkozes kezelese kigyoelemmel abban az esetben, ha nekunk
	 * utkoznek, es nem mi utkozunk.
	 *
	 * @param snakeUnit utkozo kigyoelem
	 */
	public void collideWith2(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith2(SnakeUnit)");
		if (!snake.isSaw()) {
			die();
		}
		Skeleton.exitMethod(type, id, "collideWith2(SnakeUnit)");
	}

	/**
	 * Utkozes kezelese kigyoelemmel abban az esetben, ha nekunk
	 * utkoznek, es a masik kigyoelemben ko van.
	 *
	 * @param snakeUnit masik elem
	 */
	public void collideWith2Stone(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith2Stone(SnakeUnit)");
		die();
		Skeleton.exitMethod(type, id, "collideWith2Stone(SnakeUnit)");
	}

	/**
	 * Kovetkezo elem beallitasa
	 *
	 * @param snakeUnit leendo kovetkezo elem
	 */
	public void setNextUnit(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "setNextUnit(SnakeUnit)");
		nextUnit = snakeUnit;
		Skeleton.exitMethod(type, id, "setNextUnit(SnakeUnit)");
	}

	/**
	 * Elozo elem allitasa
	 *
	 * @param snakeUnit leendo elozo elem
	 */
	public void setPrevUnit(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "setPrevUnit(SnakeUnit)");
		prevUnit = snakeUnit;
		Skeleton.exitMethod(type, id, "setPrevUnit(SnakeUnit)");
	}

	/**
	 * Kigyoelem leptetese
	 *
	 * @param nextField kovetkezo mezo
	 * @param isToGrow kell-e majd a faroknal novekedni
	 * @param receivesStone kap-e kovet
	 * @return az elozonek kuld-e vissza kovet
	 */
	public boolean step(Field nextField, boolean isToGrow, boolean receivesStone) {
		Skeleton.enterMethod(type, id, "step(Field, boolean, boolean)");
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
				/* ha eletben maradt, lepunk */
				if (isAlive) {
					nextField.stepOn(this);
					field = nextField;
				}
			/* ha nem utkozunk, akkor az uj mezon vagyunk */
			} else {
				field = nextField;
			}


		}

		/* novekedes es kovetkezo egyseg hivasa */
		if (isAlive) {
			/* ha nincs kovetkezo */
			if (nextUnit == null) {
				/* megnezzuk, kell-e novekedni */
				if (isToGrow || eatenFieldBerry) {
					SnakeUnit newTail = new SnakeUnit(snake, prevField);
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
			} /* ha van kovetkezo */ else {
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

		Skeleton.exitMethod(type, id, "step(Field, boolean, boolean)");
		return rejectStone;
	}

	/**
	 * Beallitja, hogy van-e a kigyoelemben ko
	 *
	 * @param has az allitas igazsagara vonatkozo logikai ertek
	 */
	public void setStone(boolean has) {
		Skeleton.enterMethod(type, id, "setStone(boolean)");
		isStone = has;
		Skeleton.exitMethod(type, id, "setStone(boolean)");
	}

	/**
	 * Megoli a kigyoelemet
	 */
	public void die() {
		Skeleton.enterMethod(type, id, "die()");
		if (isAlive) {
			isAlive = false;
			field.unsetObject(this);
			snake.removeSnakeUnit(this);
			if (nextUnit != null) {
				nextUnit.die();
			}
			if (prevUnit == null) {
				snake.die();
			}
		}
		Skeleton.exitMethod(type, id, "die()");
	}

	/**
	 * Visszadja a kovetkeozo elemet
	 *
	 * @param dir milyen iranyban kovetkezo elemet kerunk
	 * @return kovetkezo elem
	 */
	public Field getNextField(Direction dir) {
		Skeleton.enterMethod(type, id, "getNextField(Direction)");
		Field next = field.getNext(dir);
		Skeleton.exitMethod(type, id, "getNextField(Direction)");
		return next;
	}
}
