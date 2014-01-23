package solitaire;

import interfaces.IAction;
import interfaces.IPlateau;

public class DisplaceAction implements IAction {

	private IPlateau plateau;
	private int x1;
	private int x2;
	private int y1;
	private int y2;

	public DisplaceAction(IPlateau plateau, int x1, int y1, int x2, int y2) {
		this.plateau = plateau;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	@Override
	public void undo() {
		try {
			plateau.setField(x1, y1, IPlateau.FIELD_WITH_BALL);
			plateau.setField(x2, y2, IPlateau.EMPTY_FIELD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void redo() {
		try {
			plateau.displace(x1, y1, x2, y2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
