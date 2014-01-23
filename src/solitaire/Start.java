package solitaire;

import interfaces.IPlateau;
import interfaces.ISolitaire;
import plateau.CrossPlateau;

public class Start {
	public static void main(String[] args) {
		try {
			ISolitaire solitaire = new Solitaire();
			// ISolitaire solitaire = new SolitaireGUI();

			IPlateau plateau = new CrossPlateau();
			solitaire.setPlateau(plateau);
			solitaire.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
