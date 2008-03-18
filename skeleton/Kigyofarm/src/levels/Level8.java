package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A nyolcadik p�lya is k�t mez�b�l �ll, az egyiken egy k�gy� tal�lhat�,
 * a m�sikon pedig semmi. Az id� l�p, a k�gy� pedig az �res mez�re l�p.
 * <p>
 * Ez a p�lya a "K�gy� szabadon l�p" funkci� tesztel�s�re szolg�l, a
 * k�gy� nem hal meg �s a hossza sem v�ltozik.
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
