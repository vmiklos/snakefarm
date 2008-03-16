package snakefarm;

public class Wall extends Collidable {
	private static int lastid=0; 
	private int id=lastid;
	private UserInterface ui;
	private static final String type="Wall";
	
	public Wall(UserInterface ui) {
		this.ui=ui;
		lastid++;
		ui.enterMethod(type, id, "Wall()");
		ui.exitMethod(type, id, "Wall()");
	}
	
	@Override
	public void collideWith(SnakeUnit snakeUnit) {
		ui.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		ui.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
	
	@Override
	public void collideWithSaw(SnakeUnit snakeUnit) {
		ui.enterMethod(type, id, "collideWith(SnakeUnit)");
		snakeUnit.collideWith2(this);
		ui.exitMethod(type, id, "collideWith(SnakeUnit)");
	}
}