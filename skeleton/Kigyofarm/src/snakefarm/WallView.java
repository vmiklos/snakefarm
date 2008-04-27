package snakefarm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


/**
 *
 * @author cassus
 */
public class WallView extends BaseView{
	Wall wall;

	public WallView(Wall wall) {
		this.wall = wall;
	}

	@Override
	public void paint(Graphics g) {
		Point p = getPointFromCoordinate(wall);
		g.setColor(Color.darkGray);
		g.fillRect(p.x, p.y, getFieldSize(), getFieldSize());
	}

}
