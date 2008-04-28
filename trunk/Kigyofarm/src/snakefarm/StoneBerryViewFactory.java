package snakefarm;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
class StoneBerryViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	BaseView genBaseView(StoneBerry o) {
		return new StoneBerryView(o);
	}

}
