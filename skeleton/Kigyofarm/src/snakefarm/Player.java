package snakefarm;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class Player {

	private static int lastid = 0;
	private int id = lastid;
	private SkeletonInterface si;
	private static final String type = "Player";
	private Game game;
	private List<Snake> snakes = new LinkedList<Snake>();
	private List<Snake> temp = new LinkedList<Snake>();

	public Player(SkeletonInterface si, Game game) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "Player(Game)");
		this.game = game;
		si.exitMethod(type, id, "Player(Game)");
	}

	public void addSnake(Field field) {
		si.enterMethod(type, id, "addSnake()");
		snakes.add(new Snake(si, this, field));
		si.exitMethod(type, id, "addSnake()");
	}

	public void step() {
		si.enterMethod(type, id, "step()");
		temp.addAll(snakes);
		for (Iterator i = temp.iterator(); i.hasNext();) {
			((Snake) i.next()).step();
		}
		temp.clear();
		si.exitMethod(type, id, "step()");
	}

	public void removeSnake(Snake snake) {
		si.enterMethod(type, id, "removeSnake(Snake)");
		snakes.remove(snake);
		si.exitMethod(type, id, "removeSnake(Snake)");
	}
}
