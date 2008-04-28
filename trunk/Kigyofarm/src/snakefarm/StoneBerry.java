package snakefarm;

/**
 * Kobogyo osztaly.
 */
public class StoneBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "StoneBerry";
	private StoneBerryFactory factory = new StoneBerryFactory();

	/**
	 * Kobogyo konstruktora.
	 *
	 * @param field melyik mezon jojjon letre
	 */
	public StoneBerry(Field field) {
		lastid++;
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
