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
	private SkeletonInterface si;
	private static final String type = "Direction";
	public static final int max = 3;
	private int dir;

	public Direction(SkeletonInterface si, int dir) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "Direction(int)");
		this.dir = dir;
		si.exitMethod(type, id, "Direction(int)");
	}

	public Direction(SkeletonInterface si) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "Direction()");
		this.dir = (int) (Math.random() * (double) max);
		si.exitMethod(type, id, "Direction()");
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
		si.enterMethod(type, id, "turnLeft()");
		dir = (dir + 1) % max;
		si.exitMethod(type, id, "turnLeft()");
	}

	public void turnRight() {
		si.enterMethod(type, id, "turnRight()");
		dir = (dir + max - 1) % max;
		si.exitMethod(type, id, "turnRight()");
	}

	public Direction reverse() {
		si.enterMethod(type, id, "reverse()");
		si.exitMethod(type, id, "reverse()");
		return new Direction(si, (dir + (max / 2)) % max);
	}
}
