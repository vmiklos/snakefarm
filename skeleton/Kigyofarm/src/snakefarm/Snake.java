package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class Snake {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Snake";
	private Player unnamed_Player_;
	private Direction unnamed_Direction_;
	private List<SnakeUnit> stone = new LinkedList<SnakeUnit>();
	
	public Snake(UserInterface ui, Player player) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Snake(Player)");
		unnamed_Player_=player;
		stone.add(new SnakeUnit(ui, this));
		stone.add(new SnakeUnit(ui, this));
		ui.exitMethod(type, id, "Snake(Player)");
	}

	public Player getPlayer() {
		throw new UnsupportedOperationException();
	}

	public void setSawCounter() {
		throw new UnsupportedOperationException();
	}

	public void turnLeft() {
		throw new UnsupportedOperationException();
	}

	public void turnRight() {
		throw new UnsupportedOperationException();
	}

	public boolean isSaw() {
		throw new UnsupportedOperationException();
	}

	public void step() {
		ui.enterMethod(type, id, "step()");
		SnakeUnit head=stone.listIterator(0).next();
		Field next=head.getNextField(unnamed_Direction_);
		head.step(next, false, false);
		ui.exitMethod(type, id, "step()");
	}

	public void die() {
		throw new UnsupportedOperationException();
	}

	public Field getNext() {
		throw new UnsupportedOperationException();
	}
}