package snakefarm;

import java.awt.Graphics;
import java.awt.Point;


/**
 *
 * @author cassus
 */
public class SnakeUnitView extends BaseView{
	SnakeUnit snakeUnit;

	public SnakeUnitView(SnakeUnit snakeUnit) {
		this.snakeUnit = snakeUnit;
	}

	@Override
	public void paint(Graphics g) {
		Point p = getPointFromCoordinate(snakeUnit);
		g.setColor(snakeUnit.getPlayer().getColor());
		g.fillRect(p.x, p.y, getFieldSize(), getFieldSize());
	}

}
