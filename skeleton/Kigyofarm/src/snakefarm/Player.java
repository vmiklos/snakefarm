package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class Player {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Player";
	/*private Player unnamed_Player_;
	private Player unnamed_Player_33;*/
	private Game game;
	private List<Snake> snakes=new LinkedList<Snake>();
	
	public Player(UserInterface ui, Game game) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Player(Game)");
		this.game=game;
		ui.exitMethod(type, id, "Player(Game)");
	}

	public void addSnake(Field field) {
		ui.enterMethod(type, id, "addSnake()");
		snakes.add(new Snake(ui, this, field));
		ui.exitMethod(type, id, "addSnake()");
	}

	public void step() {
		ui.enterMethod(type, id, "step()");
		for (java.util.Iterator i=snakes.iterator(); i.hasNext(); ) {
			((Snake)i.next()).step();
		}
		ui.exitMethod(type, id, "step()");
	}

	public void removeSnake(Snake snake) {
		snakes.remove(snake);
		throw new UnsupportedOperationException();
	}
}