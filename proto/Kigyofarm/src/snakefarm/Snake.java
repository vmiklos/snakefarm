package snakefarm;

import java.util.*;

/**
 * Kigyo osztaly, amely osszefoglalja az egy kigyohoz tartozo kigyo
 * egysegeket. Tarolja es valtoztatni tudja a kigyo iranyat.
 */
public class Snake {

	private int id;
	public static final int sawCounterMax = 20;
	private Player player;
	private Direction direction = new Direction(0);
	private List<SnakeUnit> units;
	private int sawCounter = 0;
	private boolean isAlive = true;
	private int controlPhase = 0;
	private int controlSpeed = 0;
	private int stonePhase = 0;
	private int stoneSpeed = 0;

	/**
	 * A kigyo konstruktora.
	 *
	 * @param player a kigyot vezerlo jatekos
	 */
	public Snake(Player player, int id) {
		this.player = player;
		this.id = id;
		units = new LinkedList<SnakeUnit>();
	}

	public int getId()
	{
		return id;
	}

	public void save()
	{
		Proto.out.println("addsnake " + player.getId() + " " +
				id + " " +
				direction.hashCode() + " " +
				controlSpeed + " " +
				stoneSpeed + " " +
				sawCounter);
		for (Iterator i = units.listIterator(); i.hasNext();) {
			SnakeUnit su = (SnakeUnit) i.next();
			su.save();
		}
		Proto.out.println("endsnake");
	}

	/**
	 * Uj farok hozzaadasa a kigyohoz.
	 *
	 * @param tail az uj farok, amely a regi farok utan lesz fuzve
	 */
	public void addSnakeUnit(SnakeUnit tail) {
		if(units.size() > 0)
		{
			SnakeUnit su = (SnakeUnit) units.get(units.size()-1);
			su.setNextUnit(tail);
			tail.setPrevUnit(su);
		}
		units.add(tail);
		tail.setSnake(this);
		tail.setId(units.size());
		SnakeUnit i = tail.getNextUnit();
		while(i != null)
		{
			units.add(i);
			i.setSnake(this);
			i = i.getNextUnit();
		}
	}

	/**
	 * Kigyoelem torlese a kigyobol.
	 *
	 * @param unit melyik elem legyen torolve
	 */
	public void removeSnakeUnit(SnakeUnit unit) {
		units.remove(unit);
		if(unit.getPrevUnit() != null)
		{
			unit.getPrevUnit().setNextUnit(null);
			unit.setPrevUnit(null);
		}
		SnakeUnit i = unit.getNextUnit();
		while(i != null)
		{
			units.remove(i);
			i = i.getNextUnit();
		}
	}

	/**
	 * Megadja, hogy melyik jatekoshoz tartozik a kigyo.
	 *
	 * @return a jatekos
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Beallitja a kigyo furesz-szamlalojat, tehat hogy meg hany
	 * lepesik van furesz modban a kigyo.
	 */
	public void setSawCounter(int sawCounter) {
		this.sawCounter = sawCounter;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void setControlSpeed(int controlSpeed) {
		this.controlSpeed = controlSpeed;
	}

	public void setStoneSpeed(int stoneSpeed) {
		this.stoneSpeed = stoneSpeed;
	}

	/**
	 * Balra forditja a kigyot.
	 */
	public void turnLeft() {
		direction.turnLeft();
	}

	/**
	 * Jobbra forditja a kigyot.
	 */
	public void turnRight() {
		direction.turnRight();
	}

	/**
	 * Megmondja, hogy furesz modban van-e a kigyo.
	 *
	 * @return logikai ertek a kerdes megvalaszolasara
	 */
	public boolean isSaw() {
		return (sawCounter > 0);
	}

	/**
	 * Leptet egyet a kigyon.
	 */
	public void step() {
		if (isAlive) {
			boolean stepped = false;
			SnakeUnit head = units.get(0);
			controlPhase++;
			if(controlPhase == controlSpeed)
			{
				Proto.out.println("Event Step " + id);
				controlPhase = 0;
				stepped = true;
				Field next = head.getNextField(direction);
				head.step(next, false);
			}
			if (stepped && sawCounter > 0) {
				if(sawCounter==1 && isAlive)
					// most lep at normal modba
					Proto.out.println("StepEvent SnakeModeNormal");
				sawCounter--;
			}
			if(stepped)
				Proto.out.println("End Step");
			stonePhase++;
			if(isAlive && stonePhase == stoneSpeed)
			{
				stonePhase = 0;
				head.stoneStep(false);
			}
		}
	}

	/**
	 * Megoli a kigyot.
	 */
	public void die() {
		if (isAlive) {
			Proto.out.println("StepEvent SnakeDie "+id);
			isAlive = false;
			/*for (Iterator i = units.iterator(); i.hasNext();) {
			((SnakeUnit) (i.next())).die();
			}*/
			player.removeSnake(this);
		}
	}

	public void show()
	{
		Proto.out.println("Snake " + id);
		Proto.out.println("Prop " + sawCounter + " " + controlSpeed + " " + stoneSpeed);
		for (Iterator i = units.listIterator(); i.hasNext();) {
			SnakeUnit su = (SnakeUnit) i.next();
			su.show();
		}
		Proto.out.println("endsnake");
	}

	public int getLength()
	{
		return units.size();
	}

	public int getStoneLength()
	{
		int ret = 0;
		for (Iterator i = units.listIterator(); i.hasNext();) {
			SnakeUnit su = (SnakeUnit) i.next();
			if(su.hasStone())
				ret++;
		}
		return ret;
	}

	public int getControlSpeed()
	{
		return controlSpeed;
	}

	public int getStoneSpeed()
	{
		return stoneSpeed;
	}

	public int getSawCounter()
	{
		return sawCounter;
	}
}
