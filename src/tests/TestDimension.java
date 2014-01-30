package tests;

import solitaire.Coordonnee;
import solitaire.Dimension;
import exceptions.TestFailException;

/**
 * Effectue une série de test sur la classe {@link Dimension}
 * 
 * @author Yannis
 * 
 */
public class TestDimension extends Test {
	@Override
	public void run() throws TestFailException {
		int width1 = 6;
		int height1 = 4;
		int width2 = 10;
		int height2 = 12;
		Coordonnee coord1 = new Coordonnee(width2 * 2, height2 / 2);
		Coordonnee coord2 = new Coordonnee(width2 / 2, height2 * 2);
		Coordonnee coord3 = new Coordonnee(width2 / 2, height2 / 2);
		Coordonnee coord4 = new Coordonnee(width2, height2);

		Dimension dimension = new Dimension(width1, height1);
		Dimension erreurDimension1 = new Dimension(-1, 0);
		Dimension erreurDimension2 = new Dimension(0, -1);

		checkDiff(dimension.getWidth(), width1, "La largeur n'est pas bonne.");
		checkDiff(dimension.getHeight(), height1, "La hauteur n'est pas bonne.");

		dimension.setWidth(width2);
		dimension.setHeight(height2);

		checkDiff(dimension.getWidth(), width2, "Le setWidth() n'est pas bon.");
		checkDiff(dimension.getHeight(), height2,
				"Le setHeight() n'est pas bon.");

		checkInf(erreurDimension1.getWidth(), 0,
				"La largeur est négative: elle doit être remise à 0.");
		checkInf(erreurDimension2.getHeight(), 0,
				"La hauteur est négative: elle doit être remise à 0.");

		checkDiff(dimension.isInside(coord1), false,
				"Le x est sensé être trop grand.");
		checkDiff(dimension.isInside(coord2), false,
				"Le y est sensé être trop grand.");
		checkDiff(dimension.isInside(coord3), true,
				"Cette coordonée est dans la dimention.");
		checkDiff(
				dimension.isInside(coord4),
				false,
				"x et y son respectivement égaux à la largeur et à la heuteur "
						+ "de la Dimention. La Coordonnee est donc hors de la Dimention.");

		checkEquals(new Dimension(23, 34), new Dimension(23, 34),
				"La methode equals() est mal implémentée.");
	}
}
