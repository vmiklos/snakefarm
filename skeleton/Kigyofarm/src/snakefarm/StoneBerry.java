package snakefarm;

public class StoneBerry extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "StoneBerry";

	public StoneBerry(Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "StoneBerry()");
		this.field = field;
		field.setObject(this);
		Skeleton.exitMethod(type, id, "StoneBerry()");
	}

	public void collideWith(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		field.unsetObject(this);
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
}
