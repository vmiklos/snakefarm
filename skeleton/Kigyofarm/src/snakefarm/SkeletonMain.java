package snakefarm;

/**
 *
 * @author Gergo
 */
public class SkeletonMain {

	public static void main(String args[]) {
		SkeletonInterface si = new SkeletonInterface();
		Game game = new Game(si);
		game.newPlayer();
		game.step();
	}
}
