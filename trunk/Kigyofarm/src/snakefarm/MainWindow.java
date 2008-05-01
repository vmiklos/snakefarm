package snakefarm;

import java.awt.*;

/**
 *
 * @author Gergo
 */
public class MainWindow extends Frame {

	GraphicMain gm;

	public MainWindow(GraphicMain g) {
		gm = g;
		setLayout(new GridLayout(1, 2));
		setTitle("Snakefarm");
		setSize(600, 600);
		addWindowListener(g);
	}

	@Override
	public void paint(Graphics g) {
		gm.getGameFieldView().paint(g);
	}
}
