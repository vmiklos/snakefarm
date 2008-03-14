package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class GameField {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="GameField";
	private Game unnamed_Game_;
	private List<Field> fields = new LinkedList<Field>();
	
	public GameField(UserInterface ui) {
		this.ui=ui;
		lastid++;
	}
}