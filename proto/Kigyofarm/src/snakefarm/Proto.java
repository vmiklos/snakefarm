package snakefarm;

import java.io.*;
import java.util.*;

/**
 * Ez a proto fo classa, innen indul a program.
 */
public class Proto {
	private Game game;
	public static PrintStream out = null;
	private static boolean debug = false;
	private static int time = 0;
	// ez ide azert kell mert egy addsnake utani parancsnak tudnia
	// kell, hogy hanyas snake-hez kerulnek be uj unitok
	private static Snake snake = null;

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

	private class AddPlayer implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.AddPlayer.parseCommand: too few parameters");
			game.newPlayer(Integer.parseInt(args[0]));
		}
	}

	private class AddSnake implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 6)
				throw new Exception("Proto.AddSnake.parseCommand: too few parameters");
			int playerid = Integer.parseInt(args[0]);
			int snakeid = Integer.parseInt(args[1]);
			Direction dir = new Direction(Integer.parseInt(args[2]));
			int snakespeed = Integer.parseInt(args[3]);
			int stonespeed = Integer.parseInt(args[4]);
			int sawmode = Integer.parseInt(args[5]);

			Player player = game.getPlayerById(playerid);
			player.addSnake(snakeid);
			snake = player.getSnakeById(snakeid);
			snake.setDirection(dir);
			snake.setControlSpeed(snakespeed);
			snake.setStoneSpeed(stonespeed);
			snake.setSawCounter(sawmode);
		}
	}

	private class AddSnakeUnit implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 2)
				throw new Exception("Proto.AddSnakeUnit.parseCommand: too few parameters");
			SnakeUnit su = new SnakeUnit(snake, game.gameField.getFieldById(Integer.parseInt(args[0])));
			if(Integer.parseInt(args[1]) == 0)
				su.setStone(false);
			else
				su.setStone(true);
			snake.addSnakeUnit(su);
		}
	}

	private class EndSnake implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
		}
	}

	private class Comment implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			// poor man's StringUtils.join()
			for(int i=0;i<args.length;i++)
			{
				if(i+1 == args.length)
					out.println(args[i]);
				else
					out.print(args[i]+ " ");
			}
		}
	}

	public Proto(String fileName) throws Exception {
		game = new Game();
		Parser parser = new Parser();
		parser.addCommand("debug", new Debug());
		parser.addCommand("out", new Out());
		parser.addCommand("time", new Time());
		parser.addCommand("load", new Load());
		parser.addCommand("addplayer", new AddPlayer());
		parser.addCommand("addsnake", new AddSnake());
		parser.addCommand("addsnakeunit", new AddSnakeUnit());
		parser.addCommand("endsnake", new EndSnake());
		parser.addCommand("comment", new Comment());
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
