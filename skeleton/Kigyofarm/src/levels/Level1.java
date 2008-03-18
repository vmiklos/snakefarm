package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * Az els� p�lya 3 mez�b�l �ll: az egyiken egy fal tal�lhat�, a m�sik
 * kett�n pedig egy k�t hossz�s�g� k�gy�. A k�gy� ir�nya a m�sik mez�
 * fel� mutat.
 * <p>
 * Ez a p�lya a "K�gy� �tk�z�se fallal" funkci� tesztel�s�re szolg�l, a
 * k�gy�nak (�s mindk�t elem�nek) meg kell semmis�lnie.
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
		units.add(new SnakeUnit(snake, f2));
		units.add(new SnakeUnit(snake, f1));
		return units;
	}

	public Direction setDirection(Snake snake) {
		return dir;
	}
}
