package snakefarm;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 *
 * @author Gergo
 */
public class GameFieldController extends KeyAdapter {

	GraphicMain graphicMain;
	GameFieldCanvas gameFieldCanvas;
	HashMap<Integer, Integer> keycodeToPlayer;
	HashMap<Integer, Integer> keycodeToCommand;

	public GameFieldController(GraphicMain graphicMain) {
		this.graphicMain = graphicMain;
		gameFieldCanvas = new GameFieldCanvas(this);
		fillHashMaps();
	}

	public void paintGameField(Graphics g) {
		graphicMain.paintGameField(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Integer player = keycodeToPlayer.get(e.getKeyCode());
		Integer command = keycodeToCommand.get(e.getKeyCode());
		if (player != null && command != null) {
			graphicMain.playerCommand(player, command);
		}
	}

	public GameFieldCanvas getCanvas() {
		return gameFieldCanvas;
	}

	public void update() {
		gameFieldCanvas.repaint();
	}

	private void fillHashMaps() {
		keycodeToPlayer = new HashMap<Integer, Integer>();
		keycodeToPlayer.put(KeyEvent.VK_LEFT, 0);
		keycodeToPlayer.put(KeyEvent.VK_RIGHT, 0);
		keycodeToPlayer.put(KeyEvent.VK_UP, 0);
		keycodeToPlayer.put(KeyEvent.VK_DOWN, 0);
		keycodeToPlayer.put(KeyEvent.VK_A, 1);
		keycodeToPlayer.put(KeyEvent.VK_D, 1);
		keycodeToPlayer.put(KeyEvent.VK_W, 1);
		keycodeToPlayer.put(KeyEvent.VK_S, 1);

		keycodeToCommand = new HashMap<Integer, Integer>();
		keycodeToCommand.put(KeyEvent.VK_LEFT, 0);
		keycodeToCommand.put(KeyEvent.VK_RIGHT, 1);
		keycodeToCommand.put(KeyEvent.VK_UP, 2);
		keycodeToCommand.put(KeyEvent.VK_DOWN, 3);
		keycodeToCommand.put(KeyEvent.VK_A, 0);
		keycodeToCommand.put(KeyEvent.VK_D, 1);
		keycodeToCommand.put(KeyEvent.VK_W, 2);
		keycodeToCommand.put(KeyEvent.VK_S, 3);
	}
}
