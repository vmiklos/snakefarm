package snakefarm.viewfactories;

import snakefarm.views.BaseView;
import snakefarm.views.StoneBerryView;
import snakefarm.StoneBerry;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
public class StoneBerryViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	public BaseView genBaseView(StoneBerry o) {
		return new StoneBerryView(o);
	}

}
