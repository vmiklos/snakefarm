package snakefarm;

public class FieldBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "FieldBerry";

	public FieldBerry(Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "FieldBerry()");
		this.field = field;
		field.setObject(this);
		Skeleton.exitMethod(type, id, "FieldBerry()");
	}

	public void collideWith(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		field.unsetObject(this);
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
}
