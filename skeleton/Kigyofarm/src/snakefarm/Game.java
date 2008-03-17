package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class Game {

	private static int lastid = 0;
	private int id = lastid;
	private SkeletonInterface si;
	private static final String type = "Game";
	private GameField gameField;
	private List<Player> players = new LinkedList<Player>();

	public Game(SkeletonInterface si) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "Game()");
		gameField = new GameField(si, this);
		si.exitMethod(type, id, "Game()");
	}

	public void newPlayer() {
		si.enterMethod(type, id, "newPlayer()");
		Player temp = new Player(si, this);
		players.add(temp);
		temp.addSnake(gameField.GetRandomFreeField());
		si.exitMethod(type, id, "newPlayer()");
	}

	public void step() {
		si.enterMethod(type, id, "step()");
		for (java.util.Iterator i = players.iterator(); i.hasNext();) {
			((Player) (i.next())).step();
		}
		si.exitMethod(type, id, "step()");
	}
}
