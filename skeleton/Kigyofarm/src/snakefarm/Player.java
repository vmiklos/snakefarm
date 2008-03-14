package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class Player {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Player";
	private Player unnamed_Player_;
	private Player unnamed_Player_33;
	private Game unnamed_Game_;
	private List<Snake> unnamed_Snake_ = new LinkedList<Snake>();
	
	public Player(UserInterface ui, Game game) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Player(Game)");
		unnamed_Game_=game;
		ui.exitMethod(type, id, "Player(Game)");
	}

	public void addSnake() {
		ui.enterMethod(type, id, "addSnake()");
		unnamed_Snake_.add(new Snake(ui, this));
		ui.exitMethod(type, id, "addSnake()");
	}

	public void step() {
		ui.enterMethod(type, id, "step()");
		for (java.util.Iterator i=unnamed_Snake_.iterator(); i.hasNext(); ) {
			((Snake)i.next()).step();
		}
		ui.exitMethod(type, id, "step()");
	}

	public void removeSnake(Snake snake) {
		throw new UnsupportedOperationException();
	}
}