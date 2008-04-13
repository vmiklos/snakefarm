package snakefarm;

/**
 * Ez a class valositja meg a kigyonak egy haladasi iranyat.
 */
public class Direction {

	private static int lastid = 0;
	private int id = lastid;
	/**
	 * Megadja, hogy maximum hany iranyban allhat a kigyo.
	 */
	public static final int numberOfDirections = 4;
	/**
	 * Az iranyt tarolja: 0 - jobb, 1 - fel, 2 - balra, 3 - le.
	 */
	private int dir;
	private boolean turnedLeft = false;

	/**
	 * Beallitja az iranyt.
	 *
	 * @param dir az uj irany
	 */
	public Direction(int dir) {
		lastid++;
		this.dir = dir;
	}

	/**
	 * Az irany osztaly konstruktora.
	 */
	public Direction() {
		lastid++;
		this.dir = (int) (Math.random() * (double) numberOfDirections);
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
		if(turnedLeft)
		{
			Proto.out.println("StepEvent TurnLeft");
			turnedLeft = false;
		}
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
		turnedLeft = true;
		dir = (dir + 1) % numberOfDirections;
	}

	/**
	 * Jobbra forditja az iranyt.
	 */
	public void turnRight() {
		dir = (dir + numberOfDirections - 1) % numberOfDirections;
	}

	/**
	 * Az ellenkezojere forditja az iranyt.
	 */
	public Direction reverse() {
		return new Direction((dir + (numberOfDirections / 2)) % numberOfDirections);
	}
}
