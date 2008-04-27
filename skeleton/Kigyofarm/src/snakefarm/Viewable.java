package snakefarm;

/**
 *
 * @author cassus
 */
abstract public class Viewable {
	private BaseView baseView = null;

	abstract protected BaseView genBaseView();
	
	/**
	 * cache-eljuk a meglevo view-eket
	 * @return
	 */
	public BaseView getBaseView() {
		if(baseView != null) {
			return baseView;
		} else {
			return baseView = genBaseView();
		}
	}
}
