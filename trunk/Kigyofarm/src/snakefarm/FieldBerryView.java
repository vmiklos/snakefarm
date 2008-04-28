package snakefarm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * FieldBerry megjelenito osztaly
 */
public class FieldBerryView extends BaseView {
	/**
	 * a megjelenitendo modell elem
	 */
	FieldBerry fieldBerry;

	/**
	 * inicializalo konstruktor
	 * @param fieldBerry megjelenitendo modell elem
	 */
	public FieldBerryView(FieldBerry fieldBerry) {
		this.fieldBerry = fieldBerry;
	}

	/**
	 * az elem kirajzolasa
	 * a mezei bogyo kek kor
	 * @param g Grafikus konextus, amire rajzolni kell
	 */
	@Override
	public void paint(Graphics g) {
		Point p = getPointOfFieldElement(fieldBerry);
		g.setColor(Color.blue);
		g.fillOval(p.x, p.y, getFieldSize(), getFieldSize());
	}

}
