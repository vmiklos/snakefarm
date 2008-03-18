package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A tizedik pálya hasonló a kilencedikhez, de mindkét kígyó eszik egy
 * fûrészbogyót.
 * <p>
 * Ez a pálya a "Fûrészbogyót evett kígyó ütközése fûrészbogyót evett
 * kígyó fejével" funkció tesztelésére szolgál, és az egyik az egyik
 * kígyónak meg kell halnia.
 */
public class Level10 extends LevelBase {

	Game game;
	Field f1 = new Field();
	Field f2 = new Field();
	Field f3 = new Field();
	Field f4 = new Field();
	Field f5 = new Field();
	Direction dir1 = new Direction(0);
	Direction dir2 = new Direction(0);
	int snakesCreated = 0;
	int directionsSet = 0;

	public void initialize() {
		game = new Game();
		game.newPlayer();
		game.newPlayer();
	}

	public void execute() {
		game.step();
		game.step();
	}

	public List<Field> createGameField(GameField gameField) {
		List<Field> fields = new LinkedList<Field>();
		f1.setNeighbour(dir1, f2);
		f2.setNeighbour(dir1, f3);
		f5.setNeighbour(dir2, f4);
		f4.setNeighbour(dir2, f3);
		fields.add(f1);
		fields.add(f2);
		fields.add(f3);
		fields.add(f4);
		fields.add(f5);
		f2.setObject(new SawBerry(f2));
		f4.setObject(new SawBerry(f4));
		return fields;
	}

	public List<SnakeUnit> createSnake(Snake snake, int id) {
		List<SnakeUnit> units = new LinkedList<SnakeUnit>();
		if (snakesCreated == 0) {
			snakesCreated++;
			units.add(new SnakeUnit(snake, f1));
		} else {
			units.add(new SnakeUnit(snake, f5));
		}
		return units;
	}

	public Direction setDirection(Snake snake) {
		if (directionsSet == 0) {
			directionsSet++;
			return dir1;
		} else {
			return dir2;
		}
	}
}
