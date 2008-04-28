package snakefarm;

/**
 * Factory osztaly a view letrehozas levalasztasahoz a modellrol
 */
class WallViewFactory {

	/**
	 * letrehozza a modellt megjelenito view objektumot
	 * @param o specifikus modell elem
	 * @return letrejott view elem
	 */
	BaseView genBaseView(Wall o) {
		return new WallView(o);
	}

}
