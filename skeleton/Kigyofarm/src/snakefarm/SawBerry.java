package snakefarm;

public class SawBerry extends Collidable {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="SawBerry";
	
	public SawBerry(UserInterface ui) {
		this.ui=ui;
		lastid++;
	}
}