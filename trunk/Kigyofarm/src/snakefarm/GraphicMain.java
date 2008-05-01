package snakefarm;

import snakefarm.views.BaseView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import snakefarm.creators.GameCreator;

/**
 *
 * @author Gergo
 */
public class GraphicMain extends WindowAdapter {

	MainWindow mw;
	Game game;

	public static void main(String[] args) {
		new GraphicMain();
	}

	public GraphicMain() {
		game = new Game(new GameCreator(5, 5, 3));
		mw = new MainWindow(this);
		mw.setVisible(true);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		e.getWindow().dispose();
	}

	public BaseView getGameFieldView() {
		return game.getGameField().getBaseView();
	}
}
