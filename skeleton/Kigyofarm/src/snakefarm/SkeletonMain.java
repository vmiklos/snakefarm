/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snakefarm;

/**
 *
 * @author Gergo
 */
public class SkeletonMain {
	public static void main(String args[]) {
		UserInterface ui=new UserInterface();
		Game game=new Game(ui);
		game.newPlayer();
		game.step();
	}
}
