package snakefarm;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
class FieldViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	BaseView genBaseView(Field o) {
		return new FieldView(o);
	}

}
