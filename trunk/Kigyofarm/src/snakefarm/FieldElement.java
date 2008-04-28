package snakefarm;

/**
 * Mezore teheto elemek ososztalya
 */
public abstract class FieldElement extends Viewable {
	/**
	 * a mezo amin az elem all
	 */
	protected Field field;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

}
