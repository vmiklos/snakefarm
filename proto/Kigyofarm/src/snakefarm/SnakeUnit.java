package snakefarm;

/**
 * A kigyot alkoto egyseg. Egy kigyohoz tobb ilyen egyseg is tartozhat,
 * attol fuggoen, hogy a kigyo milyen hosszu.
 */
public class SnakeUnit extends Collidable {

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
		this.snake = snake;
		this.field = field;
		field.setObject(this);
	}

	public void save()
	{
		String stone;
		if(isStone)
			stone = "1";
		else
			stone = "0";
		Proto.out.println(field.id + " " + stone);
	}

	/**
	 * Ket kigyoelem utkozteteset vegzi el.
	 *
	 * @param snakeUnit a masik kigyoelem
	 */
	public void collideWith(SnakeUnit snakeUnit) {
		if (isStone) {
			snakeUnit.collideWith2Stone(this);
		} else {
			snakeUnit.collideWith2(this);
		}
	}

	/**
	 * Kigyoelem utkozteteset vegzi ugy, hogy tudjuk, hogy a masik
	 * kigyoelem furesz modban van.
	 *
	 * @param snakeUnit a masik kigyoelem
	 */
	@Override
	public void collideWithSaw(SnakeUnit snakeUnit) {
		if (isStone) {
			snakeUnit.collideWith2Stone(this);
		} else {
			snakeUnit.collideWith2(this);
			die();
		}
	}

	/**
	 * Utkoztetes fallal
	 *
	 * @param wall az utkozo fal
	 */
	public void collideWith2(Wall wall) {
		Proto.out.println("StepEvent Snake_Wall");
		die();
	}

	/**
	 * Utkozes fureszbogyoval.
	 *
	 * @param sawBerry az utkozo fureszbogyo
	 */
	public void collideWith2(SawBerry sawBerry) {
		snake.setSawCounter(Snake.sawCounterMax);
	}

	/**
	 * Utkozes mezei bogyoval.
	 *
	 * @param fieldBerry az utkozo mezei bogyo
	 */
	public void collideWith2(FieldBerry fieldBerry) {
		eatenFieldBerry = true;
	}

	/**
	 * Utkozes kobogyoval
	 *
	 * @param stoneBerry az utkozo kobogyo
	 */
	public void collideWith2(StoneBerry stoneBerry) {
		eatenStoneBerry = true;
	}

	/**
	 * Utkozes kezelese kigyoelemmel abban az esetben, ha nekunk
	 * utkoznek, es nem mi utkozunk.
	 *
	 * @param snakeUnit utkozo kigyoelem
	 */
	public void collideWith2(SnakeUnit snakeUnit) {
		if (!snake.isSaw()) {
			die();
		}
	}

	/**
	 * Utkozes kezelese kigyoelemmel abban az esetben, ha nekunk
	 * utkoznek, es a masik kigyoelemben ko van.
	 *
	 * @param snakeUnit masik elem
	 */
	public void collideWith2Stone(SnakeUnit snakeUnit) {
		die();
	}

	/**
	 * Kovetkezo elem beallitasa
	 *
	 * @param snakeUnit leendo kovetkezo elem
	 */
	public void setNextUnit(SnakeUnit snakeUnit) {
		nextUnit = snakeUnit;
	}

	/**
	 * Elozo elem allitasa
	 *
	 * @param snakeUnit leendo elozo elem
	 */
	public void setPrevUnit(SnakeUnit snakeUnit) {
		prevUnit = snakeUnit;
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

		return rejectStone;
	}

	/**
	 * Beallitja, hogy van-e a kigyoelemben ko
	 *
	 * @param has az allitas igazsagara vonatkozo logikai ertek
	 */
	public void setStone(boolean has) {
		isStone = has;
	}

	/**
	 * Megoli a kigyoelemet
	 */
	public void die() {
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
	}

	/**
	 * Visszadja a kovetkeozo elemet
	 *
	 * @param dir milyen iranyban kovetkezo elemet kerunk
	 * @return kovetkezo elem
	 */
	public Field getNextField(Direction dir) {
		Field next = field.getNext(dir);
		return next;
	}
}
