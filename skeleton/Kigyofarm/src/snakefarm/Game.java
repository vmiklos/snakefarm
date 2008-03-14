package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class Game {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Game";
	private GameField unnamed_GameField_;
	private List<Player> unnamed_Player_ = new LinkedList<Player>();
	
	public Game(UserInterface ui) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Game()");
		ui.exitMethod(type, id, "Game()");
	}
	public void newPlayer() {
		ui.enterMethod(type, id, "newPlayer()");
		Player temp=new Player(ui, this);
		unnamed_Player_.add(temp);
		temp.addSnake();
		ui.exitMethod(type, id, "newPlayer()");
	}

	public void step() {
		ui.enterMethod(type, id, "step()");
		for (java.util.Iterator i=unnamed_Player_.iterator(); i.hasNext(); ) {
			((Player)i.next()).step();
		}
		ui.exitMethod(type, id, "step()");
	}
}