package tests;

import solitaire.Coordonnee;
import solitaire.Dimension;
import solitaire.Emplacement;
import solitaire.Etat;
import solitaire.TablierAnglais;
import interfaces.IAction;
import actions.AddBilleAction;
import actions.MoveBilleAction;
import actions.PlayAction;
import actions.RemoveBilleAction;
import exceptions.TestFailException;

/**
 * Ce test permet de tester le bon fonctionnement des différentes {@link Action}
 * 
 * @author Yannis
 * 
 */
public class TestAction extends Test {
	@Override
	public void run() throws TestFailException {
		TablierAnglais tablier = new TablierAnglais(new Dimension(5, 5));
		Coordonnee coord1 = new Coordonnee(3, 3);
		Coordonnee coord2 = new Coordonnee(2, 3);
		Coordonnee coord3 = new Coordonnee(1, 3);
		tablier.add(coord1, new Emplacement(Etat.AVEC_BILLE));
		tablier.add(coord2, new Emplacement(Etat.AVEC_BILLE));
		tablier.add(coord3, new Emplacement(Etat.SANS_BILLE));

		// Ajout de bille
		IAction addBille = new AddBilleAction(tablier, coord3);
		addBille.redo();
		checkEquals(tablier.hasBille(coord3), true,
				"Le AddBilleAction.redo() ne fonctionne pas.");
		addBille.undo();
		checkEquals(tablier.hasBille(coord3), false,
				"Le AddBilleAction.undo() ne fonctionne pas.");

		// Retrait de Bille
		IAction removeBille = new RemoveBilleAction(tablier, coord1);
		removeBille.redo();
		checkEquals(tablier.hasBille(coord1), false,
				"Le RemoveBilleAction.redo() ne fonctionne pas.");
		removeBille.undo();
		checkEquals(tablier.hasBille(coord1), true,
				"Le RemoveBilleAction.undo() ne fonctionne pas.");

		// Déplacement de Bille
		IAction moveBille = new MoveBilleAction(tablier, coord1, coord3);
		moveBille.redo();
		checkEquals(tablier.hasBille(coord1), false,
				"Le MoveBilleAction.redo() ne fonctionne pas: source a une bille.");
		checkEquals(tablier.hasBille(coord3), true,
				"Le MoveBilleAction.redo() ne fonctionne pas: target n'a pas de bille.");
		moveBille.undo();
		checkEquals(tablier.hasBille(coord1), true,
				"Le MoveBilleAction.undo() ne fonctionne pas: source n'a pas de bille.");
		checkEquals(tablier.hasBille(coord3), false,
				"Le MoveBilleAction.redo() ne fonctionne pas: target a une bille.");

		// Jouer un coup
		IAction play = new PlayAction(tablier, coord1, coord2, coord3);
		play.redo();
		checkEquals(tablier.hasBille(coord1), false,
				"Le PlayAction.redo() ne fonctionne pas: source a une bille.");
		checkEquals(tablier.hasBille(coord2), false,
				"Le PlayAction.redo() ne fonctionne pas: source a une bille.");
		checkEquals(tablier.hasBille(coord3), true,
				"Le PlayAction.redo() ne fonctionne pas: target n'a pas de bille.");
		play.undo();
		checkEquals(tablier.hasBille(coord1), true,
				"Le PlayAction.undo() ne fonctionne pas: source n'a pas bille.");
		checkEquals(tablier.hasBille(coord2), true,
				"Le PlayAction.undo() ne fonctionne pas: middle n'a pas bille.");
		checkEquals(tablier.hasBille(coord3), false,
				"Le PlayAction.undo() ne fonctionne pas: target a une de bille.");
	}
}
