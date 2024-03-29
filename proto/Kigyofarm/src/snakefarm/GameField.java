package snakefarm;

import java.io.*;
import java.util.*;

/**
 * Jatekter osztaly, amely a mezoket tartalmazza. Jatek inditaskor
 * felepiti a jatekteret (letrehozza a mezoket es beallitja a
 * szomszedokat).
 */
public class GameField {

	private static int lastid = 0;
	private int id = lastid;
	private Game game;
	private List<Field> fields = new LinkedList<Field>();

	/**
	 * A jatekter konstruktora.
	 *
	 * @param game A jatek amelyben a jatekter letrejon.
	 */
	public GameField(Game game) {
		lastid++;
		this.game = game;
	}

	/**
	 * Visszaad veletlenszeruen egy mezot a jatekterbol.
	 * 
	 * @return a veletlenszeru mezo
	 */
	public Field GetRandomFreeField() {
		LinkedList<Field> freeFields = new LinkedList<Field>();
		int index;
		for (java.util.Iterator i = fields.listIterator(); i.hasNext();) {
			Field field = (Field) i.next();
			if (field.isEmpty()) {
				freeFields.add(field);
			}
		}
		index = (int) ((double) (freeFields.size()) * Math.random());
		if (freeFields.size() != 0) {
			return freeFields.get(index);
		} else {
			return null;
		}
	}

	/**
	 * Visszaad egy Field-et az azonositoja alapjan.
	 *
	 * @param id az azonosito
	 * @return a Field
	 */
	public Field getFieldById(int id) {
		if (fields != null)
		{
			for (Iterator i = fields.listIterator(); i.hasNext();) {
				Field field = (Field) i.next();
				if (field.getId() == id) {
					return field;
				}
			}
		}
		return null;
	}

	/**
	 * Elmenti a jatekmezot.
	 *
	 * @param fileName kimenet fileneve
	 */
	public void saveMap(String fileName) throws Exception {
		Proto.out.println("load " + fileName);
		File file = new File(fileName);
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		Iterator i = fields.listIterator();
		while (true)
		{
			Field field = (Field) i.next();
			boolean last;
			if(i.hasNext())
				last = false;
			else
				last = true;
			Field up = field.getNext(new Direction(1));
			int upid = 0;
			if(up != null)
				upid = up.getId();
			Field left = field.getNext(new Direction(2));
			int leftid = 0;
			if(left != null)
				leftid = left.getId();
			dos.writeBytes(field.getId() + ";" +
					field.getObjectString()+ ";" +
					upid+ ";" +
					leftid);
			dos.writeBytes("\r\n");
			if(last)
				break;
		}
	}

	/**
	 * Betolt egy jatekmezot
	 *
	 * @param fileName a jatekmezo eleresi utja
	 */
	public void loadMap(String fileName) throws Exception {
		Proto.out.println("Event MapLoad " + fileName);
		fields = new LinkedList<Field>();
		Direction up = new Direction(1);
		Direction left = new Direction(2);
		Vector lines = new Vector();
		BufferedReader input = new BufferedReader(new FileReader(fileName));
		String line;
		while((line = input.readLine()) != null) {
			if(line.equals(""))
				continue;
			String[] params = line.split(";");
			Field f = new Field(Integer.parseInt(params[0]));
			f.setNeighbour(up, getFieldById(Integer.parseInt(params[2])));
			f.setNeighbour(left, getFieldById(Integer.parseInt(params[3])));
			if(params[1].equals("W"))
				f.setObject(new Wall(f));
			else if(params[1].equals("F"))
				f.setObject(new FieldBerry(f));
			else if(params[1].equals("T"))
				f.setObject(new StoneBerry(f));
			else if(params[1].equals("A"))
				f.setObject(new SawBerry(f));
			else if(params[1].equals("0"));
			else
				throw new Exception("unknown collidable type: " + params[1]);
			fields.add(f);
		}
	}
}
