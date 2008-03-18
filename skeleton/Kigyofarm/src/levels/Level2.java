package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A m�sodik p�lya szint�n k�t mez�b�l �ll: az egyiken egy mezei bogy�
 * van, a m�sikon pedig egy egy hossz�s�g� k�gy� feje. A k�gy� ir�nya a
 * m�sik mez� fel� mutat.
 * <p>
 * Ez a p�lya a "K�gy� �tk�z�se mezei bogy�val" funkci� tesztel�s�re
 * szolg�l, a mezei bogy�nak meg kell semmis�lnie, a k�gy� hossz�nak
 * pedig eggyel n�vekednie kell.
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
