package snakefarm;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Gergo
 */
public class StepTimer extends TimerTask {

	Timer timer;
	GraphicMain graphicMain;
	int stepDelay;

	public StepTimer(GraphicMain graphicMain, int initialDelay, int stepDelay) {
		this.graphicMain = graphicMain;
		this.stepDelay = stepDelay;
		timer = new Timer(true);
		timer.schedule(this, initialDelay, stepDelay);
	}

	public void stop() {
		timer.cancel();
	}

	public void run() {
		graphicMain.step();
	}
}
