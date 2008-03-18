package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A második pálya szintén két mezõbõl áll: az egyiken egy mezei bogyó
 * van, a másikon pedig egy egy hosszúságú kígyó feje. A kígyó iránya a
 * másik mezõ felé mutat.
 * <p>
 * Ez a pálya a "Kígyó ütközése mezei bogyóval" funkció tesztelésére
 * szolgál, a mezei bogyónak meg kell semmisülnie, a kígyó hosszának
 * pedig eggyel növekednie kell.
 */
public class Level2 extends LevelBase {

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
		f2.setObject(new FieldBerry(f2));
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
