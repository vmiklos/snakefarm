package snakefarm;

/**
 * Interfesz a parancsok parsolasahoz. Megadja, hogy milyen fuggvenyt
 * kell definialni ha egy osztaly akar egy parancsot parsolni.
 */
interface CommandParser {
	public void parseCommand(String[] args) throws Exception;
}
