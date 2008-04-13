package snakefarm;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Ez a proto fo classa, innen indul a program. Soha nem
 * peldanyositjuk.
 */
public class Proto {
	public static PrintStream out = null;
	private static boolean debug = false;
	private static int time = 0;

	/**
	 * Ez a proto main fuggvenye.
	 * @param args Commandline argumentumok.
	 */
	public static void main(String args[]) {
		debug = true;
		out = System.out;
		time = 100;
		out.println(debug);
		out.println(out);
		out.println(time);
	}
}
