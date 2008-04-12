package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A nyolcadik palya is ket mezobol all, az egyiken egy kigyo talalhato,
 * a masikon pedig semmi. Az ido lep, a kigyo pedig az ures mezore lep.
 * <p>
 * Ez a palya a "Kigyo szabadon lep" funkcio tesztelesere szolgal, a
 * kigyo nem hal meg es a hossza sem valtozik.
 */
public class Level8 extends LevelBase {

	Game game;
	Field[][] matrix = new Field[3][3];
	Direction dir = new Direction(0);
	Direction dir1 = new Direction(0);
	Direction dir2 = new Direction(1);

	public void initialize() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrix[i][j] = new Field();
			}
		}
		game = new Game();
		game.newPlayer();
	}

	public void execute() {
		for(int it=0; it<6; it++) {
			game.step();
		}
	}

	public List<Field> createGameField(GameField gameField) {
		List<Field> fields = new LinkedList<Field>();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				matrix[i][j].setNeighbour(dir1, matrix[i + 1][j]);
			}
		}
		for (int i = 0; i < 3; i++) {
			matrix[i][0].setNeighbour(dir2, matrix[i][2]);
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				matrix[i][j].setNeighbour(dir2.reverse(), matrix[i][j + 1]);
			}
		}
		for (int j = 0; j < 3; j++) {
			matrix[0][j].setNeighbour(dir1.reverse(), matrix[2][j]);
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				fields.add(matrix[i][j]);
			}
		}
		return fields;
	}

	public List<SnakeUnit> createSnake(Snake snake, int id) {
		List<SnakeUnit> units = new LinkedList<SnakeUnit>();
		units.add(new SnakeUnit(snake, matrix[1][1]));
		return units;
	}

	public Direction setDirection(Snake snake) {
		return dir;
	}
}
