package snakefarm;

/**
 * Fal objektum.
 * <p>
 * Ez veszi körül a pályát. Ha belemegy a kígyó, meghal.
 */
public class Wall extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Wall";

	/**
	 * Fal konstruktora.
	 *
	 * @param field melyik mezon jojjon letre
	 */
	public Wall(Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "Wall()");
		this.field = field;
		field.setObject(this);
		Skeleton.exitMethod(type, id, "Wall()");
	}

	/**
	 * Utkozes kigyoelemmel.
	 *
	 * @param snakeUnit utkozo kigyoelem
	 */
	public void collideWith(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}

	/*
	 * Utkozes fureszmodban levo kigyoelemmel.
	 *
	 * @param snakeUnit utkozo kigyoelem
	 */
	@Override
	public void collideWithSaw(SnakeUnit snakeUnit) {
		/* TODO: wtf? nem tokmind1, hha falnak megyunk akkor
		 * van-e fureszunk? (vmiklos) */
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
}
