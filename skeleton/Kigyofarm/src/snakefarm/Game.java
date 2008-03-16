package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class Game {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Game";
	
	private GameField gameField;
	private List<Player> players=new LinkedList<Player>();
	
	public Game(UserInterface ui) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Game()");
		gameField=new GameField(ui, this);
		ui.exitMethod(type, id, "Game()");
	}
	
	public void newPlayer() {
		ui.enterMethod(type, id, "newPlayer()");
		Player temp=new Player(ui, this);
		players.add(temp);
		temp.addSnake(gameField.GetRandomFreeField());
		ui.exitMethod(type, id, "newPlayer()");
	}

	public void step() {
		ui.enterMethod(type, id, "step()");
		for (java.util.Iterator i=players.iterator(); i.hasNext(); ) {
			((Player)(i.next())).step();
		}
		ui.exitMethod(type, id, "step()");
	}
}