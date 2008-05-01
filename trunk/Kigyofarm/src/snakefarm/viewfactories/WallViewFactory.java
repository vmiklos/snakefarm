package snakefarm.viewfactories;

import snakefarm.views.WallView;
import snakefarm.views.BaseView;
import snakefarm.Wall;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
public class WallViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	public BaseView genBaseView(Wall o) {
		return new WallView(o);
	}

}
