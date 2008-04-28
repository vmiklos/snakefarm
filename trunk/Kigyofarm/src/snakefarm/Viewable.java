package snakefarm;

/**
 * megjelenitheto modell elemek ososztalya
 */
abstract public class Viewable {
	/**
	 * a modell elemnek megfelelo nezet elem
	 *  cache / singleton szerepet tolt be
	 */
	private BaseView view = null;

	/**
	 * a modell elemhez tartozo nezet elem letrehozasa
	 * @return a modell elemhez tartozo nezet elem
	 */
	abstract protected BaseView genBaseView();

	/**
	 * modell elemhez tartozo view lekerdezese
	 * ha ez az elso keres, akkor letrejon a view elem, es cache-eljuk
	 * ha a tobbedik keres, akkor mar a cache-bol szolgaljuk ki
	 * @return a modell elemhez tartozo nezet elem
	 */
	public BaseView getBaseView() {
		if(view != null) {
			return view;
		} else {
			return view = genBaseView();
		}
	}
}
