package snakefarm;

/**
 * Vilagobjektum. Ebbol a classbol oroklodik az osszes mezon
 * megtalalhato elem, amelyek kepes utkozni.
 */
public abstract class Collidable extends FieldElement{

	/**
	 * Utkozik a SnakeUnit kigyo egyseggel es visszater az utkozes
	 * utan maradt vilagobjektummal.
	 *
	 * @param snakeUnit utkozo kigyoelem
	 */
	public abstract void collideWith(SnakeUnit snakeUnit);

	/**
	 * Alapertelmezettel ugyanaz, mint a collideWith(), de a
	 * SnakeUnitnal override-olja.
	 */
	public void collideWithSaw(SnakeUnit snakeUnit) {
		collideWith(snakeUnit);
	}
}
