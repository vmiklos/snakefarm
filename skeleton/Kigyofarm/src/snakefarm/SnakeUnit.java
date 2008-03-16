package snakefarm;

public class SnakeUnit extends Collidable {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="SnakeUnit";
	
	private Snake snake;
	private SnakeUnit prevUnit=null;
	private SnakeUnit nextUnit=null;
	private boolean eatenFieldBerry=false;
	private boolean eatenStoneBerry=false;
	private boolean isAlive=true;
	private boolean isStone;
	
	public SnakeUnit(UserInterface ui, Snake snake, Field field) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "SnakeUnit(Snake, Field)");
		this.snake=snake;
		this.field=field;
		if (!field.setObject(this)) {
			//para van!!! elvileg nem kellene, hogy ilyen legyen, erre kell valami
		}
		ui.exitMethod(type, id, "SnakeUnit(Snake, Field)");
	}

	public void collideWith2(Wall wall) {
		ui.enterMethod(type, id, "collideWith2(Wall)");
		die();
		ui.exitMethod(type, id, "collideWith2(Wall)");
	}

	public void collideWith2(SawBerry sawBerry) {
		ui.enterMethod(type, id, "collideWith2(SawBerry)");
		snake.setSawCounter();
		ui.exitMethod(type, id, "collideWith2(SawBerry)");
	}

	public void collideWith2(FieldBerry fieldBerry) {
		ui.enterMethod(type, id, "collideWith2(FieldBerry)");
		eatenFieldBerry=true;
		ui.exitMethod(type, id, "collideWith2(FieldBerry)");
	}

	public void collideWith2(StoneBerry stoneBerry) {
		ui.enterMethod(type, id, "collideWith2(StoneBerry)");
		eatenStoneBerry=true;
		ui.exitMethod(type, id, "collideWith2(StoneBerry)");
	}

	public void collideWith2(SnakeUnit snakeUnit) {
		ui.enterMethod(type, id, "collideWith2(SnakeUnit)");
		if (!snake.isSaw()) die();
		ui.exitMethod(type, id, "collideWith2(SnakeUnit)");
	}

	public void collideWith2Stone(SnakeUnit snakeUnit) {
		ui.enterMethod(type, id, "collideWith2Stone(SnakeUnit)");
		die();
		ui.exitMethod(type, id, "collideWith2Stone(SnakeUnit)");
	}

	/*public SnakeUnit getNextUnit() {
		ui.enterMethod(type, id, "getNextUnit()");
		ui.exitMethod(type, id, "getNextUnit()");
		return nextUnit;
	}

	public SnakeUnit getPrevUnit() {
		ui.enterMethod(type, id, "getPrevUnit()");
		ui.exitMethod(type, id, "getPrevUnit()");
		return prevUnit;
	}*/

	public void setNextUnit(SnakeUnit snakeUnit) {
		ui.enterMethod(type, id, "setNextUnit(SnakeUnit)");
		nextUnit=snakeUnit;
		ui.exitMethod(type, id, "setNextUnit(SnakeUnit)");
	}

	public void setPrevUnit(SnakeUnit snakeUnit) {
		ui.enterMethod(type, id, "setPrevUnit(SnakeUnit)");
		prevUnit=snakeUnit;
		ui.exitMethod(type, id, "setPrevUnit(SnakeUnit)");
	}

	/*public Snake getSnake() {
		ui.enterMethod(type, id, "getSnake()");
		ui.exitMethod(type, id, "getSnake()");
		return snake;
	}

	public Field getField() {
		ui.enterMethod(type, id, "getField()");
		ui.exitMethod(type, id, "getField()");
		return field;
	}*/

	public boolean step(Field nextField, boolean isToGrow, boolean receivesStone) {
		ui.enterMethod(type, id, "step(Field, boolean, boolean)");
		boolean rejectStone=false, nextRejectsStone;
		Field prevField=field;
		
		/* uressegbe lepunk-e */
		if (nextField==null) die();
		
		/* utkozunk */
		if (isAlive) {
			field.stepOut(this);
			Collidable collidee=nextField.stepOn(this);
			if (collidee!=null) {
				if (snake.isSaw()) collidee.collideWithSaw(this);
				else collidee.collideWith(this);
			}
		}
		
		if (isAlive) {
			/* mostmar nincs akadaly, tehat lepunk */
			nextField.stepOn(this);
			field=nextField;
			
			/* ha nincs kovetkezo */
			if (nextUnit==null) {
				/* megnezzuk, kell-e novekedni */
				if (isToGrow || eatenFieldBerry) {
					SnakeUnit newTail=new SnakeUnit(ui, snake, prevField);
					newTail.setPrevUnit(this);
					setNextUnit(newTail);
					snake.addSnakeUnit(newTail);
					eatenFieldBerry=false;
				}
				/* megnezzuk, az elozonek kuld-e vissza kovet */
				rejectStone=isStone && (receivesStone || eatenStoneBerry);
				/* megnezzuk, ko lesz-e */
				isStone=isStone || receivesStone || eatenStoneBerry;
				eatenStoneBerry=false;
			}
			
			/* ha van kovetkezo */
			if (nextUnit!=null) {
				/* szolunk neki is, es a kovet is tovabbadjuk */
				nextRejectsStone=nextUnit.step(prevField, eatenFieldBerry || isToGrow, isStone);
				eatenFieldBerry=false;
				/* megnezzuk, az elozonek kuld-e vissza kovet */
				rejectStone=isStone && nextRejectsStone && (receivesStone || eatenStoneBerry);
				/* megnezzuk, ko lesz-e */
				isStone=receivesStone || eatenStoneBerry || (nextRejectsStone && isStone);
				eatenStoneBerry=false;
			}
		}
		
		ui.exitMethod(type, id, "step(Field, boolean, boolean)");
		return rejectStone;
	}

	public void setStone(boolean has) {
		ui.enterMethod(type, id, "setStone(boolean)");
		isStone=has;
		ui.exitMethod(type, id, "setStone(boolean)");
	}

	public void die() {
		ui.enterMethod(type, id, "die()");
		if (isAlive) {
			isAlive=false;
			snake.removeSnakeUnit(this);
			if (prevUnit==null) snake.die();
			else if (nextUnit!=null) nextUnit.die();
			field.setObject(null);
		}
		ui.exitMethod(type, id, "die()");
	}

	public Field getNextField(Direction dir) {
		ui.enterMethod(type, id, "getNextField(Direction)");
		Field next=field.getNext(dir);
		ui.exitMethod(type, id, "getNextField(Direction)");
		return next;
	}
}