package snakefarm;

import snakefarm.creators.GameFieldCreator;
import snakefarm.viewfactories.GameFieldViewFactory;
import snakefarm.views.BaseView;
import java.io.*;
import java.util.*;

/**
 * Jatekter osztaly, amely a mezoket tartalmazza. Jatek inditaskor
 * felepiti a jatekteret (letrehozza a mezoket es beallitja a
 * szomszedokat).
 */
public class GameField extends Viewable {

	private Game game;
	private List<Field> fields;
	private GameFieldViewFactory factory = new GameFieldViewFactory();

	/**
	 * A jatekter konstruktora.
	 *
	 * @param game A jatek amelyben a jatekter letrejon.
	 */
	public GameField(Game game, GameFieldCreator gameFieldCreator) {
		this.game = game;
		fields = gameFieldCreator.getFields();
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
	
	/**
	 * a jatekteren levo Viewable-ok lekerdezese
	 * @return a jatekteren levo Viewable-ok listaja
	 */
	public List<Viewable> getViewables() {
		return new LinkedList<Viewable>(fields);
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
