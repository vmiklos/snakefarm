package snakefarm;

public class Collidable {
	protected Field field;

	public void collideWith(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	// Ezt leirtam az uml diagramban is, de ide is leirom
	// Alapertelmezetten a collideWithSaw fv. ugyanaz mint a collideWith, csak a SnakeUnitnal kell mas
	// Ez a fv. hivodik meg akkor, amikor fureszfejes kigyo utkozik az objektumba
	// Tehat defaultbol csak meghivja a colldeWith() fv.-t ugyanazzal a parameterrel
	// Ezt sztem ide kene irni, tekintve, hogy kozos osztalyrol van szo es nem kozos interfeszrol
	public void collideWithSaw(SnakeUnit snakeUnit) {
		throw new UnsupportedOperationException();
	}

	public void setField(Field field) {
		throw new UnsupportedOperationException();
	}
}