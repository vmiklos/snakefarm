package snakefarm;

import java.util.HashMap;

/**
 * Egy jatekmezot valosit meg.
 */
public class Field {

	private static int lastid = 0;
	public int id;
	private HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>(Direction.numberOfDirections);
	private Collidable objectOnField = null;

	/**
	 * A jatekmezo konstruktora.
	 */
	public Field(int id) {
		this.id = id;
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
		objectOnField = null;
	}

	/**
	 * Kovetkezo mezo lekerese.
	 *
	 * @param dir megadja, hogy milyen iranyban kovetkezo
	 * @return a kovetkezo mezo
	 */
	public Field getNext(Direction dir) {
		return neighbours.get(dir);
	}

	/**
	 * Beallitja a mezo objektumat, ha a mezo ures.
	 *
	 * @param c az uj objektum
	 * @return sikerult-e beallitani (ha nem akkor a mezo nem ures)
	 */
	public boolean setObject(Collidable c) {
		boolean flag = true;
		if (objectOnField == null) {
			objectOnField = c;
			objectOnField.setField(this);
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * Torli a mezo objektumat, valamint az objektumban is torolteti
	 * a mezore valo referenciat.
	 *
	 * @param c a torlendo objektum
	 */
	public void unsetObject(Collidable c) {
		if (objectOnField != null) {
			if (objectOnField.equals(c)) {
				objectOnField.setField(null);
				objectOnField = null;
			}
		}
	}

	public String getObjectString()
	{
		if(objectOnField == null)
			return "0";
		String type = objectOnField.getClass().getName();
		if(type.equals("snakefarm.Wall"))
			return "W";
		else if(type.equals("snakefarm.FieldBerry"))
			return "F";
		else if(type.equals("snakefarm.StoneBerry"))
			return "T";
		else if(type.equals("snakefarm.SawBerry"))
			return "A";
		return "0";
	}

	/**
	 * Megmondja, hogy a mezo ures-e.
	 *
	 * @return a mezo uressegere vonatkozo logikai ertek
	 */
	public boolean isEmpty() {
		return (objectOnField == null);
	}

	/**
	 * Szomszedot allit be.
	 *
	 * @param dir melyik iranyu szomszed legyen allitva
	 * @param field a szomszed referenciaja
	 */
	public void setNeighbour(Direction dir, Field field) {
		neighbours.put(dir, field);
		if(field != null)
		{
			field.neighbours.put(dir.reverse(), this);
		}
	}
}
