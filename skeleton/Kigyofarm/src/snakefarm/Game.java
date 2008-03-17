package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class Game {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Game";
	private GameField gameField;
	private List<Player> players = new LinkedList<Player>();

	public Game() {
		lastid++;
		Skeleton.enterMethod(type, id, "Game()");
		gameField = new GameField(this);
		Skeleton.exitMethod(type, id, "Game()");
	}

	public void newPlayer() {
		Skeleton.enterMethod(type, id, "newPlayer()");
		Player player = new Player(this);
		players.add(player);
		player.addSnake(gameField.GetRandomFreeField());
		Skeleton.exitMethod(type, id, "newPlayer()");
	}

	public void step() {
		Skeleton.enterMethod(type, id, "step()");
		for (java.util.Iterator i = players.iterator(); i.hasNext();) {
			((Player) (i.next())).step();
		}
		Skeleton.exitMethod(type, id, "step()");
	}
}
