package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * Az elso palya 3 mezobol all: az egyiken egy fal talalhato, a masik
 * ketton pedig egy ket hosszusagu kigyo. A kigyo iranya a masik mezo
 * fele mutat.
 * <p>
 * Ez a palya a "Kigyo utkozese fallal" funkcio tesztelesere szolgal, a
 * kigyonak (es mindket elemenek) meg kell semmisulnie.
 */
public class Level1 extends LevelBase {

	Game game;
	Field f1 = new Field();
	Field f2 = new Field();
	Field f3 = new Field();
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
		f2.setNeighbour(dir, f3);
		f3.setObject(new Wall(f3));
		fields.add(f1);
		fields.add(f2);
		fields.add(f3);
		return fields;
	}

	public List<SnakeUnit> createSnake(Snake snake, int id) {
		List<SnakeUnit> units = new LinkedList<SnakeUnit>();
		SnakeUnit s0 = new SnakeUnit(snake, f2);
		SnakeUnit s1 = new SnakeUnit(snake, f1);
		s0.setNextUnit(s1);
		s1.setPrevUnit(s0);
		units.add(s0);
		units.add(s1);
		return units;
	}

	public Direction setDirection(Snake snake) {
		return dir;
	}
}
