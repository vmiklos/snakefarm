package snakefarm.creators;

import snakefarm.*;
import java.awt.Color;

/**
 *
 * @author Gergo
 */
public class PlayerCreator {

	private Field field;
	private Color color;

	public PlayerCreator(Field field, Color color) {
		this.field = field;
		this.color = color;
	}

	public int getNumberOfSnakes() {
		return 1;
	}

	public SnakeCreator getSnakeCreator(int index) {
		if (index != 0) {
			throw new IllegalArgumentException();
		}
		return new SnakeCreator(field);
	}
	
	public Color getColor() {
		return color;
	}
}
