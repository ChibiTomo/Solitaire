package tests;

import java.util.ArrayList;
import java.util.List;

import yt.YT;
import exceptions.TestFailException;

/**
 * C'est la classe qui s'occupe d'effectuer tout les tests nécessaires au bon
 * fonctionnement de l'application.
 * 
 * @author Yannis
 * 
 */
public class Moulinette extends Test {

	private List<Test> tests = new ArrayList<Test>();
	private String bigSeparator = "---------------------------------------";

	@Override
	public void run() throws TestFailException {
		YT.println(bigSeparator);
		YT.println("Demarrage des tests...");
		YT.println(bigSeparator);
		for (Test test : tests) {
			String testName = test.getClass().getSimpleName();
			YT.print("*****" + testName + " ");
			test.run();
			YT.println("Succès!");
		}
		YT.println(bigSeparator);
		YT.println("Tous les tests sont passés! Enjoy ;)");
		YT.println();
	}

	public void add(Test test) {
		tests.add(test);
	}
}
