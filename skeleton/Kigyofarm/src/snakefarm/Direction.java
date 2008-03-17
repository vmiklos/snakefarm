package snakefarm;
/*
 * 0 - right
 * 1 - up
 * 2 - left
 * 3 - down
 * */

public class Direction {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Direction";
	public static final int numberOfDirections = 4;
	private int dir;

	public Direction(int dir) {
		lastid++;
		Skeleton.enterMethod(type, id, "Direction(int)");
		this.dir = dir;
		Skeleton.exitMethod(type, id, "Direction(int)");
	}

	public Direction() {
		lastid++;
		Skeleton.enterMethod(type, id, "Direction()");
		this.dir = (int) (Math.random() * (double) numberOfDirections);
		Skeleton.exitMethod(type, id, "Direction()");
	}

	@Override
	public int hashCode() {
		return dir;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Direction other = (Direction) obj;
		if (this.dir != other.dir) {
			return false;
		}
		return true;
	}

	public void turnLeft() {
		Skeleton.enterMethod(type, id, "turnLeft()");
		dir = (dir + 1) % numberOfDirections;
		Skeleton.exitMethod(type, id, "turnLeft()");
	}

	public void turnRight() {
		Skeleton.enterMethod(type, id, "turnRight()");
		dir = (dir + numberOfDirections - 1) % numberOfDirections;
		Skeleton.exitMethod(type, id, "turnRight()");
	}

	public Direction reverse() {
		Skeleton.enterMethod(type, id, "reverse()");
		Skeleton.exitMethod(type, id, "reverse()");
		return new Direction((dir + (numberOfDirections / 2)) % numberOfDirections);
	}
}
