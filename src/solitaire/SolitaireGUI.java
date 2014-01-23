package solitaire;

import gui.Window;
import interfaces.IPlateau;
import interfaces.ISolitaire;

public class SolitaireGUI implements ISolitaire {
	private IPlateau plateau;

	public void setPlateau(IPlateau plateau) {
		this.plateau = plateau;
	}

	public void start() {
		new Window(this).open();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}
}
