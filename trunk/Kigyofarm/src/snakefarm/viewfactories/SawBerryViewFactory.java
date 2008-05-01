package snakefarm.viewfactories;

import snakefarm.views.BaseView;
import snakefarm.views.SawBerryView;
import snakefarm.SawBerry;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
public class SawBerryViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	public BaseView genBaseView(SawBerry o) {
		return new SawBerryView(o);
	}

}
