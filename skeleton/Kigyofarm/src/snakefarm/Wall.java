package snakefarm;

public class Wall extends Collidable {

	private static int lastid = 0;
	private int id = lastid;
	private SkeletonInterface si;
	private static final String type = "Wall";

	public Wall(SkeletonInterface si) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "Wall()");
		si.exitMethod(type, id, "Wall()");
	}

	@Override
	public void collideWith(SnakeUnit snakeUnit) {
		si.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		si.exitMethod(type, id, "collideWith(SnakeUnit)");
	}

	@Override
	public void collideWithSaw(SnakeUnit snakeUnit) {
		si.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		si.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
}
