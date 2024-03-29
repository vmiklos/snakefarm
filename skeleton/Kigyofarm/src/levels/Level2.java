package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A masodik palya szinten ket mezobol all: az egyiken egy mezei bogyo
 * van, a masikon pedig egy egy hosszusagu kigyo feje. A kigyo iranya a
 * masik mezo fele mutat.
 * <p>
 * Ez a palya a "Kigyo utkozese mezei bogyoval" funkcio tesztelesere
 * szolgal, a mezei bogyonak meg kell semmisulnie, a kigyo hosszanak
 * pedig eggyel novekednie kell.
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
