package snakefarm;

/**
 * Kobogyo osztaly.
 */
public class StoneBerry extends Collidable {

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

	@Override
	protected BaseView genBaseView() {
		return new StoneBerryView(this);
	}
}
