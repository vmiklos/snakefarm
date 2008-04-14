package snakefarm;

import java.util.*;
import java.io.*;


public class Parser {
	private HashMap commands;

	public Parser() {
		commands = new HashMap();
	}

	public void parse(String fileName) throws Exception {
		BufferedReader input;
		if(fileName != null)
			input = new BufferedReader(new FileReader(new File(fileName)));
		else
			input = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line = input.readLine()) != null) {
			if(line.startsWith("#"))
				continue;
			line = line.replaceAll("\\s+", " ");
			line = line.replaceAll("^ ", "");
			if(line.equals(""))
				continue;
			StringTokenizer tokens = new StringTokenizer(line);
			String cmd = tokens.nextToken();
			String[] args;
			int j;
			CommandParser cp;
			try {
				Integer.parseInt(cmd);
				cp = (CommandParser)commands.get("addsnakeunit");
				args = new String[tokens.countTokens()+1];
				args[0] = cmd;
				j = 1;
			} catch(NumberFormatException e)
			{
				cp = (CommandParser)commands.get(cmd);
				args = new String[tokens.countTokens()];
				j = 0;
			}
			if(cp == null)
				throw new Exception("Parser.parse: unkown command ("+line+")");
			if(tokens.countTokens() > 0) {
				while(tokens.hasMoreTokens())
					args[j++] = tokens.nextToken();
			}
			cp.parseCommand(args);
		}
	}

	public void addCommand(String commandString, CommandParser command) {
		commands.put(commandString, command);
	}
}
