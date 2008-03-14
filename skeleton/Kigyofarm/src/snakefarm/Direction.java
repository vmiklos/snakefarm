package snakefarm;

public class Direction {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Direction";
	private int dir;
	private Snake unnamed_Snake_;
	
	public Direction(UserInterface ui) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Direction()");
		ui.exitMethod(type, id, "Direction()");
	}
	public void turnLeft() {
		ui.enterMethod(type, id, "turnLeft()");
		ui.exitMethod(type, id, "turnLeft()");
	}

	public void turnRight() {
		ui.enterMethod(type, id, "turnRight()");
		ui.exitMethod(type, id, "turnRight()");
	}
}