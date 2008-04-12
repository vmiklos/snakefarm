package snakefarm;

/**
 * Fureszbogyo objektum. Csupan a Collidable classbol szarmazo
 * utkozes fuggvenyt kell megvalositani a helyes mukodesehez.
 */
public class SawBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "SawBerry";

	/**
	 * A fureszbogyo konstruktora.
	 */
	public SawBerry(Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "SawBerry()");
		this.field = field;
		field.setObject(this);
		Skeleton.exitMethod(type, id, "SawBerry()");
	}

	/**
	 * Utkoztet egy kigyoelemet a fureszbogyoval.
	 */
	public void collideWith(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		field.unsetObject(this);
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
}
