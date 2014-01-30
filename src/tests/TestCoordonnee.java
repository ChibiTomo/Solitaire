package tests;

import solitaire.Coordonnee;
import exceptions.TestFailException;

/**
 * Effectue une série de test sur la classe {@link Coordonnee}
 * 
 * @author Yannis
 * 
 */
public class TestCoordonnee extends Test {

	@Override
	public void run() throws TestFailException {
		int x1 = 34;
		int y1 = 2;
		int x2 = 45;
		int y2 = 3;

		Coordonnee coordonee = new Coordonnee(x1, y1);
		Coordonnee erreurCoordonee1 = new Coordonnee(-1, 0);
		Coordonnee erreurCoordonee2 = new Coordonnee(0, -1);

		checkDiff(coordonee.getX(), x1, "Le X n'est pas bon.");
		checkDiff(coordonee.getY(), y1, "Le Y n'est pas bon.");

		coordonee.setX(x2);
		coordonee.setY(y2);

		checkDiff(coordonee.getX(), x2, "Le setX() n'est pas bon.");
		checkDiff(coordonee.getY(), y2, "Le setY() n'est pas bon.");

		checkInf(erreurCoordonee1.getX(), 0,
				"Le X est négatif: il doit être remis à 0.");
		checkInf(erreurCoordonee2.getY(), 0,
				"Le Y est négatif: il doit être remis à 0.");

		checkEquals(new Coordonnee(23, 34), new Coordonnee(23, 34),
				"La methode equals() est mal implémentée.");
	}
}
