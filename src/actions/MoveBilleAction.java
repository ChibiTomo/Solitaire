package actions;

import solitaire.Coordonnee;
import solitaire.TablierAnglais;
import interfaces.IAction;

public class MoveBilleAction implements IAction {

	private RemoveBilleAction sourceAction;
	private AddBilleAction targetAction;

	public MoveBilleAction(TablierAnglais tablier, Coordonnee coord1,
			Coordonnee coord2) {
		sourceAction = new RemoveBilleAction(tablier, coord1);
		targetAction = new AddBilleAction(tablier, coord2);
	}

	@Override
	public void redo() {
		sourceAction.redo();
		targetAction.redo();
	}

	@Override
	public void undo() {
		sourceAction.undo();
		targetAction.undo();
	}

}
