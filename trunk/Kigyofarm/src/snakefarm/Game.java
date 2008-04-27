package snakefarm;

import java.util.*;

/**
 * Osszefogja a jatekteret es a jatekosokat, valamint inicializalja a
 * jatekot.
 * <p>
 * Megvalositja az utemezest is.
 */
public class Game {

	private static int lastid = 0;
	private int id = lastid;
	private static final String type = "Game";
	private GameField gameField;
	private List<Player> players = new LinkedList<Player>();

	/**
	 * A jatek konstruktora.
	 */
	public Game() {
		lastid++;
		gameField = new GameField(this);
	}

	/**
	 * Visszaadja a jatek jatekteret.
	 *
	 * @return a jatekter
	 */
	public GameField getGameField()
	{
		return gameField;
	}

	/**
	 * Letrehoz egy uj jatekost a jatekban.
	 */
	public void newPlayer(int id) {
		Player player = new Player(this, id);
		players.add(player);
	}

	/**
	 * Elmenti a jatekosokat.
	 */
	public void savePlayers()
	{
		for (Iterator i = players.listIterator(); i.hasNext();) {
			Player player = (Player) i.next();
			Proto.out.println("addplayer " + player.getId());
			player.saveSnakes();
		}
	}

	/**
	 * Megnezi, hogy vege van-e a jateknak.
	 */
	private boolean checkEnd()
	{
		int countAlive = 0;
		Player lastAlive = null;
		for (Iterator i = players.iterator(); i.hasNext();) {
			Player player = (Player) i.next();
			if(player.hasSnake())
			{
				countAlive++;
				lastAlive = player;
			}
		}
		if(countAlive==1)
		{
			lastAlive.win();
			return true;
		}
		else
			return false;
	}

	/**
	 * Leptet egyet minden jatekoson.
	 */
	public void step() {
		for (java.util.Iterator i = players.iterator(); i.hasNext();) {
			((Player) (i.next())).step();
			checkEnd();
		}
	}

	/**
	 * Veget vet a jateknak.
	 */
	public void end()
	{
		Collections.sort((List)players);
		int max = players.get(0).getMaxLength();
		int minStone = players.get(0).getMinStoneLength();
		for (Iterator i = players.iterator(); i.hasNext();) {
			Player player = (Player) i.next();
			if(player.compareTo(players.get(0))==0)
				player.win();
		}
	}

	/**
	 * Megkeres egy jatekost az azonositoja alapjan.
	 *
	 * @param id azonosito
	 * @return a jatekos
	 */
	public Player getPlayerById(int id) {
		if (players != null)
		{
			for (Iterator i = players.listIterator(); i.hasNext();) {
				Player player = (Player) i.next();
				if (player.getId() == id) {
					return player;
				}
			}
		}
		return null;
	}

	/**
	 * Megmutat egy kigyot
	 *
	 * @param id a kigyo azonositoja
	 */
	public void showSnake(int id)
	{
		if (players != null)
		{
			for (Iterator i = players.listIterator(); i.hasNext();) {
				Player player = (Player) i.next();
				player.showSnake(id);
			}
		}
	}

	/**
	 * Balra forgat egy kigyot
	 *
	 * @param snakeId a kigyo azonositoja
	 */
	public void turnLeft(int snakeId) {
		if (players != null)
		{
			for (Iterator i = players.listIterator(); i.hasNext();) {
				Player player = (Player) i.next();
				player.turnLeft(snakeId);
			}
		}
	}

	/**
	 * Jobbra forgat egy kigyot
	 * 
	 * @param snakeId a kigyo azonositoja
	 */
	public void turnRight(int snakeId) {
		if (players != null)
		{
			for (Iterator i = players.listIterator(); i.hasNext();) {
				Player player = (Player) i.next();
				player.turnRight(snakeId);
			}
		}
	}
}
