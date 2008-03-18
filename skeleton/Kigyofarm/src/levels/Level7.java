package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A hetedik p�lya is k�t mez�b�l �ll, az egyiken egy k�bogy� tal�lhat�,
 * a m�sikon pedig egy k�gy�. Ahogy az id� l�p, a k�gy� a k�bogy�t
 * tartalmaz� mez�re l�p.
 * <p>
 * Ez a p�lya a "K�gy� �tk�z�se k�bogy�val" funkci� tesztel�s�re
 * szolg�l, a k�bogy�nak meg kell semmis�lnie, a k�gy� hossz�nak pedig
 * nem szabad megv�ltoznia.
 */
public class Level7 extends LevelBase {

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
		f2.setObject(new StoneBerry(f2));
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
