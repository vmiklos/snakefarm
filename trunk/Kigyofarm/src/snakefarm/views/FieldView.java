package snakefarm.views;

import snakefarm.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * mezo megjelenito osztaly
 */
public class FieldView extends BaseView {
	/**
	 * a megjelenitendo modell elem
	 */
	Field field;

	/**
	 * inicializalo konstruktor
	 * @param field megjelenitendo modell elem
	 */
	public FieldView(Field field) {
		this.field = field;
	}

	/**
	 * az elem kirajzolasa
	 * feher hatter, es a rajta levo eloter, ha van
	 * @param g Grafikus konextus, amire rajzolni kell
	 */
	@Override
	public void paint(Graphics g) {
		// hatter kirajzolasa
		Point p = getPointOfField(field);
		g.setColor(Color.white);
		g.fillRect(p.x, p.y, getFieldSize(), getFieldSize());

		// eloter kirajzolasa (ha van)
		Viewable viewable = field.getViewable();
		if (viewable != null) {
			viewable.getBaseView().paint(g);
		}
	}

}
