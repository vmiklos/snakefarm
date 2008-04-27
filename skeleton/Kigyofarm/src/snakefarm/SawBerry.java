package snakefarm;

/**
 * Fureszbogyo objektum. Csupan a Collidable classbol szarmazo
 * utkozes fuggvenyt kell megvalositani a helyes mukodesehez.
 */
public class SawBerry extends Collidable {

	/**
	 * A fureszbogyo konstruktora.
	 */
	public SawBerry(Field field) {
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

	@Override
	protected BaseView genBaseView() {
		return new SawBerryView(this);
	}
}
