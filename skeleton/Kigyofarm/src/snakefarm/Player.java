package snakefarm;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class Player {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Player";
	private Game game;
	private List<Snake> snakes = new LinkedList<Snake>();
	private List<Snake> temp = new LinkedList<Snake>();

	public Player(Game game) {
		lastid++;
		Skeleton.enterMethod(type, id, "Player(Game)");
		this.game = game;
		Skeleton.exitMethod(type, id, "Player(Game)");
	}

	public void addSnake(Field field) {
		Skeleton.enterMethod(type, id, "addSnake(Field)");
		snakes.add(new Snake(this, field));
		Skeleton.exitMethod(type, id, "addSnake(Field)");
	}

	public void step() {
		Skeleton.enterMethod(type, id, "step()");
		temp.addAll(snakes);
		for (Iterator i = temp.iterator(); i.hasNext();) {
			((Snake) i.next()).step();
		}
		temp.clear();
		Skeleton.exitMethod(type, id, "step()");
	}

	public void removeSnake(Snake snake) {
		Skeleton.enterMethod(type, id, "removeSnake(Snake)");
		snakes.remove(snake);
		Skeleton.exitMethod(type, id, "removeSnake(Snake)");
	}
}
