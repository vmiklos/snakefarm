package snakefarm;

/**
 * Fureszbogyo objektum. Csupan a Collidable classbol szarmazo
 * utkozes fuggvenyt kell megvalositani a helyes mukodesehez.
 */
public class SawBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "SawBerry";
	private SawBerryViewFactory factory = new SawBerryViewFactory();

	/**
	 * A fureszbogyo konstruktora.
	 */
	public SawBerry(Field field) {
		lastid++;
		this.field = field;
		field.setObject(this);
	}

	/**
	 * Utkoztet egy kigyoelemet a fureszbogyoval.
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
