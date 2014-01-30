package tests;

import solitaire.Coordonnee;
import solitaire.Dimension;
import solitaire.Emplacement;
import solitaire.Etat;
import solitaire.Historique;
import solitaire.TablierAnglais;
import interfaces.IAction;
import actions.AddBilleAction;
import actions.PlayAction;
import actions.RemoveBilleAction;
import exceptions.TestFailException;

public class TestHistorique extends Test {

	@Override
	public void run() throws TestFailException {
		TablierAnglais tablier = new TablierAnglais(new Dimension(5, 5));
		Coordonnee coord1 = new Coordonnee(3, 3);
		Coordonnee coord2 = new Coordonnee(2, 3);
		Coordonnee coord3 = new Coordonnee(1, 3);
		tablier.add(coord1, new Emplacement(Etat.AVEC_BILLE));
		tablier.add(coord2, new Emplacement(Etat.AVEC_BILLE));
		tablier.add(coord3, new Emplacement(Etat.SANS_BILLE));

		IAction action1 = new PlayAction(tablier, coord1, coord2, coord3);
		IAction action2 = new AddBilleAction(tablier, coord2);
		IAction action3 = new RemoveBilleAction(tablier, coord3);
		IAction action4 = new AddBilleAction(tablier, coord3);

		Historique historique = new Historique();

		checkDiff(historique.getPrevious(), null,
				"Le getPrevious() retourne null s'il n'y a pas d'action précédente.");
		checkDiff(historique.getNext(), null,
				"Le getNext() retourne null s'il n'y a pas d'action suivante.");

		historique.add(action1);
		checkEquals(historique.getPrevious(), action1,
				"Le getPrevious() ne fonctionne pas.");
		historique.add(action2);
		historique.add(action3);

		historique.undo();
		checkEquals(historique.getPrevious(), action2,
				"Le undo() ne fonctionne pas.");
		checkEquals(historique.getNext(), action3,
				"Le getNext() ne fonctionne pas.");

		historique.redo();
		checkEquals(historique.getPrevious(), action3,
				"Le redo() ne fonctionne pas.");
		checkDiff(historique.getNext(), null, "Le redo() ne fonctionne pas.");

		historique.undo();
		historique.undo();
		historique.undo();
		historique.add(action4);
		checkDiff(historique.getPrevious(), action4,
				"Le add() ne fonctionne pas.");
		checkDiff(historique.getNext(), null,
				"Le add() doit supprimer toutes les actions suivantes.");
	}

}
