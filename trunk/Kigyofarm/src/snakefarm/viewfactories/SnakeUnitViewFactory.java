package snakefarm.viewfactories;

import snakefarm.views.BaseView;
import snakefarm.views.SnakeUnitView;
import snakefarm.SnakeUnit;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
public class SnakeUnitViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	public BaseView genBaseView(SnakeUnit o) {
		return new SnakeUnitView(o);
	}

}
