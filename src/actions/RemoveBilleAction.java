package actions;

import solitaire.Coordonnee;
import solitaire.Emplacement;
import solitaire.Etat;
import solitaire.TablierAnglais;
import interfaces.IAction;

public class RemoveBilleAction implements IAction {

	private Emplacement emplacement;
	private Etat oldEtat;

	public RemoveBilleAction(TablierAnglais tablier, Coordonnee coordonnee) {
		emplacement = tablier.getEmplacement(coordonnee);
		if (!tablier.hasBille(coordonnee)) {
			emplacement = null;
		}
	}

	@Override
	public void redo() {
		if (emplacement != null) {
			oldEtat = emplacement.getEtat();
			emplacement.setEtat(Etat.SANS_BILLE);
		}
	}

	@Override
	public void undo() {
		if (emplacement != null) {
			emplacement.setEtat(oldEtat);
		}
	}

}
