package snakefarm;

/**
 * A kigyot alkoto egyseg. Egy kigyohoz tobb ilyen egyseg is tartozhat,
 * attol fuggoen, hogy a kigyo milyen hosszu.
 */
public class SnakeUnit extends Collidable {

	private int id;
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

	public void setId(int id)
	{
		this.id = id;
	}

	public void save()
	{
		String stone;
		if(isStone)
			stone = "1";
		else
			stone = "0";
		Proto.out.println(field.getId() + " " + stone);
	}

	/**
	 * Ket kigyoelem utkozteteset vegzi el ugy, hogy tudjuk, hogy a
	 * masik kigyolem nincs fureszmodban.
	 *
	 * @param snakeUnit a masik kigyoelem
	 */
	public void collideWith(SnakeUnit snakeUnit) {
		Proto.out.println("StepEvent Snake_Snake " + snake.getId());
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
		Proto.out.println("StepEvent Snake_Snake " + snake.getId());
		if (isStone) {
			snakeUnit.collideWith2Stone(this);
		} else {
			SnakeUnit pu = prevUnit;
			snakeUnit.collideWith2(this);
			snake.removeSnakeUnit(this);
			if(pu != null)
			{
				int snakeid = snake.getPlayer().addSnake(0);
				Snake newsnake = snake.getPlayer().getSnakeById(snakeid);
				// itt azert -1 mert az eredeti erteket majd
				// csokkenteni fogjuk de az uj kigyoet nem
				// tudjuk majd, tehat itt tesszuk meg,
				// ha kell
				if(snake.getSawCounter()>0)
					newsnake.setSawCounter(snake.getSawCounter()-1);
				else
					newsnake.setSawCounter(snake.getSawCounter());
				newsnake.setControlSpeed(snake.getControlSpeed());
				newsnake.setStoneSpeed(snake.getStoneSpeed());
				newsnake.addSnakeUnit(nextUnit);
				System.out.println("StepEvent SnakeSplit "+id+" "+newsnake.getId());
			}
			else
			{
				// a kigyo fejevel utkoztek, szal ez
				// halal
				die();
			}
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
		Proto.out.println("StepEvent Snake_SawBerry");
		snake.setSawCounter(Snake.sawCounterMax);
	}

	/**
	 * Utkozes mezei bogyoval.
	 *
	 * @param fieldBerry az utkozo mezei bogyo
	 */
	public void collideWith2(FieldBerry fieldBerry) {
		Proto.out.println("StepEvent Snake_FieldBerry");
		eatenFieldBerry = true;
	}

	/**
	 * Utkozes kobogyoval
	 *
	 * @param stoneBerry az utkozo kobogyo
	 */
	public void collideWith2(StoneBerry stoneBerry) {
		Proto.out.println("StepEvent Snake_StoneBerry");
		eatenStoneBerry = true;
	}

	/**
	 * Utkozes kezelese kigyoelemmel abban az esetben, ha nekunk
	 * utkoznek, es a masik kigyoelemben nincs ko.
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

	public SnakeUnit getNextUnit()
	{
		return nextUnit;
	}

	/**
	 * Elozo elem allitasa
	 *
	 * @param snakeUnit leendo elozo elem
	 */
	public void setPrevUnit(SnakeUnit snakeUnit) {
		prevUnit = snakeUnit;
	}

	public SnakeUnit getPrevUnit()
	{
		return prevUnit;
	}

	/**
	 * Kigyoelem leptetese
	 *
	 * @param nextField kovetkezo mezo
	 * @param isToGrow kell-e majd a faroknal novekedni
	 */
	public void step(Field nextField, boolean isToGrow) {
		boolean rejectStone = false, nextRejectsStone;
		Field prevField = field;

		/* uressegbe lepunk-e */
		if (nextField == null) {
			die();
		}

		if (isAlive) {
			field.stepOut(this);
			Collidable collidee = nextField.stepOn(this);
			/* utkozunk */
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
		// novekedes es kovetkezo egyseg hivasa
		if (isAlive) {
			// ha nincs kovetkezo
			if (nextUnit == null) {
				// megnezzuk, kell-e novekedni
				if (isToGrow || eatenFieldBerry) {
					SnakeUnit newTail = new SnakeUnit(snake, prevField);
					newTail.setPrevUnit(this);
					setNextUnit(newTail);
					snake.addSnakeUnit(newTail);
				}
			} // ha van kovetkezo
			else
			{
				// szolunk neki is
				nextUnit.step(prevField, eatenFieldBerry || isToGrow);
			}
		}
	}

	/**
	 * Ko leptetese
	 *
	 * @param receivesStone kap-e kovet
	 * @return az elozonek kuld-e vissza kovet
	 */
	public boolean stoneStep(boolean receivesStone) {
		if(receivesStone)
			Proto.out.println("Event StoneStep " + snake.getId());
		boolean rejectStone = false, nextRejectsStone;

		/* novekedes es kovetkezo egyseg hivasa */
		if (isAlive) {
			/* ha nincs kovetkezo */
			if (nextUnit == null) {
				/* megnezzuk, az elozonek kuld-e vissza kovet */
				rejectStone = isStone && (receivesStone || eatenStoneBerry);
				/* megnezzuk, ko lesz-e */
				isStone = isStone || receivesStone || eatenStoneBerry;
				eatenStoneBerry = false;
			} /* ha van kovetkezo */ else {
				/* szolunk neki is, es a kovet is tovabbadjuk */
				nextRejectsStone = nextUnit.stoneStep(isStone);
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

	public void show()
	{
		String stone;
		if(isStone)
			stone = "1";
		else
			stone = "0";
		Proto.out.println(id + " " + field.getId() + " " + stone);
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

	public boolean hasStone()
	{
		return isStone;
	}
}
