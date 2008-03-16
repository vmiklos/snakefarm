package snakefarm;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class Player {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Player";
	
	private Game game;
	private List<Snake> snakes=new LinkedList<Snake>();
	private List<Snake> temp=new LinkedList<Snake>();
	
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
		temp.addAll(snakes);
		for (Iterator i=temp.iterator(); i.hasNext(); ) {
			((Snake)i.next()).step();
		}
		temp.clear();
		ui.exitMethod(type, id, "step()");
	}

	public void removeSnake(Snake snake) {
		ui.enterMethod(type, id, "removeSnake(Snake)");
		snakes.remove(snake);
		ui.exitMethod(type, id, "removeSnake(Snake)");
	}
}