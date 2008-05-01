package snakefarm.creators;

import snakefarm.*;

/**
 *
 * @author Gergo
 */
public class GameCreator {

	private int width;
	private int height;
	private int players;
	private GameField gameField;

	public GameCreator(int w, int h, int p) {
		width = w;
		height = h;
		players = p;
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
		return new PlayerCreator(gameField.GetRandomFreeField());
	}
}
