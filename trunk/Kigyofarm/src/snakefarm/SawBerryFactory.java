package snakefarm;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
class SawBerryViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	BaseView genBaseView(SawBerry o) {
		return new SawBerryView(o);
	}

}
