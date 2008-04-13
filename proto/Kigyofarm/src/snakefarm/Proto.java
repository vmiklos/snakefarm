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
	private Game game;
	public static PrintStream out = System.out;
	private static boolean debug = false;
	private static int time = 0;

	private class Debug implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Debug.parseCommand: too few parameters");
			if(Integer.parseInt(args[0]) == 1)
				debug = true;
			else
				debug = false;
		}
	}

	public Proto(String fileName) throws Exception {
		game = new Game();
		Parser parser = new Parser();
		parser.addCommand("debug", new Debug());
		//parser.addCommand("out", new Out());
		//parser.addCommand("time", new Time());
		parser.parse(fileName);

		//out = System.out;
		//time = 100;
		out.println(debug);
		//out.println(out);
		//out.println(time);
	}

	/**
	 * Ez a proto main fuggvenye.
	 * @param args Commandline argumentumok.
	 */
	public static void main(String args[]) throws Exception {
		new Proto(args[0]);
	}
}
