package snakefarm.viewfactories;

import snakefarm.views.BaseView;
import snakefarm.views.FieldView;
import snakefarm.Field;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
public class FieldViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	public BaseView genBaseView(Field o) {
		return new FieldView(o);
	}

}
