package snakefarm;

import snakefarm.viewfactories.FieldViewFactory;
import snakefarm.views.BaseView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Egy jatekmezot valosit meg.
 */
public class Field extends Viewable {


	private HashMap<Direction, Field> neighbours = new HashMap<Direction, Field>(Direction.numberOfDirections);
	private Collidable objectOnField = null;
	private Coordinate coordinate;
	private FieldViewFactory factory = new FieldViewFactory();

	/**
	 * A jatekmezo konstruktora.
	 */
	public Field(Coordinate coordinate) {
		this.coordinate = coordinate;
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
		if (objectOnField.equals(snakeUnit)) {
			objectOnField = null;
		}
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
		if (field != null) {
			field.neighbours.put(dir.reverse(), this);
		}
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	/**
	 * megmondja, milyen iranyu szomszedja a masik mezo
	 * @param f a mezo, aminek az iranya erdekel
	 * @return az irany, null ha nem szomszedja
	 */
	public Direction getDirection(Field f) {
		for (Iterator i=neighbours.entrySet().iterator(); i.hasNext();) {
			Map.Entry current = (Map.Entry) i.next();
			if (current.getValue() == f) return (Direction) current.getKey();
		}
		return null;
	}

	public Viewable getViewable() {
		return objectOnField;
	}

	/**
	 * modell elemhez tartozo nezet letrehozasa
	 * @return az elemhez tartozo nezet objektum
	 */
	@Override
	protected BaseView genBaseView() {
		return factory.genBaseView(this);
	}
}
