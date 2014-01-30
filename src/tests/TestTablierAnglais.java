package tests;

import solitaire.Coordonnee;
import solitaire.Dimension;
import solitaire.Emplacement;
import solitaire.Etat;
import solitaire.TablierAnglais;
import exceptions.TestFailException;

/**
 * Effectue une série de test sur la classe {@link TablierAnglais}
 * 
 * @author Yannis
 * 
 */
public class TestTablierAnglais extends Test {
	@Override
	public void run() throws TestFailException {
		Dimension dimension1 = new Dimension(3, 3);
		Dimension dimension2 = new Dimension(9, 9);
		Coordonnee coord = new Coordonnee(4, 4);
		Coordonnee erreurCoord = new Coordonnee(43, 12);
		Emplacement emplacement = new Emplacement(Etat.AVEC_BILLE);

		TablierAnglais tablier = new TablierAnglais(dimension1);

		checkEquals(tablier.getDimension(), dimension1,
				"La dimention du tablier n'est pas bonne.");

		tablier.setDimension(dimension2);

		checkEquals(tablier.getDimension(), dimension2,
				"Le setDimention() ne fonctionne pas.");

		checkEquals(tablier.isInside(erreurCoord), false,
				"Le isInside() ne fonctionne pas.");
		checkEquals(tablier.isInside(coord), true,
				"Le isInside() ne fonctionne pas.");

		checkEquals(tablier.getEmplacement(coord), new Emplacement(),
				"Une case non définis est par défaut désactivé.");

		checkEquals(tablier.set(coord, Etat.AVEC_BILLE), false,
				"Si une case est désactivée, le set() doit retourner false.");
		checkEquals(tablier.set(erreurCoord, Etat.AVEC_BILLE), false,
				"Si la Coordonnee n'est pas dans le plateau, le set() doit retourner false.");

		checkEquals(tablier.hasBille(coord), false,
				"Le hasBille() ne fonctionne pas.");

		checkEquals(tablier.add(erreurCoord, emplacement), false,
				"Le add() doit retourner false si la Coordonnee se situe hors du plateau.");
		checkDiff(tablier.getEmplacement(erreurCoord), null,
				"Le get() doit retourner null si la Coordonnee se situe hors du plateau.");

		checkEquals(tablier.add(coord, emplacement), true,
				"Le add() doit retourner true tout s'est bien passé.");
		checkEquals(tablier.getEmplacement(coord), new Emplacement(Etat.AVEC_BILLE),
				"Le add() ne fonctionne pas.");

		checkEquals(tablier.getEmplacement(new Coordonnee(4, 4)), new Emplacement(
				Etat.AVEC_BILLE),
				"Le get() avec des objets aux valeurs identiques.");

		checkEquals(tablier.hasBille(coord), true,
				"Le hasBille() ne fonctionne pas.");
		checkEquals(tablier.isDesactivated(coord), false,
				"Le isDesactivated() ne fonctionne pas.");
		checkEquals(tablier.isDesactivated(erreurCoord), true,
				"Le isDesactivated() ne fonctionne pas.");

		tablier.remove(coord);
		checkEquals(tablier.getEmplacement(coord), new Emplacement(),
				"Le remove() ne fonctionne pas.");
		checkEquals(tablier.isDesactivated(coord), true,
				"Le isDesactivated() ne fonctionne pas.");

		tablier.cheatOn();
		checkEquals(tablier.isCheatOn(), true,
				"Le cheat mode doit être activé.");
		tablier.cheatOff();
		checkEquals(tablier.isCheatOn(), false,
				"Le cheat mode doit être désactivé.");
	}
}
