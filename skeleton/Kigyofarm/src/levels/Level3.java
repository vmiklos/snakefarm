package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 *
 * @author Gergo
 */
public class Level3 extends LevelBase {

	Game game;
	Field[][] matrix = new Field[3][4];
	Direction dir1 = new Direction(0);
	Direction dir2 = new Direction(1);
	int snakesCreated = 0;
	int directionsSet = 0;

	public void initialize() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				matrix[i][j] = new Field();
			}
		}
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
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				matrix[i][j].setNeighbour(dir1, matrix[i + 1][j]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matrix[i][j].setNeighbour(dir2, matrix[i][j + 1]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				fields.add(matrix[i][j]);
			}
		}
		matrix[1][2].setObject(new SawBerry(matrix[1][2]));
		return fields;
	}

	public List<SnakeUnit> createSnake(Snake snake, int id) {
		List<SnakeUnit> units = new LinkedList<SnakeUnit>();
		if (snakesCreated == 0) {
			SnakeUnit snakeUnit1 = new SnakeUnit(snake, matrix[0][1]);
			SnakeUnit snakeUnit2 = new SnakeUnit(snake, matrix[0][0]);
			snakeUnit1.setNextUnit(snakeUnit2);
			snakeUnit2.setPrevUnit(snakeUnit1);
			units.add(snakeUnit1);
			units.add(snakeUnit2);
			snakesCreated = 1;
		} else {
			units.add(new SnakeUnit(snake, matrix[2][2]));
		}
		return units;
	}

	public Direction setDirection(Snake snake) {
		Direction dir;
		if (directionsSet == 0) {
			dir = dir2;
			directionsSet = 1;
		} else {
			dir = dir1.reverse();
		}
		return dir;
	}
}
