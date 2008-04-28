package snakefarm;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
class FieldBerryViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	BaseView genBaseView(FieldBerry o) {
		return new FieldBerryView(o);
	}

}
