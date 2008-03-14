package snakefarm;

public class Wall extends Collidable {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Wall";
	
	public Wall(UserInterface ui) {
		this.ui=ui;
		lastid++;
	}
}