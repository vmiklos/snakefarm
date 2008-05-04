package snakefarm.creators;

import snakefarm.Snake;
import snakefarm.SnakeUnit;
import snakefarm.Direction;
import snakefarm.Field;
import java.util.LinkedList;

/**
 *
 * @author Gergo
 */
public class BittenOffSnakeCreator extends SnakeCreator {

	Snake oldSnake;
	SnakeUnit collidee;

	public BittenOffSnakeCreator(Snake oldSnake, SnakeUnit collidee) {
		this.oldSnake = oldSnake;
		this.collidee = collidee;
	}
	
	@Override
	public LinkedList<SnakeUnit> getSnakeUnits() {
		LinkedList<SnakeUnit> list = new LinkedList<SnakeUnit>();
		for (SnakeUnit current = collidee.getNextUnit(); current != null; current = current.getNextUnit()) {
			list.add(current);
			current.setSnake(snake);
		}
		return list;
	}
	
	@Override
	public Direction getDirection() {
		Field nextField = collidee.getField();
		Field currentField = collidee.getNextUnit().getField();
		Direction dir = currentField.getDirection(nextField);
		dir.turnLeft();
		return dir;
	}

	@Override
	public int getSawCounterMax() {
		return oldSnake.getSawCounterMax();
	}

	@Override
	public int getControlSpeed() {
		return oldSnake.getControlSpeed();
	}

	@Override
	public int getStoneSpeed() {
		return oldSnake.getStoneSpeed();
	}
}
