package tests;

import solitaire.Emplacement;
import solitaire.Etat;
import exceptions.TestFailException;

/**
 * Effectue une série de test sur la classe {@link Emplacement}
 * 
 * @author Yannis
 * 
 */
public class TestEmplacement extends Test {

	@Override
	public void run() throws TestFailException {
		Emplacement emplacement1 = new Emplacement();
		Emplacement emplacement2 = new Emplacement(Etat.AVEC_BILLE);

		checkEquals(emplacement1.getEtat(), Etat.DISABLED,
				"L'emplacement doit être désactivée par défaut.");
		checkEquals(emplacement2.getEtat(), Etat.AVEC_BILLE,
				"Il faut pouvoir passer l'état dés l'instanciation de l'emplacement.");

		emplacement1.setEtat(Etat.SANS_BILLE);
		checkEquals(emplacement1.getEtat(), Etat.SANS_BILLE,
				"Le setEtat() ne fonctionne pas.");

		checkEquals(new Emplacement(Etat.SANS_BILLE), new Emplacement(
				Etat.SANS_BILLE), "La méthode equals() est mal implémentée.");
	}
}
