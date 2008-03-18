package snakefarm;

/**
 * Vilagobjektum. Ebbol a classbol oroklodik az osszes mezon
 * megtalalhato elem, amelyek kepes utkozni.
 */
public abstract class Collidable {

	/**
	 * Ez a referencia arra a mezore mutat amin jelenleg allunk.
	 */
	protected Field field;

	/**
	 * Utkozik a SnakeUnit kigyo egyseggel es visszater az utkozes
	 * utan maradt vilagobjektummal.
	 *
	 * @param utkozo kigyoelem
	 */
	public abstract void collideWith(SnakeUnit snakeUnit);

	/**
	 * Alapertelmezettel ugyanaz, mint a collideWith(), de a
	 * SnakeUnitnal override-olja.
	 */
	public void collideWithSaw(SnakeUnit snakeUnit) {
		collideWith(snakeUnit);
	}

	/**
	 * Beallitja, hogy melyik mezon vagyunk.
	 *
	 * @param field az uj mezo referenciaja
	 */
	public void setField(Field field) {
		this.field = field;
	}
}
