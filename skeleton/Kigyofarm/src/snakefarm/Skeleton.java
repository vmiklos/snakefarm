package snakefarm;

import levels.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Skeleton {

	private static int indentation = 0;
	private static BufferedReader reader;
	private static LevelBase currentLevel = null;
	private static boolean outputEnabled = false;

	/**
	 * Ez a skeleton main fuggvenye. Bekeri, hogy hanyas szamu
	 * palyat teszteljuk, betolti az adott palyat, majd lefuttatja.
	 * @param args Commandline argumentumok, nem hasznaljuk.
	 */
	public static void main(String args[]) {
		reader = new BufferedReader(new InputStreamReader(System.in));
		mainMenu();
		if (currentLevel != null) {
			outputEnabled = false;
			currentLevel.initialize();
			outputEnabled = true;
			currentLevel.execute();
		}
	}

	private static void mainMenu() {
		int i = askForInt("Which level to run (0 to exit)? ", 0, 10);
		switch (i) {
			case 1:
				currentLevel = new Level1();
				break;
			case 2:
				currentLevel = new Level2();
				break;
			case 3:
				currentLevel = new Level3();
				break;
			case 4:
				currentLevel = new Level4();
				break;
			case 5:
				currentLevel = new Level5();
				break;
			case 6:
				currentLevel = new Level6();
				break;
			case 7:
				currentLevel = new Level7();
				break;
			case 8:
				currentLevel = new Level8();
				break;
			case 9:
				currentLevel = new Level9();
				break;
			case 10:
				currentLevel = new Level10();
				break;
		}
	}

	public static LevelBase getCurrentLevel() {
		return currentLevel;
	}

	public static void enterMethod(String objectType, int objectId, String method) {
		if (outputEnabled) {
			printTabs();
			System.out.println("->" + objectType + "[" + objectId + "]." + method);
		}
		indentation++;
	}

	public static void exitMethod(String objectType, int objectId, String method) {
		indentation--;
		if (outputEnabled) {
			printTabs();
			System.out.println("<-" + objectType + "[" + objectId + "]." + method);
		}

	}

	public static void message(String s) {
		printTabs();
		System.out.println(s);
	}

	private static void printTabs() {
		for (int i = 0; i < indentation; i++) {
			System.out.print(" ");
		}
	}

	public static int askForInt(String question, int min, int max) {
		int i = 0;
		boolean success = false;
		while (!success) {
			try {
				printTabs();
				System.out.print(question + "(int between " + Integer.toString(min) + " and " + Integer.toString(max) + ") ");
				String s = reader.readLine();
				i = Integer.parseInt(s);
				if ((min <= i) && (i <= max)) {
					success = true;
				}
			} catch (NumberFormatException e) {
			} catch (IOException e) {
			}
		}
		return i;
	}

	public static boolean askForBool(String question) {
		boolean b = false;
		boolean success = false;
		while (!success) {
			try {
				printTabs();
				System.out.print("??" + question + "[y/n]");
				String s = reader.readLine();
				if (s.equals("y")) {
					b = true;
					success = true;
				} else if (s.equals("n")) {
					b = false;
					success = true;
				}
			} catch (IOException e) {
			}
		}
		return b;
	}

	public static int askForControl() {
		int control = 0;
		boolean success = false;
		while (!success) {
			try {
				printTabs();
				System.out.print("?? Control snake [l/f/r, which stands for Left/Forward/Right]: ");
				String s = reader.readLine();
				if (s.equals("l")) {
					control = 1;
					success = true;
				} else if (s.equals("f")) {
					control = 0;
					success = true;
				} else if (s.equals("r")) {
					control = -1;
					success = true;
				}
			} catch (IOException e) {
			}
		}
		return control;
	}
}
