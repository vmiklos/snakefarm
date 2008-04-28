package snakefarm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Kobogyo megjelenito osztaly
 */
public class StoneBerryView extends BaseView{
	/**
	 * a megjelenitendo modell elem
	 */
	StoneBerry stoneBerry;

	/**
	 * inicializalo konstruktor
	 * @param stoneBerry megjelenitendo modell elem
	 */
	public StoneBerryView(StoneBerry stoneBerry) {
		this.stoneBerry = stoneBerry;
	}

	/**
	 * az elem kirajzolasa
	 * a kobogyo szurke kor
	 * @param g Grafikus konextus, amire rajzolni kell
	 */
	@Override
	public void paint(Graphics g) {
		Point p = getPointOfFieldElement(stoneBerry);
		g.setColor(Color.gray);
		g.fillOval(p.x, p.y, getFieldSize(), getFieldSize());
	}

}
