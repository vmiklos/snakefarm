package snakefarm;

import java.awt.Graphics;
import java.util.Collection;

/**
 *
 * @author cassus
 */
public class GameFieldView extends java.awt.Component{
	private GameField gameField;

	@Override
	public void paint(Graphics g) {
		Collection<Viewable> fields = gameField.getViewables();
		for(Viewable field : fields) {
			BaseView view = field.getBaseView();
			view.paint(g);
		}
	}

}
