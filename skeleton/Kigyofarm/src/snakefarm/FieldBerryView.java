package snakefarm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author cassus
 */
public class FieldBerryView extends BaseView {
	FieldBerry fieldBerry;

	public FieldBerryView(FieldBerry fieldBerry) {
		this.fieldBerry = fieldBerry;
	}

	@Override
	public void paint(Graphics g) {
		Point p = getPointFromCoordinate(fieldBerry);
		g.setColor(Color.blue);
		g.fillOval(p.x, p.y, getFieldSize(), getFieldSize());
	}

}
