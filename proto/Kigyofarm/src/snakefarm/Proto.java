package snakefarm;

import java.io.*;
import java.util.*;

/**
 * Ez a proto fo classa, innen indul a program.
 */
public class Proto {
	private Game game;
	public static PrintStream out = null;
	public static boolean debug = false;
	private static int time = 0;
	// ez ide azert kell mert egy addsnake utani parancsnak tudnia
	// kell, hogy hanyas snake-hez kerulnek be uj unitok
	private static Snake snake = null;

	/**
	 * A debug parancs ertelmezoje.
	 */
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

	/**
	 * Az out parancs ertelmezoje.
	 */
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

	/**
	 * A time parancs ertelmezoje.
	 */
	private class Time implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.Time.parseCommand: too few parameters");
			time = Integer.parseInt(args[0]);
		}
	}

	/**
	 * A load parancs ertelmezoje.
	 */
	private class Load implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.Load.parseCommand: too few parameters");
			game.getGameField().loadMap(args[0]);
		}
	}

	/**
	 * Az addplayer parancs ertelmezoje.
	 */
	private class AddPlayer implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.AddPlayer.parseCommand: too few parameters");
			game.newPlayer(Integer.parseInt(args[0]));
		}
	}

	/**
	 * Az addsnake parancs ertelmezoje.
	 */
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

	/**
	 * Az addsnakeunit parancs ertelmezoje.
	 */
	private class AddSnakeUnit implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 2)
				throw new Exception("Proto.AddSnakeUnit.parseCommand: too few parameters");
			SnakeUnit su = new SnakeUnit(snake, game.getGameField().getFieldById(Integer.parseInt(args[0])));
			if(Integer.parseInt(args[1]) == 0)
				su.setStone(false);
			else
				su.setStone(true);
			snake.addSnakeUnit(su);
		}
	}

	/**
	 * Az endsnake parancs ertelmezoje.
	 */
	private class EndSnake implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
		}
	}

	/**
	 * A comment parancs ertelmezoje.
	 */
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

	/**
	 * A snapshot parancs ertelmezoje.
	 */
	private class Snapshot implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.Snapshot.parseCommand: too few parameters");
			game.getGameField().saveMap(args[0]);
			game.savePlayers();
		}
	}

	/**
	 * Az exit parancs ertelmezoje.
	 */
	private class Exit implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			out.println("Event Exit");
			System.exit(0);
		}
	}

	/**
	 * A step parancs ertelmezoje.
	 */
	private class Step implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			int num = 1;
			if(args.length == 1)
				num = Integer.parseInt(args[0]);
			for(int i=0;i<num;i++)
				game.step();
			time--;
			if(time==0)
				game.end();
		}
	}

	/**
	 * A showsnake parancs ertelmezoje.
	 */
	private class ShowSnake implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				return;
			int num = Integer.parseInt(args[0]);
			game.showSnake(num);
		}
	}

	/**
	 * A showplayer parancs ertelmezoje.
	 */
	private class ShowPlayer implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.ShowPlayer.parseCommand: != 1 not yet implemented");
			Player player = game.getPlayerById(Integer.parseInt(args[0]));
			player.show();
		}
	}

	/**
	 * A turnleft parancs ertelmezoje.
	 */
	private class TurnLeft implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.TurnLeft.parseCommand: too few parameters");
			game.turnLeft(Integer.parseInt(args[0]));
		}
	}

	/**
	 * A turnright parancs ertelmezoje.
	 */
	private class TurnRight implements CommandParser {
		public void parseCommand(String[] args) throws Exception {
			if(args.length != 1)
				throw new Exception("Proto.TurnRight.parseCommand: too few parameters");
			game.turnRight(Integer.parseInt(args[0]));
		}
	}

	/**
	 * A proto konstruktora.
	 *
	 * @param fileName a bemeneti file eleresi utja. null azt
	 * jelenti, hogy stdinrol olvasson a program.
	 */
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
		parser.addCommand("snapshot", new Snapshot());
		parser.addCommand("exit", new Exit());
		parser.addCommand("step", new Step());
		parser.addCommand("showsnake", new ShowSnake());
		parser.addCommand("turnleft", new TurnLeft());
		parser.addCommand("turnright", new TurnRight());
		parser.addCommand("showplayer", new ShowPlayer());
		parser.parse(fileName);
	}

	/**
	 * Ez a proto main fuggvenye.
	 * @param args Commandline argumentumok.
	 */
	public static void main(String args[]) throws Exception {
		String arg = null;
		if(args.length > 0)
			arg = args[0];
		new Proto(arg);
	}
}
