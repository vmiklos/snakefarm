package snakefarm;

import snakefarm.viewfactories.SnakeUnitViewFactory;
import snakefarm.views.BaseView;
import snakefarm.creators.BittenOffSnakeCreator;

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
	private SnakeUnitViewFactory factory = new SnakeUnitViewFactory();

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

	/**
	 * Beallitja, hogy az elem melyik kigyohoz tartozik.
	 *
	 * @param snake az uj kigyo
	 */
	public void setSnake(Snake snake)
	{
		this.snake = snake;
	}

	/**
	 * Ket kigyoelem utkozteteset vegzi el ugy, hogy tudjuk, hogy a
	 * masik kigyolem nincs fureszmodban.
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
			SnakeUnit pu = prevUnit;
			snakeUnit.collideWith2(this);
			
			if(pu == null) {
				// a kigyo fejevel utkoztek, szal ez
				// halal
				die();
			} else if (nextUnit != null) {
				snake.getPlayer().addSnake(new BittenOffSnakeCreator(snake, this));
			}
			snake.removeSnakeUnit(this);
			if (field!=null) field.unsetObject(this);
		}
	}

	/**
	 * Utkoztetes fallal
	 *
	 * @param wall az utkozo fal
	 */
	public void collideWith2(Wall wall) {
		die();
	}

	/**
	 * Utkozes fureszbogyoval.
	 *
	 * @param sawBerry az utkozo fureszbogyo
	 */
	public void collideWith2(SawBerry sawBerry) {
		snake.setSawCounter(snake.getSawCounterMax());
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

	public SnakeUnit getNextUnit() {
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

	/**
	 * Elozo elem lekerdezese
	 *
	 * @return az elozo elem referenciaja
	 */
	public SnakeUnit getPrevUnit() {
		return prevUnit;
	}

	/**
	 * Kigyoelem leptetese
	 *
	 * @param nextField kovetkezo mezo
	 * @param isToGrow kell-e majd a faroknal novekedni
	 */
	public void step(Field nextField, boolean isToGrow) {
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
			} else { // ha van kovetkezoszolunk neki is
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

	/**
	 * Megmondja, hogy az adott elemben van-e ko.
	 *
	 * @return a fenti allitas igazsaga
	 */
	public boolean hasStone() {
		return isStone;
	}

	/**
	 * Megmondja, hogy az adott elemben, es a kigyo minden utanalevo
	 * elemeben van-e ko.
	 *
	 * @return a fenti allitas igazsaga
	 */
	public boolean isFullStone() {
		if (isStone) {
			if(nextUnit == null) {
				return true;
                        } else {
				return nextUnit.isFullStone();
                        }
		} else {
			return false;
                }
	}

	/**
	 * Megadja, hogy melyik kigyohoz tartozik az elem.
	 *
	 * @return a kigyo referenciaja
	 */
	public Snake getSnake() {
		return snake;
	}

	/**
	 * modell elemhez tartozo nezet letrehozasa
	 * @return az elemhez tartozo nezet objektum
	 */
	@Override
	protected BaseView genBaseView() {
		return factory.genBaseView(this);
	}

	/**
	 * jatekos lekerdezese
	 * @return a kigyot iranyito jatekos
	 */
	public Player getPlayer() {
		return snake.getPlayer();
	}

	/**
	 * a kigyoelem fureszenek lekerdezese
	 * @return ez a kigyoelem fej es rendelkezik furesszel a kigyo
	 */
	public boolean hasSaw() {
		return (getPrevUnit() == null && snake.isSaw());
	}
}
