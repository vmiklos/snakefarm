package snakefarm;

import java.awt.Canvas;
import java.awt.Graphics;

/**
 *
 * @author Gergo
 */
public class GameFieldCanvas extends Canvas {

	GameFieldController gameFieldController;

	public GameFieldCanvas(GameFieldController gameFieldController) {
		this.gameFieldController = gameFieldController;
		addKeyListener(gameFieldController);
		setSize(300, 300);
	}

	@Override
	public void paint(Graphics g) {
		gameFieldController.paintGameField(g);
	}
}
