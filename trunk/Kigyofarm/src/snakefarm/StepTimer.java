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

	public StepTimer(GraphicMain graphicMain, int stepDelay) {
		this.graphicMain = graphicMain;
		this.stepDelay = stepDelay;
		timer = new Timer();
		timer.schedule(this, 3000, stepDelay);
	}

	public void stop() {
		timer.cancel();
	}

	public void run() {
		graphicMain.step();
	}
}
