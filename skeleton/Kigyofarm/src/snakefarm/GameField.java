package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class GameField {

	private static int lastid = 0;
	private int id = lastid;
	private SkeletonInterface si;
	private static final String type = "GameField";
	private Game game;
	private List<Field> fields = new LinkedList<Field>();

	public GameField(SkeletonInterface si, Game game) {
		this.si = si;
		lastid++;
		si.enterMethod(type, id, "GameField(Game)");
		this.game = game;
		Direction d = new Direction(si, 0);
		Field f1 = new Field(si, this);
		Field f2 = new Field(si, this);
		f1.setNeighbour(new Direction(si, 0), f2);
		f2.setNeighbour(new Direction(si, 0), f1);
		f2.setObject(new Wall(si));
		fields.add(f1);
		fields.add(f2);
		si.exitMethod(type, id, "GameField(Game)");
	}

	public Field GetRandomFreeField() {
		si.enterMethod(type, id, "GetRandomFreeField()");
		LinkedList<Field> freeFields = new LinkedList<Field>();
		int index;
		for (java.util.Iterator i = fields.listIterator(); i.hasNext();) {
			Field field = (Field) i.next();
			if (field.isEmpty()) {
				freeFields.add(field);
			}
		}
		index = (int) ((double) (freeFields.size()) * Math.random());
		si.exitMethod(type, id, "GetRandomFreeField()");
		if (freeFields.size() != 0) {
			return freeFields.get(index);
		} else {
			return null;
		}
	}
}
