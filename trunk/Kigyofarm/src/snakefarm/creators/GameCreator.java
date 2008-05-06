package snakefarm.creators;

import snakefarm.*;
import java.awt.Color;

/**
 *
 * @author Gergo
 */
public class GameCreator {

	private int width;
	private int height;
	private int players;
	private int stepsLimit;
	private GameField gameField;
	private Color[] colors;

	public GameCreator(int w, int h, int p, int limit) {
		width = w;
		height = h;
		players = p;
		stepsLimit = limit;
		initialiseColors();
	}

	public GameFieldCreator getGameFieldCreator() {
		return new GameFieldCreator(width, height);
	}

	public int getNumberOfPlayers() {
		return players;
	}
	
	public void setGameField(GameField gameField) {
		this.gameField = gameField;
	}

	public PlayerCreator getPlayerCreator(int index) {
		if ((index < 0) || (index >= players)) {
			throw new IllegalArgumentException();
		}
		return new PlayerCreator(gameField.GetRandomFreeField(), colors[index]);
	}
	
	private void initialiseColors() {
		colors = new Color[4];
		colors[0]=Color.GREEN;
		colors[1]=Color.ORANGE;
		colors[2]=Color.CYAN;
		colors[3]=Color.YELLOW;
	}
	
	public int getStepsLimit() {
		return stepsLimit;
	}
}
