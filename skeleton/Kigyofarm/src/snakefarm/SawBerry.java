package snakefarm;

public class SawBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "SawBerry";

	public SawBerry(Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "SawBerry()");
		this.field = field;
		field.setObject(this);
		Skeleton.exitMethod(type, id, "SawBerry()");
	}

	public void collideWith(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		field.unsetObject(this);
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
}
