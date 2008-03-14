package snakefarm;

import java.util.List;
import java.util.LinkedList;

public class GameField {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="GameField";
	private Game game;
	private List<Field> fields=new LinkedList<Field>();
	
	public GameField(UserInterface ui, Game game) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "GameField(Game)");
		this.game=game;
		fields.add(new Field(ui, this));
		fields.add(new Field(ui, this));
		fields.add(new Field(ui, this));
		ui.exitMethod(type, id, "GameField(Game)");
	}
	public Field GetRandomFreeField() {
		ui.enterMethod(type, id, "GetRandomFreeField()");
		LinkedList<Field> freeFields=new LinkedList<Field>();
		int index;
		for (java.util.Iterator i=fields.listIterator(); i.hasNext(); ) {
			Field field=(Field)i.next();
			if (field.isEmpty()) freeFields.add(field);
		}
		index=(int)((double)(freeFields.size())*Math.random());
		ui.exitMethod(type, id, "GetRandomFreeField()");
		return freeFields.get(index);
	}
}