package snakefarm;
/*
 * 0 - right
 * 1 - up
 * 2 - left
 * 3 - down
 * */

public class Direction {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Direction";
	
	public static final int max=3;
	
	private int dir;
	
	public Direction(UserInterface ui, int dir) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Direction(int)");
		this.dir=dir;
		ui.exitMethod(type, id, "Direction(int)");
	}
	
	public Direction(UserInterface ui) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Direction()");
		this.dir=(int)(Math.random()*(double)max);
		ui.exitMethod(type, id, "Direction()");
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
		ui.enterMethod(type, id, "turnLeft()");
		dir=(dir+1)%max;
		ui.exitMethod(type, id, "turnLeft()");
	}

	public void turnRight() {
		ui.enterMethod(type, id, "turnRight()");
		dir=(dir+max-1)%max;
		ui.exitMethod(type, id, "turnRight()");
	}

	public Direction reverse() {
		ui.enterMethod(type, id, "reverse()");
		ui.exitMethod(type, id, "reverse()");
		return new Direction(ui, (dir+(max/2))%max);
	}
}