package snakefarm.views;

import snakefarm.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Fal megjelenito osztaly
 */
public class WallView extends BaseView{
	/**
	 * a megjelenitendo modell elem
	 */
	Wall wall;

	/**
	 * inicializalo konstruktor
	 * @param wall megjelenitendo modell elem
	 */
	public WallView(Wall wall) {
		this.wall = wall;
	}

	/**
	 * az elem kirajzolasa
	 * a falak sotetszurke negyzetek
	 * @param g Grafikus konextus, amire rajzolni kell
	 */
	@Override
	public void paint(Graphics g) {
		Point p = getPointOfFieldElement(wall);
		g.setColor(Color.darkGray);
		g.fillRect(p.x, p.y, getFieldSize(), getFieldSize());
	}

}
