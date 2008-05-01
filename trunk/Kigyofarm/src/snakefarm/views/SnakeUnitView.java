package snakefarm.views;

import snakefarm.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Kigyoelem megjelenito osztaly
 */
public class SnakeUnitView extends BaseView{
	/**
	 * a megjelenitendo modell elem
	 */
	SnakeUnit snakeUnit;
	
	/**
	 * inicializalo konstruktor
	 * @param snakeUnit megjelenitendo modell elem
	 */
	public SnakeUnitView(SnakeUnit snakeUnit) {
		this.snakeUnit = snakeUnit;
	}

	/**
	 * az elem kirajzolasa
	 * 
	 * A kígyóelemek kitöltik az egész mezőt, színük attól függ, melyik
	 * játékos kígyójához tartoznak. Ha egy kígyóegység kő, akkor 
	 * a közepén fehér négyzet van, ha a kígyó fűrészmódban van,
	 * akkor a fejének fekete kerete van.
	 * 
	 * @param g Grafikus konextus, amire rajzolni kell
	 */
	@Override
	public void paint(Graphics g) {
		// alap kigyoelem kirajzolasa
		Point p = getPointOfFieldElement(snakeUnit);
		g.setColor(snakeUnit.getPlayer().getColor());
		g.fillRect(p.x, p.y, getFieldSize(), getFieldSize());
		
		//ha ko van benne
		if(snakeUnit.hasStone()) {
			//rajzolas parametereinek szamitasa
			int stoneSize = getStoneSize();
			int borderSize = getFieldSize() -  2 * stoneSize;

			Point p2 = new Point(p);
			p2.translate(borderSize, borderSize);

			g.setColor(Color.white);
			g.fillRect(p2.x, p2.y, stoneSize, stoneSize);
		}
		
		//ha furesz (es fej)
		if(snakeUnit.hasSaw()) {
			g.setColor(Color.black);
			g.drawRect(p.x, p.y, getFieldSize(), getFieldSize());
		}
	}

	private int getStoneSize() {
		return getFieldSize()/3;
	}

}
