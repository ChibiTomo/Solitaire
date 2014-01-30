package actions;

import solitaire.Coordonnee;
import solitaire.Emplacement;
import solitaire.Etat;
import solitaire.TablierAnglais;
import interfaces.IAction;

/**
 * Cette {@link IAction} permet d'ajouter une bille sur le plateau aux
 * {@link Coordonnee} données.
 * 
 * @author Yannis
 * 
 */
public class AddBilleAction implements IAction {

	protected Emplacement emplacement;
	private Etat oldEtat;

	public AddBilleAction(TablierAnglais tablier, Coordonnee coordonnee) {
		emplacement = tablier.getEmplacement(coordonnee);
		if (tablier.hasBille(coordonnee)) {
			emplacement = null;
		}
	}

	@Override
	public void redo() {
		if (emplacement != null) {
			oldEtat = emplacement.getEtat();
			emplacement.setEtat(Etat.AVEC_BILLE);
		}
	}

	@Override
	public void undo() {
		if (emplacement != null) {
			emplacement.setEtat(oldEtat);
		}
	}

}
