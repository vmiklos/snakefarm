package snakefarm;

/**
 * Ez a class valositja meg a kigyonak egy haladasi iranyat.
 */
public class Direction {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Direction";
	/**
	 * Megadja, hogy maximum hany iranyban allhat a kigyo.
	 */
	public static final int numberOfDirections = 4;
	/**
	 * Az iranyt tarolja: 0 - jobb, 1 - fel, 2 - balra, 3 - le.
	 */
	private int dir;

	/**
	 * Beallitja az iranyt.
	 *
	 * @param dir az uj irany
	 */
	public Direction(int dir) {
		lastid++;
		Skeleton.enterMethod(type, id, "Direction(int)");
		this.dir = dir;
		Skeleton.exitMethod(type, id, "Direction(int)");
	}

	/**
	 * Az irany osztaly konstruktora.
	 */
	public Direction() {
		lastid++;
		Skeleton.enterMethod(type, id, "Direction()");
		this.dir = (int) (Math.random() * (double) numberOfDirections);
		Skeleton.exitMethod(type, id, "Direction()");
	}

	/**
	 * Visszaad egy hash kodot az objektumra.
	 */
	@Override
	public int hashCode() {
		return dir;
	}

	/**
	 * Osszehasonlitja az objektumot egy masik irannyal.
	 *
	 * @return az osszeshasonlitas eredmenye
	 */
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

	/**
	 * Balra forditja az iranyt.
	 */
	public void turnLeft() {
		Skeleton.enterMethod(type, id, "turnLeft()");
		dir = (dir + 1) % numberOfDirections;
		Skeleton.exitMethod(type, id, "turnLeft()");
	}

	/**
	 * Jobbra forditja az iranyt.
	 */
	public void turnRight() {
		Skeleton.enterMethod(type, id, "turnRight()");
		dir = (dir + numberOfDirections - 1) % numberOfDirections;
		Skeleton.exitMethod(type, id, "turnRight()");
	}

	/**
	 * Az ellenkezojere forditja az iranyt.
	 */
	public Direction reverse() {
		Skeleton.enterMethod(type, id, "reverse()");
		Skeleton.exitMethod(type, id, "reverse()");
		return new Direction((dir + (numberOfDirections / 2)) % numberOfDirections);
	}
}
