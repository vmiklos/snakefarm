package snakefarm.viewfactories;

import snakefarm.views.BaseView;
import snakefarm.views.GameFieldView;
import snakefarm.GameField;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
public class GameFieldViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	public BaseView genBaseView(GameField o) {
		return new GameFieldView(o);
	}

}
