package snakefarm;

import levels.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Ez a skeleton fo classa, innen indul a program. Soha nem
 * peldanyositjuk.
 */
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

	/**
	 * A fomenu. Bekeri a felhasznalotol a betoltendo palya szamat,
	 * majd elmenti a kapott erteket.
	 */
	private static void mainMenu() {
		System.out.println("0 - Exit");
		System.out.println("1 - Kigyo utkozese fallal");
		System.out.println("2 - Kigyo utkozese mezei bogyoval");
		System.out.println("3 - Fureszbogyot evett kigyo utkozese kigyoval");
		System.out.println("4 - Kigyo utkozik kigyoval");
		System.out.println("5 - Kobogyot evett kigyo utkozese fureszbogyot evett kigyoval");
		System.out.println("6 - Kigyo utkozese fureszbogyoval");
		System.out.println("7 - Kigyo utkozese kobogyoval");
		System.out.println("8 - Kigyo szabadon lep");
		System.out.println("9 - Fureszbogyot evett kigyo utkozik normal kigyo fejevel");
		System.out.println("10 - Fureszbogyot evett kigyo utkozese fureszbogyot evett kigyo fejevel");
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

	/**
	 * Lekerdezi a jelenlegi palyat.
	 *
	 * @return a jelenleg kivalasztott palya
	 */
	public static LevelBase getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * Kiir egy ertesitest arrol, hogy beleptunk egy metodusba.
	 *
	 * @param objectType az objektum tipusa (classneve)
	 * @param objectId az objektum azonositoja (szama)
	 * @param method a metodus neve, beleertve a parameterlistat
	 */
	public static void enterMethod(String objectType, int objectId, String method) {
		if (outputEnabled) {
			printTabs();
			System.out.println("->" + objectType + "[" + objectId + "]." + method);
		}
		indentation++;
	}

	/**
	 * Kiir egy ertesitest arrol, hogy kileptunk egy metodusbol.
	 *
	 * @param objectType az objektum tipusa (classneve)
	 * @param objectId az objektum azonositoja (szama)
	 * @param method a metodus neve, beleertve a parameterlistat
	 */
	public static void exitMethod(String objectType, int objectId, String method) {
		indentation--;
		if (outputEnabled) {
			printTabs();
			System.out.println("<-" + objectType + "[" + objectId + "]." + method);
		}

	}

	/**
	 * Kiir egy uzenetet a jelenlegi indentalasi szinten, tehat
	 * tabokat teve a szoveg ele.
	 *
	 * @param s a kiirando szoveg.
	 */
	public static void message(String s) {
		printTabs();
		System.out.println(s);
	}

	/**
	 * Kiirja a jelenlegi indentalasi szint megjelenitesehez
	 * szukseges mennyisegu tabot.
	 */
	private static void printTabs() {
		for (int i = 0; i < indentation; i++) {
			System.out.print(" ");
		}
	}

	/**
	 * Beker egy egesz szamot.
	 *
	 * @param question a kerdes szovege
	 * @param min minimalis ertek
	 * @param max maximalias ertek
	 * @return a kapott szam
	 */
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

	/**
	 * Beker egy igaz/hamis erteket.
	 *
	 * @param question a kerdes szovege
	 * @return a kapott logikai ertek
	 */
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

	/**
	 * Beker egy iranyt. A bal/elore/jobb iranyokhoz az 1/0/-1
	 * szamokat rendeli, majd visszaadja.
	 *
	 * @return a kapott irany
	 */
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
