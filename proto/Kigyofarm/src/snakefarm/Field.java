package snakefarm;

import java.util.HashMap;

/**
 * Egy jatekmezot valosit meg.
 */
public class Field {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Field";
	private HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>(Direction.numberOfDirections);
	private Collidable objectOnField = null;

	/**
	 * A jatekmezo konstruktora.
	 */
	public Field() {
		lastid++;
		Skeleton.enterMethod(type, id, "Field(GameField)");
		Skeleton.exitMethod(type, id, "Field(GameField)");
	}

	/**
	 * Ralepes a jatekmezore. Ha van rajta valaki akkor nem csinal
	 * semmit, ha nincs akkor ralepteti.
	 *
	 * @param snakeUnit a kigyo, amelyet ra szeretnenk leptetni
	 * @return ha nem volt rajta senki, akkor null, ha volt, akkor a
	 * rajtalevo vilagobjektumot adja vissza
	 */
	public Collidable stepOn(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "stepOn(SnakeUnit)");
		Skeleton.exitMethod(type, id, "stepOn(SnakeUnit)");
		if (objectOnField == null) {
			objectOnField = snakeUnit;
			return null;
		} else {
			return objectOnField;
		}
	}

	/**
	 * Kilepes a mezorol.
	 *
	 * @param snakeUnit a kileptetendo kigyoelem
	 */
	public void stepOut(SnakeUnit snakeUnit) {
		/* FIXME ez a param ide teljesen felesleges (vmiklos) */
		Skeleton.enterMethod(type, id, "stepOut(SnakeUnit)");
		objectOnField = null;
		Skeleton.exitMethod(type, id, "stepOut(SnakeUnit)");
	}

	/**
	 * Kovetkezo mezo lekerese.
	 *
	 * @param dir megadja, hogy milyen iranyban kovetkezo
	 * @return a kovetkezo mezo
	 */
	public Field getNext(Direction dir) {
		Skeleton.enterMethod(type, id, "getNext(Direction)");
		Skeleton.exitMethod(type, id, "getNext(Direction)");
		return neighbours.get(dir);
	}

	/**
	 * Beallitja a mezo objektumat, ha a mezo ures.
	 *
	 * @param c az uj objektum
	 * @return sikerult-e beallitani (ha nem akkor a mezo nem ures)
	 */
	public boolean setObject(Collidable c) {
		Skeleton.enterMethod(type, id, "setObject(Collidable)");
		boolean flag = true;
		if (objectOnField == null) {
			objectOnField = c;
			objectOnField.setField(this);
		} else {
			flag = false;
		}
		Skeleton.exitMethod(type, id, "setObject(Collidable)");
		return flag;
	}

	/**
	 * Torli a mezo objektumat, valamint az objektumban is torolteti
	 * a mezore valo referenciat.
	 *
	 * @param c a torlendo objektum
	 */
	public void unsetObject(Collidable c) {
		Skeleton.enterMethod(type, id, "unsetObject(Collidable)");
		if (objectOnField != null) {
			if (objectOnField.equals(c)) {
				objectOnField.setField(null);
				objectOnField = null;
			}
		}
		Skeleton.exitMethod(type, id, "unsetObject(Collidable)");
	}

	/**
	 * Megmondja, hogy a mezo ures-e.
	 *
	 * @return a mezo uressegere vonatkozo logikai ertek
	 */
	public boolean isEmpty() {
		Skeleton.enterMethod(type, id, "isEmpty()");
		Skeleton.exitMethod(type, id, "isEmpty()");
		return (objectOnField == null);
	}

	/**
	 * Szomszedot allit be.
	 *
	 * @param dir melyik iranyu szomszed legyen allitva
	 * @param field a szomszed referenciaja
	 */
	public void setNeighbour(Direction dir, Field field) {
		Skeleton.enterMethod(type, id, "setNeighbour(Direction, Field)");
		neighbours.put(dir, field);
		field.neighbours.put(dir.reverse(), this);
		Skeleton.exitMethod(type, id, "setNeighbour(Direction, Field)");
	}
}
