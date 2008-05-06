package snakefarm;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;
import snakefarm.creators.GameCreator;
import java.awt.BorderLayout;
import java.util.LinkedList;

/**
 *
 * @author Gergo
 */
public class GraphicMain extends WindowAdapter {

	private MainWindow mainWindow;
	private Game game;
	private GameFieldController gameFieldController;
	private WinnersCanvas winnersCanvas;
	private StepTimer stepTimer;
	private int gameFieldWidth = 20;
	private int gameFieldHeight = 20;
	private int numberOfPlayers = 2;
	private int stepsLimit = 100;
	private int stepsElapsed = 0;
	private int stepDelay = 1000;
	private boolean isPlaying;
	private boolean isGameOver;

	public static void main(String[] args) {
		new GraphicMain();
	}

	public GraphicMain() {
		mainWindow = new MainWindow(this);
		mainWindow.setVisible(true);
		mainWindow.setSize(800, 800);
		gameFieldController = new GameFieldController(this);
		winnersCanvas = new WinnersCanvas(this);
		newGame();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		e.getWindow().dispose();
		stepTimer.stop();
	}

	public void paintGameField(Graphics g) {
		if (isPlaying) {
			game.getGameField().getBaseView().paint(g);
		}
	}

	public void newGame() {
		game = new Game(new GameCreator(gameFieldWidth, gameFieldHeight, numberOfPlayers));
		stepTimer = new StepTimer(this, stepDelay);
		isPlaying=true;
		mainWindow.add(gameFieldController.getCanvas(), BorderLayout.CENTER);
		//mainWindow.add(winnersCanvas, BorderLayout.CENTER);
	}

	public void step() {
		game.step();
		stepsElapsed++;
		gameFieldController.update();
		if ((stepsElapsed == stepsLimit) || game.checkEnd()) {
			stepTimer.stop();
			isPlaying=false;
			isGameOver=true;
			mainWindow.remove(gameFieldController.getCanvas());
			mainWindow.add(winnersCanvas, BorderLayout.CENTER);
			mainWindow.validate();
		}
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
	
	public LinkedList<Player> getWinners() {
		if (isGameOver) {
			return game.getWinners();
		} else {
			return new LinkedList<Player>();
		}
	}
}
