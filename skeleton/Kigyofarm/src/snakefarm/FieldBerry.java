package snakefarm;

/**
 * Mezei bogyot megvalosito osztaly.
 */
public class FieldBerry extends Collidable {

	/**
	 * A mezei bogyo konstruktora.
	 *
	 * @param field melyik mezore keruljon a bogyo
	 */
	public FieldBerry(Field field) {
		this.field = field;
		field.setObject(this);
	}

	/**
	 * A mezei bogyo es kigyoelem utkozesenek kezelese.
	 *
	 * @param snakeUnit melyik kigyoelemmel utkozzon
	 */
	public void collideWith(SnakeUnit snakeUnit) {
		snakeUnit.collideWith2(this);
		field.unsetObject(this);
	}

	@Override
	protected BaseView genBaseView() {
		return new FieldBerryView(this);
	}
}
