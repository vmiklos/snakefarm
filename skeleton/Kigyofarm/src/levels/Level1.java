package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * Az elsõ pálya 3 mezõbõl áll: az egyiken egy fal található, a másik
 * kettõn pedig egy két hosszúságú kígyó. A kígyó iránya a másik mezõ
 * felé mutat.
 * <p>
 * Ez a pálya a "Kígyó ütközése fallal" funkció tesztelésére szolgál, a
 * kígyónak (és mindkét elemének) meg kell semmisülnie.
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
