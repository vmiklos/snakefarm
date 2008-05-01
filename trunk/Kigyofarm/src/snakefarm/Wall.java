package snakefarm;

import snakefarm.viewfactories.WallViewFactory;
import snakefarm.views.BaseView;

/**
 * Fal objektum.
 * <p>
 * Ez veszi korul a palyat. Ha belemegy a kigyo, meghal.
 */
public class Wall extends Collidable {

	private WallViewFactory factory = new WallViewFactory();

	/**
	 * Fal konstruktora.
	 *
	 * @param field melyik mezon jojjon letre
	 */
	public Wall(Field field) {
		this.field = field;
		field.setObject(this);
	}

	/**
	 * Utkozes kigyoelemmel.
	 *
	 * @param snakeUnit utkozo kigyoelem
	 */
	public void collideWith(SnakeUnit snakeUnit) {
		snakeUnit.collideWith2(this);
	}

	/**
	 * modell elemhez tartozo nezet letrehozasa
	 * @return az elemhez tartozo nezet objektum
	 */
	@Override
	protected BaseView genBaseView() {
		return factory.genBaseView(this);
	}
}
