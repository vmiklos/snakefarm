package snakefarm.views;

import snakefarm.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * fureszbogyo megjelenito osztaly
 */
public class SawBerryView extends BaseView{
	/**
	 * a megjelenitendo modell elem
	 */
	SawBerry sawBerry;

	/**
	 * inicializalo konstruktor
	 * @param sawBerry megjelenitendo modell elem
	 */
	public SawBerryView(SawBerry sawBerry) {
		this.sawBerry = sawBerry;
	}

	/**
	 * az elem kirajzolasa
	 * a fureszbogyo piros kor
	 * @param g Grafikus konextus, amire rajzolni kell
	 */
	@Override
	public void paint(Graphics g) {
		Point p = getPointOfFieldElement(sawBerry);
		g.setColor(Color.red);
		g.fillOval(p.x, p.y, getFieldSize(), getFieldSize());
	}

}
