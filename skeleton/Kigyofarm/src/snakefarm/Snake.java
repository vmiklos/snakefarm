package snakefarm;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class Snake {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Snake";
	
	private static final int sawCounterMax=20;
	
	private Player player;
	private Direction direction;
	private List<SnakeUnit> units = new LinkedList<SnakeUnit>();
	private int sawCounter=0;
	private boolean isAlive=true;
	
	public Snake(UserInterface ui, Player player, Field field) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Snake(Player, Field)");
		this.player=player;
		direction=new Direction(ui, 0);
		units.add(new SnakeUnit(ui, this, field));
		ui.exitMethod(type, id, "Snake(Player, Field)");
	}
	
	public void addSnakeUnit(SnakeUnit tail) {
		ui.enterMethod(type, id, "addSnakeUnit(SnakeUnit)");
		units.add(tail);
		ui.exitMethod(type, id, "addSnakeUnit(SnakeUnit)");
	}
	
	public void removeSnakeUnit(SnakeUnit unit) {
		ui.enterMethod(type, id, "removeSnakeUnit(SnakeUnit)");
		units.remove(unit);
		ui.exitMethod(type, id, "removeSnakeUnit(SnakeUnit)");
	}
	
	public Player getPlayer() {
		ui.enterMethod(type, id, "getPlayer()");
		ui.exitMethod(type, id, "getPlayer()");
		return player;
	}

	public void setSawCounter() {
		ui.enterMethod(type, id, "setSawCounter()");
		sawCounter=sawCounterMax;
		ui.exitMethod(type, id, "setSawCounter()");
	}

	public void turnLeft() {
		ui.enterMethod(type, id, "turnLeft()");
		direction.turnLeft();
		ui.exitMethod(type, id, "turnLeft()");
	}

	public void turnRight() {
		ui.enterMethod(type, id, "turnRight()");
		direction.turnRight();
		ui.exitMethod(type, id, "turnRight()");
	}

	public boolean isSaw() {
		ui.enterMethod(type, id, "isSaw()");
		ui.exitMethod(type, id, "isSaw()");
		return (sawCounter>0);
	}

	public void step() {
		ui.enterMethod(type, id, "step()");
		if (isAlive) {
			if (sawCounter>0) sawCounter--;
			SnakeUnit head=units.get(0);
			Field next=head.getNextField(direction);
			head.step(next, false, false);
		}
		ui.exitMethod(type, id, "step()");
	}

	public void die() {
		ui.enterMethod(type, id, "die()");
		if (isAlive) {
			isAlive=false;
			for (Iterator i=units.iterator(); i.hasNext(); ) {
				((SnakeUnit)(i.next())).die();
			}
			player.removeSnake(this);
		}
		ui.exitMethod(type, id, "die()");
	}

	/*public Field getNext() {
		ui.enterMethod(type, id, "getNext()");
		ui.error("METHOD NOT IMPLEMENTED");
		ui.exitMethod(type, id, "getNext()");
		return null;
	}*/
}