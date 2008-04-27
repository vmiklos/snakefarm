package snakefarm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


/**
 *
 * @author cassus
 */
public class StoneBerryView extends BaseView{
	StoneBerry stoneBerry;

	public StoneBerryView(StoneBerry stoneBerry) {
		this.stoneBerry = stoneBerry;
	}

	@Override
	public void paint(Graphics g) {
		Point p = getPointFromCoordinate(stoneBerry);
		g.setColor(Color.gray);
		g.fillOval(p.x, p.y, getFieldSize(), getFieldSize());
	}

}
