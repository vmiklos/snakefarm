package snakefarm;

import java.awt.Graphics;
import java.awt.Point;

/**
 * a megjelenito (View) osztalyok alaposztaja
 */
public abstract class BaseView{

	/**
	 * a kepernyon megjeleno merete egy mezonek pixelben
	 */
	private static int fieldSize = 30;

	public static int getFieldSize() {
		return fieldSize;
	}

	public static void setFieldSize(int fieldSize) {
		BaseView.fieldSize = fieldSize;
	}
	
	/**
	 * a nezet elem kirajzolasa
	 * @param g Grafikus konextus, amire rajzolni kell
	 */
	abstract public void paint(Graphics g);
	
	/**
	 * Mezon levo objektum helyenek szamolas a kepernyon
	 * @param element a mezon levo objektum
	 * @return bal felso pontja a mezoElem helyenek a kepernyon
	 */
	protected Point getPointOfFieldElement(FieldElement element) {
		return getPointOfField(element.getField());
	}

	/**
	 * Mezo helyenek szamitasa a kepernyore
	 * @param f mezo, aminek a helyet a kepernyon meg akarjuk tudni
	 * @return bal felso pontja a mezo helyenek a kepernyon
	 */
	protected Point getPointOfField(Field f) {
		Point p = f.getCoordinate();
		p.x = p.x*fieldSize;
		p.y = p.y*fieldSize;
		return p;
	}

}
