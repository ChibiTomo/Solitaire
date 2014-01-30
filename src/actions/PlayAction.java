package actions;

import solitaire.Coordonnee;
import solitaire.TablierAnglais;
import interfaces.IAction;

public class PlayAction implements IAction {

	private MoveBilleAction sourceAction;
	private RemoveBilleAction middleAction;

	public PlayAction(TablierAnglais tablier, Coordonnee source,
			Coordonnee middle, Coordonnee target) {
		sourceAction = new MoveBilleAction(tablier, source, target);
		middleAction = new RemoveBilleAction(tablier, middle);
	}

	@Override
	public void redo() {
		sourceAction.redo();
		middleAction.redo();
	}

	@Override
	public void undo() {
		sourceAction.undo();
		middleAction.undo();
	}

}
