package snakefarm.viewfactories;

import snakefarm.views.BaseView;
import snakefarm.views.FieldBerryView;
import snakefarm.FieldBerry;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
public class FieldBerryViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	public BaseView genBaseView(FieldBerry o) {
		return new FieldBerryView(o);
	}

}
