package snakefarm;

import java.awt.Graphics;
import java.util.Collection;

/**
 * Jatekter megjelenito osztaly
 */
public class GameFieldView extends BaseView{
	/**
	 * a megjelenitendo modell elem
	 */
	private GameField gameField;

	/**
	 * inicializalo konstruktor
	 * @param gameField megjelenitendo modell elem
	 */
	public GameFieldView(GameField gameField) {
		this.gameField = gameField;
	}

	/**
	 * az elem kirajzolasa
	 * @param g Grafikus konextus, amire rajzolni kell
	 */
	@Override
	public void paint(Graphics g) {
		Collection<Viewable> fields = gameField.getViewables();
		for(Viewable field : fields) {
			BaseView view = field.getBaseView();
			view.paint(g);
		}
	}

}
