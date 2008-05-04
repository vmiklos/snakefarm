package snakefarm.creators;

import snakefarm.*;
import java.util.LinkedList;

/**
 *
 * @author Gergo
 */
public class SnakeCreator {

	protected Field field = null;
	protected Snake snake = null;
	
	public SnakeCreator() {
	}

	public SnakeCreator(Field f) {
		field = f;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public LinkedList<SnakeUnit> getSnakeUnits() {
		LinkedList<SnakeUnit> list = new LinkedList<SnakeUnit>();
		list.add(new SnakeUnit(snake, field));
		return list;
	}

	public Direction getDirection() {
		return new Direction();
	}

	public int getSawCounterMax() {
		return 20;
	}

	public int getSawCounter() {
		return 0;
	}

	public int getControlSpeed() {
		return 1;
	}

	public int getControlPhase() {
		return 0;
	}

	public int getStoneSpeed() {
		return 1;
	}

	public int getStonePhase() {
		return 0;
	}
}
