/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snakefarm;

import java.io.*;

/**
 *
 * @author Gergo
 */
public class UserInterface {
	private int indentation=0;
	BufferedReader reader;
	public UserInterface() {
		InputStreamReader stream=new InputStreamReader(System.in);
		reader=new BufferedReader(stream);
	}
	public void enterMethod(String objectType, int objectId, String method) {
		printTabs();
		System.out.println("->" + objectType + "[" + objectId + "]." + method);
		indentation++;
		/*Throwable t=new Throwable();
		StackTraceElement[] a=t.getStackTrace();
		System.out.println(a[a.length-2].getClassName() + ", " + a[a.length-2].getMethodName());*/
	}
	public void exitMethod(String objectType, int objectId, String method) {
		indentation--;
		printTabs();
		System.out.println("<-" + objectType + "[" + objectId + "]." + method);
	}
	private void printTabs() {
		for (int i=0; i<indentation; i++) {
			System.out.print(" ");
		}
	}
	public int askForInt(String question) {
		int i=0;
		boolean success=false;
		while (!success) {
			try {
				System.out.print(question + "[int]");
				String s=reader.readLine();
				i=Integer.parseInt(s);
				success=true;
			} catch (NumberFormatException e) {}
			catch (IOException e) {}
		}
		return i;
	}
	public boolean askForBool(String question) {
		boolean b=false;
		boolean success=false;
		while (!success) {
			try {
				System.out.print(question + "[y/n]");
				String s=reader.readLine();
				if (s.equals("y")) {
					b=true;
					success=true;
				} else if (s.equals("n")) {
					b=false;
					success=true;
				}
			} catch (IOException e) {}
		}
		return b;
	}
}
