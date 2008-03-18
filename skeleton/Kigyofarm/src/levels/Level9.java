package levels;

import java.util.List;
import java.util.LinkedList;
import snakefarm.*;

/**
 * A kilencedik p�lya n�gy mez�b�l �ll. Az id� eltelt�vel az egyik k�gy�
 * eszik egy f�r�szbogy�t majd a m�sik k�gy� fej�nek �tk�zik.
 * <p>
 * Ez a p�lya a "F�r�szbogy�t evett k�gy� �tk�zik norm�l k�gy� fej�vel"
 * funkci� tesztel�s�re szolg�l, mivel a fej�vel �tk�z�tt ez�rt itt
 * kiv�telesen m�gis meg kell halni a m�sik k�gy�nak.
 */
public class Level9 extends LevelBase {

	Game game;
	Field f1 = new Field();
	Field f2 = new Field();
	Field f3 = new Field();
	Field f4 = new Field();
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
		f4.setNeighbour(dir2, f3);
		fields.add(f1);
		fields.add(f2);
		fields.add(f3);
		fields.add(f4);
		f2.setObject(new SawBerry(f2));
		return fields;
	}

	public List<SnakeUnit> createSnake(Snake snake, int id) {
		List<SnakeUnit> units = new LinkedList<SnakeUnit>();
		if (snakesCreated == 0) {
			snakesCreated++;
			units.add(new SnakeUnit(snake, f1));
		} else {
			units.add(new SnakeUnit(snake, f4));
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
