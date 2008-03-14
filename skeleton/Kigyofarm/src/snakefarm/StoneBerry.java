package snakefarm;

public class StoneBerry extends Collidable {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="StoneBerry";
	
	public StoneBerry(UserInterface ui) {
		this.ui=ui;
		lastid++;
	}
}