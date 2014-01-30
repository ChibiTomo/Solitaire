package tests;

import solitaire.Emplacement;
import solitaire.Etat;
import exceptions.TestFailException;

/**
 * Effectue une s�rie de test sur la classe {@link Emplacement}
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
				"L'emplacement doit �tre d�sactiv�e par d�faut.");
		checkEquals(emplacement2.getEtat(), Etat.AVEC_BILLE,
				"Il faut pouvoir passer l'�tat d�s l'instanciation de l'emplacement.");

		emplacement1.setEtat(Etat.SANS_BILLE);
		checkEquals(emplacement1.getEtat(), Etat.SANS_BILLE,
				"Le setEtat() ne fonctionne pas.");

		checkEquals(new Emplacement(Etat.SANS_BILLE), new Emplacement(
				Etat.SANS_BILLE), "La m�thode equals() est mal impl�ment�e.");
	}
}
