package snakefarm;

import java.awt.*;
import java.util.LinkedList;

/**
 *
 * @author Gergo
 */
public class WinnersCanvas extends Canvas {

	GraphicMain graphicMain;

	public WinnersCanvas(GraphicMain graphicMain) {
		this.graphicMain = graphicMain;
	}

	@Override
	public void paint(Graphics g) {
		LinkedList<Player> winners = graphicMain.getWinners();
		if (winners.size() == 0) {
			return;
		} else if (winners.size() == 1) {
			g.setColor(Color.BLACK);
			g.drawString("The winner is:", 110, 110);
			g.setColor(((Player)winners.get(0)).getColor());
			g.fillRect(200, 110, 30, 30);
		}
	}
}
