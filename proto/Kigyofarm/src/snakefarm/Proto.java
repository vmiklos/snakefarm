package snakefarm;

import java.io.*;
import java.util.*;

/**
 * Ez a proto fo classa, innen indul a program. Soha nem
 * peldanyositjuk.
 */
public class Proto {
	private Game game;
	public static PrintStream out = null;
	private static boolean debug = false;
	private static int time = 0;

	private class Debug implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.Debug.parseCommand: too few parameters");
			if(Integer.parseInt(args[0]) == 1)
				debug = true;
			else
				debug = false;
		}
	}

	private class Out implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.Out.parseCommand: too few parameters");
			int target = Integer.parseInt(args[0]);
			if(target == 1)
				out = System.out;
			else
				throw new Exception("Proto.Out.parseCommand: != 1 not yet implemented");
		}
	}

	private class Time implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.Time.parseCommand: too few parameters");
			time = Integer.parseInt(args[0]);
		}
	}

	private class Load implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.Load.parseCommand: too few parameters");
			game.gameField.loadMap(args[0]);
		}
	}

	public Proto(String fileName) throws Exception {
		game = new Game();
		Parser parser = new Parser();
		parser.addCommand("debug", new Debug());
		parser.addCommand("out", new Out());
		parser.addCommand("time", new Time());
		parser.addCommand("load", new Load());
		parser.parse(fileName);
	}

	/**
	 * Ez a proto main fuggvenye.
	 * @param args Commandline argumentumok.
	 */
	public static void main(String args[]) throws Exception {
		new Proto(args[0]);
	}
}
