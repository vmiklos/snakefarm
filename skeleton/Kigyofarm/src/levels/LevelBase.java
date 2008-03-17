package levels;

import java.util.List;
import snakefarm.*;

/**
 *
 * @author Gergo
 */
public abstract class LevelBase {

	public abstract List<Field> createGameField(GameField gameField);
	public abstract List<SnakeUnit> createSnake(Snake snake, int id);
	public abstract Direction setDirection(Snake snake);
	public abstract void execute();
	public abstract void initialize();
}
