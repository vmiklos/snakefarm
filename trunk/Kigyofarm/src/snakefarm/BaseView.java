package snakefarm;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author cassus
 */
public abstract class BaseView{

	private static int fieldSize = 30;

	public static int getFieldSize() {
		return fieldSize;
	}

	public static void setFieldSize(int fieldSize) {
		BaseView.fieldSize = fieldSize;
	}
	
	abstract public void paint(Graphics g);
	
	protected Point getPointFromCoordinate(Collidable c) {
		Point p = c.getField().getCoordinate();
		p.x = p.x*fieldSize;
		p.y = p.y*fieldSize;
		return p;
	}
}
