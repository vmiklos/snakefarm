package snakefarm;

public class Wall extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Wall";

	public Wall(Field field) {
		lastid++;
		Skeleton.enterMethod(type, id, "Wall()");
		this.field = field;
		field.setObject(this);
		Skeleton.exitMethod(type, id, "Wall()");
	}

	public void collideWith(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}

	@Override
	public void collideWithSaw(SnakeUnit snakeUnit) {
		Skeleton.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		Skeleton.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
}
