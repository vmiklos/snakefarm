package snakefarm;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;
import snakefarm.creators.GameCreator;
import java.awt.BorderLayout;

/**
 *
 * @author Gergo
 */
public class GraphicMain extends WindowAdapter {

	private MainWindow mainWindow;
	private Game game;
	private GameFieldController gameFieldController;
	private int gameFieldWidth = 20;
	private int gameFieldHeight = 20;
	private int numberOfPlayers = 2;
	private int timeLimit = 10;
	private int timeElapsed = 0;

	public static void main(String[] args) {
		new GraphicMain();
	}

	public GraphicMain() {
		game = new Game(new GameCreator(gameFieldWidth, gameFieldHeight, numberOfPlayers));
		mainWindow = new MainWindow(this);
		mainWindow.setVisible(true);
		mainWindow.setSize(800, 800);
		gameFieldController = new GameFieldController(this);
		mainWindow.add(gameFieldController.getCanvas(), BorderLayout.CENTER);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		e.getWindow().dispose();
	}

	public void paintGameField(Graphics g) {
		game.getGameField().getBaseView().paint(g);
	}

	public void step() {
		game.step();
		timeElapsed++;
		if (timeElapsed == timeLimit) {
			/* FIXME - jatek vege */
			System.out.println("time up");
		}
		gameFieldController.update();
	}

	public void playerCommand(int player, int command) {
		switch (command) {
			case 0:
				game.turnLeft(player);
				break;
			case 1:
				game.turnRight(player);
				break;
			case 2:
				game.switchToNextSnake(player);
				break;
			case 3:
				game.switchToPrevSnake(player);
				break;
		}
	}
}
