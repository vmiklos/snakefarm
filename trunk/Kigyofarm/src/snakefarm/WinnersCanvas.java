package snakefarm;

import java.awt.*;
import java.util.*;

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
			g.setColor(Color.BLACK);
			g.drawString("Everybody died, no winner!", 100, 100);
		} else if (winners.size() == 1) {
			g.setColor(Color.BLACK);
			g.drawString("The winner is:", 100, 100);
			g.setColor(((Player)winners.get(0)).getColor());
			g.fillRect(200, 85, 30, 30);
		} else {
			g.setColor(Color.BLACK);
			g.drawString("The winners are:", 100, 100);
			int cnt=0;
			for (Iterator i=winners.iterator(); i.hasNext(); ) {
				g.setColor(((Player)i.next()).getColor());
				g.fillRect(200+cnt*40, 85, 30, 30);
				cnt++;
			}
		}
	}
}
