package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A hatodik palya ket mezobol all, az egyiken egy fureszbogyo
 * talalhato, a masikon pedig egy kigyo. Ahogy az ido lep, a kigyo a
 * fureszbogyot tartalmazo mezore lep.
 * <p>
 * Ez a palya a "Kigyo utkozese fureszbogyoval" funkcio tesztelesere
 * szolgal, a fureszbogyonak meg kell semmisulnie, a kigyo hosszanak
 * pedig nem szabad megvaltoznia.
 */
public class Level6 extends LevelBase {

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
		f2.setObject(new SawBerry(f2));
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
