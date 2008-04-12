package snakefarm;

/**
 * Fal objektum.
 * <p>
 * Ez veszi korul a palyat. Ha belemegy a kigyo, meghal.
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

	/*
	 * Utkozes fureszmodban levo kigyoelemmel.
	 *
	 * @param snakeUnit utkozo kigyoelem
	 */
	@Override
	public void collideWithSaw(SnakeUnit snakeUnit) {
		/* TODO: wtf? nem tokmind1, hha falnak megyunk akkor
		 * van-e fureszunk? (vmiklos) */
		snakeUnit.collideWith2(this);
	}
}
