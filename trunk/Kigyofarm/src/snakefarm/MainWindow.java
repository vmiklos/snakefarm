package snakefarm;

import java.awt.*;

/**
 *
 * @author Gergo
 */
public class MainWindow extends Frame {

	GraphicMain graphicMain;

	public MainWindow(GraphicMain graphicMain) {
		this.graphicMain = graphicMain;
		setLayout(new BorderLayout());
		setTitle("Snakefarm");
		addWindowListener(graphicMain);
	}

	@Override
	public void paint(Graphics g) {
		graphicMain.paintGameField(g);
	}
}
