package solitaire;

import interfaces.IAction;
import interfaces.IPlateau;
import plateau.Plateau;
import exceptions.OutOfPlateauException;

public class RemoveAction implements IAction {

	private IPlateau plateau;
	private int x;
	private int y;

	public RemoveAction(IPlateau plateau, int x, int y) {
		this.plateau = plateau;
		this.x = x;
		this.y = y;
	}

	@Override
	public void undo() {
		try {
			plateau.setField(x, y, Plateau.FIELD_WITH_BALL);
		} catch (OutOfPlateauException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void redo() {
		try {
			plateau.remove(x, y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
