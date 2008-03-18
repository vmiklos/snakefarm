package snakefarm;

/**
 * Mezei bogyot megvalosito osztaly.
 */
public class FieldBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "FieldBerry";

	/**
	 * A mezei bogyo konstruktora.
	 *
	 * @param field melyik mezore keruljon a bogyo
	 */
	public FieldBerry(Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "FieldBerry()");
		this.field = field;
		field.setObject(this);
		Skeleton.exitMethod(type, id, "FieldBerry()");
	}

	/**
	 * A mezei bogyo es kigyoelem utkozesenek kezelese.
	 *
	 * @param snakeUnit melyik kigyoelemmel utkozzon
	 */
	public void collideWith(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		field.unsetObject(this);
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
}
