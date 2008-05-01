package snakefarm.creators;

import snakefarm.*;
import java.lang.IllegalArgumentException;

/**
 *
 * @author Gergo
 */
public class PlayerCreator {

	private Field field;

	public PlayerCreator(Field f) {
		field = f;
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
}
