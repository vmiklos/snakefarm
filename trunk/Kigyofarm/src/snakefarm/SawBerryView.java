package snakefarm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


/**
 *
 * @author cassus
 */
public class SawBerryView extends BaseView{
	SawBerry sawBerry;

	public SawBerryView(SawBerry sawBerry) {
		this.sawBerry = sawBerry;
	}

	@Override
	public void paint(Graphics g) {
		Point p = getPointFromCoordinate(sawBerry);
		g.setColor(Color.red);
		g.fillOval(p.x, p.y, getFieldSize(), getFieldSize());
	}

}
