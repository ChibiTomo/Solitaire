package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tests.Moulinette;
import tests.TestAction;
import tests.TestCoordonnee;
import tests.TestDimension;
import tests.TestEmplacement;
import tests.TestHistorique;
import tests.TestJeu;
import tests.TestTablierAnglais;
import tests.TestTest;
import yt.YT;
import exceptions.TestFailException;

/**
 * Cette classe est le point de départ du programme.
 * 
 * @author Yannis
 * 
 */
public class Start {

	/**
	 * C'est la methode main. Ici je lance les tests, puis, s'il sont tous
	 * passés, le solitaire.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			runTests();
			play();
		} catch (TestFailException e) {
			e.printReport();
		}
	}

	private static void play() {
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			YT.println("Quel solitaire voulez vous lancer?");
			YT.print("1: Console   ");
			YT.print("2: Graphique   ");
			YT.println("0: Quitter");

			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			String str;
			str = bufferRead.readLine();
			if (!YT.isInt(str)) {
				error();
				return;
			}

			switch (Integer.parseInt(str)) {
			case 0:
				YT.println("Aurevoir!");
				break;
			case 1:
				new ConsoleSolitaire().start();
				break;
			case 2:
				YT.println("Désolé, mais ce Solitaire est indisponible actuellement");
				play();
				// YT.println("Lancement du soli");
				// new GUISolitaire().start();
				break;
			default:
				error();
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferRead.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void error() {
		YT.println("Je n'ai pas compris...");
		play();
	}

	/**
	 * Cette methode permet l'execution des tests.
	 * 
	 * @throws TestFailException
	 */
	private static void runTests() throws TestFailException {
		Moulinette moulinette = new Moulinette();

		moulinette.add(new TestTest());
		moulinette.add(new TestCoordonnee());
		moulinette.add(new TestDimension());
		moulinette.add(new TestEmplacement());
		moulinette.add(new TestTablierAnglais());
		moulinette.add(new TestAction());
		moulinette.add(new TestHistorique());
		moulinette.add(new TestJeu());

		moulinette.run();
	}
}
