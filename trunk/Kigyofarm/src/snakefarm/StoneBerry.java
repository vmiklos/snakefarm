package snakefarm;

import snakefarm.viewfactories.StoneBerryViewFactory;
import snakefarm.views.BaseView;

/**
 * Kobogyo osztaly.
 */
public class StoneBerry extends Collidable {

	private StoneBerryViewFactory factory = new StoneBerryViewFactory();

	/**
	 * Kobogyo konstruktora.
	 *
	 * @param field melyik mezon jojjon letre
	 */
	public StoneBerry(Field field) {
		this.field = field;
		field.setObject(this);
	}

	/**
	 * Utkozes kigyoelemmel
	 *
	 * @param snakeUnit utkozo kigyoelem
	 */
	public void collideWith(SnakeUnit snakeUnit) {
		snakeUnit.collideWith2(this);
		field.unsetObject(this);
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
