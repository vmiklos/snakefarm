package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A nyolcadik pálya is két mezõbõl áll, az egyiken egy kígyó található,
 * a másikon pedig semmi. Az idõ lép, a kígyó pedig az üres mezõre lép.
 * <p>
 * Ez a pálya a "Kígyó szabadon lép" funkció tesztelésére szolgál, a
 * kígyó nem hal meg és a hossza sem változik.
 */
public class Level8 extends LevelBase {

	Game game;
	Field f1 = new Field();
	Field f2 = new Field();
	Direction dir = new Direction(0);

	public void initialize() {
		game = new Game();
		game.newPlayer();
	}

	public void execute() {
		game.step();
	}

	public List<Field> createGameField(GameField gameField) {
		List<Field> fields = new LinkedList<Field>();
		f1.setNeighbour(dir, f2);
		fields.add(f1);
		fields.add(f2);
		return fields;
	}

	public List<SnakeUnit> createSnake(Snake snake, int id) {
		List<SnakeUnit> units = new LinkedList<SnakeUnit>();
		units.add(new SnakeUnit(snake, f1));
		return units;
	}

	public Direction setDirection(Snake snake) {
		return dir;
	}
}
