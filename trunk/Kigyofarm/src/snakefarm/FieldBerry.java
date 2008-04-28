package snakefarm;

/**
 * Mezei bogyot megvalosito osztaly.
 */
public class FieldBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private FieldBerryViewFactory factory = new FieldBerryViewFactory();

	/**
	 * A mezei bogyo konstruktora.
	 *
	 * @param field melyik mezore keruljon a bogyo
	 */
	public FieldBerry(Field field) {
		lastid++;
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

	/**
	 * modell elemhez tartozo nezet letrehozasa
	 * @return az elemhez tartozo nezet objektum
	 */
	@Override
	protected BaseView genBaseView() {
		return factory.genBaseView(this);
	}
}
