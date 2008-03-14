package snakefarm;

public class FieldBerry extends Collidable {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="FieldBerry";
	
	public FieldBerry(UserInterface ui) {
		this.ui=ui;
		lastid++;
	}
}