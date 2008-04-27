package snakefarm;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;

/**
 * Jatekter osztaly, amely a mezoket tartalmazza. Jatek inditaskor
 * felepiti a jatekteret (letrehozza a mezoket es beallitja a
 * szomszedokat).
 */
public class GameField {

	private Game game;
	private List<Field> fields;

	/**
	 * A jatekter konstruktora.
	 *
	 * @param game A jatek amelyben a jatekter letrejon.
	 */
	public GameField(Game game) {
		this.game = game;
		fields = Skeleton.getCurrentLevel().createGameField(this);
		/*FIXME nincs skeleton!*/
	}

	/**
	 * Visszaad veletlenszeruen egy mezot a jatekterbol.
	 * 
	 * @return a veletlenszeru mezo
	 */
	public Field GetRandomFreeField() {
		LinkedList<Field> freeFields = new LinkedList<Field>();
		int index;
		for (java.util.Iterator i = fields.listIterator(); i.hasNext();) {
			Field field = (Field) i.next();
			if (field.isEmpty()) {
				freeFields.add(field);
			}
		}
		index = (int) ((double) (freeFields.size()) * Math.random());
		if (freeFields.size() != 0) {
			return freeFields.get(index);
		} else {
			return null;
		}
	}

	List<Viewable> getViewables() {
		return new LinkedList<Viewable>(fields);
	}
}
