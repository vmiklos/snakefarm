package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A negyedik palya is ket kigyot tartalmaz, osszesen harom mezovel. A
 * kozepso mezo ures, majd miutan az ido egyet lep, a kigyok utkoznek.
 * <p>
 * Ez a palya a "Kigyo utkozik kigyoval" funkcio tesztelesere szolgal,
 * az egyik kigyonak meg kell halnia.
 */
public class Level4 extends LevelBase {

	Game game;
	Field f1 = new Field();
	Field f2 = new Field();
	Field f3 = new Field();
	Direction dir = new Direction(0);
	int snakesCreated = 0;
	int directionsSet = 0;

	public void initialize() {
		game = new Game();
		game.newPlayer();
		game.newPlayer();
	}

	public void execute() {
		game.step();
	}

	public List<Field> createGameField(GameField gameField) {
		List<Field> fields = new LinkedList<Field>();
		f1.setNeighbour(dir, f2);
		f2.setNeighbour(dir, f3);
		fields.add(f1);
		fields.add(f2);
		fields.add(f3);
		return fields;
	}

	public List<SnakeUnit> createSnake(Snake snake, int id) {
		List<SnakeUnit> units = new LinkedList<SnakeUnit>();
		if (snakesCreated == 0) {
			snakesCreated++;
			units.add(new SnakeUnit(snake, f1));
		} else {
			units.add(new SnakeUnit(snake, f3));
		}
		return units;
	}

	public Direction setDirection(Snake snake) {
		if (directionsSet == 0) {
			directionsSet++;
			return dir;
		} else {
			return dir.reverse();
		}
	}
}
