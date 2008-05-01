package snakefarm.creators;

import snakefarm.*;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author Gergo
 */
public class GameFieldCreator {

	private int width;
	private int height;

	public GameFieldCreator(int w, int h) {
		width = w;
		height = h;
	}

	public List<Field> getFields() {
		List<Field> fields = new LinkedList<Field>();
		Field[][] matrix = new Field[width][height];
		Direction left = new Direction(2);
		Direction up = new Direction(1);
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				matrix[x][y] = new Field(new Coordinate(x, y));
				fields.add(matrix[x][y]);
				if ((x == 0) || (x == width - 1) || (y == 0) || (y == height - 1)) {
					matrix[x][y].setObject(new Wall(matrix[x][y]));
				}
				if (x>0) {
					matrix[x][y].setNeighbour(left, matrix[x-1][y]);
				}
				if (y>0) {
					matrix[x][y].setNeighbour(up, matrix[x][y-1]);
				}
			/* TODO: ide meg kell bogyo elhelyezo kod ... */
			}
		}
		return fields;
	}
}
