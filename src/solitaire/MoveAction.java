package solitaire;

import interfaces.IAction;
import interfaces.IPlateau;
import exceptions.OutOfPlateauException;

public class MoveAction implements IAction {

	private IPlateau plateau;
	private int x;
	private int y;
	private int direction;

	public MoveAction(IPlateau plateau, int x, int y, int direction) {
		this.plateau = plateau;
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	@Override
	public void undo() {
		int[][] coords = plateau.getMoveTargets(x, y, direction);
		try {
			plateau.setField(x, y, IPlateau.FIELD_WITH_BALL);
			plateau.setField(coords[0], IPlateau.FIELD_WITH_BALL);
			plateau.setField(coords[1], IPlateau.EMPTY_FIELD);
		} catch (OutOfPlateauException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void redo() {
		try {
			plateau.move(x, y, direction);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
