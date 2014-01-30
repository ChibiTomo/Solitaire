package main;

import interfaces.ISolitaire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import solitaire.Coordonnee;
import solitaire.CrossTablier;
import solitaire.Dimension;
import solitaire.TablierAnglais;
import solitaire.TriangleTablier;
import yt.YT;

public class ConsoleSolitaire implements ISolitaire {

	private TablierAnglais tablier;
	private Dimension dim;
	private BufferedReader br;

	@Override
	public void start() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			setTablier();
			askAction();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setTablier() {
		YT.print("Veuillez saisir la largeur de votre plateau: ");
		int width = saisieInt();

		YT.print("Veuillez saisir la hauteur de votre plateau: ");
		int height = saisieInt();
		dim = new Dimension(width, height);

		setForme();
	}

	private void setForme() {
		YT.println("Quelle forme?");
		YT.print("1:Croix   ");
		YT.print("2:Triangle   ");
		YT.println();

		int forme = saisieInt();
		switch (forme) {
		case 1:
			tablier = new CrossTablier(dim);
			break;
		case 2:
			tablier = new TriangleTablier(dim);
			break;

		default:
			YT.println("Je n'ai pas compris...");
			setForme();
			break;
		}
	}

	private void askAction() {
		print();
		if (hasWon()) {
			YT.println("Vous avez gagné! Bravo!");
			replay();
			return;
		}
		if (hasLoose()) {
			YT.println("C'est perdu... Dommage...");
			replay();
			return;
		}

		YT.println("Que faire?");

		YT.print("1:Jouer un coup   ");

		if (tablier.canUndo()) {
			YT.print("2:Annuler   ");
		}
		if (tablier.canRedo()) {
			YT.print("3:Refaire   ");
		}

		if (tablier.isCheatOn()) {
			YT.println("4:Désactiver la triche   ");
			YT.print("5:Ajouter une bille   ");
			YT.print("6:Supprimer une bille   ");
			YT.print("7:Déplacer une bille   ");
		} else {
			YT.print("4:Activer la triche   ");
		}
		YT.println("0:Quitter ");

		int saisie = saisieInt();
		switch (saisie) {
		case 0:
			YT.println("Merci d'avoir joué! Aurevoir.");
			return;
		case 1:
			play();
			break;
		case 2:
			tablier.undo();
			break;
		case 3:
			tablier.redo();
			break;
		case 4:
			if (tablier.isCheatOn()) {
				tablier.cheatOff();
			} else {
				tablier.cheatOn();
			}
			break;
		case 5:
			addBille();
			break;
		case 6:
			removeBille();
			break;
		case 7:
			moveBille();
			break;

		default:
			YT.println("Action inconnue...");
			break;
		}
		askAction();
	}

	private void replay() {
		YT.println("Voulez-vous rejoué? (1:oui/2:non)");
		int saisie = saisieInt();
		switch (saisie) {
		case 1:
			YT.println("C'est repartit!");
			start();
			return;
		case 2:
			YT.println("Merci d'avoir joué! Aurevoir.");
			break;
		default:
			YT.println("Heuu... On va dire que non... Aurevoir.");
			break;
		}
	}

	private boolean hasWon() {
		return tablier.getBilles().size() == 1;
	}

	private boolean hasLoose() {
		return tablier.getPossibilities().size() == 0;
	}

	private void addBille() {
		if (!tablier.isCheatOn()) {
			YT.println("Le cheat mode doit être activé.");
			return;
		}

		Coordonnee coord = saisieCoord();
		if (tablier.hasBille(coord)) {
			YT.println("Il y a déjà une bille ici.");
			return;
		}
		tablier.addBille(coord);
	}

	private void removeBille() {
		if (!tablier.isCheatOn()) {
			YT.println("Le cheat mode doit être activé.");
			return;
		}

		Coordonnee coord = saisieCoord();
		if (!tablier.hasBille(coord)) {
			YT.println("Il n'y a pas de bille ici.");
			return;
		}
		tablier.removeBille(coord);
	}

	private void moveBille() {
		if (!tablier.isCheatOn()) {
			YT.println("Le cheat mode doit être activé.");
			return;
		}

		Coordonnee source = saisieCoord();
		if (!tablier.hasBille(source)) {
			YT.println("Il n'y a pas de bille à la source.");
			return;
		}

		Coordonnee target = saisieCoord();
		if (tablier.hasBille(target)) {
			YT.println("Il y a une bille à l'arrivée.");
			return;
		}
		tablier.moveBille(source, target);
	}

	private void play() {
		YT.println("Choisissez l'emplacement de départ.");
		Coordonnee source = saisieCoord();
		YT.println("Choisissez l'emplacement d'arrivée.");
		Coordonnee target = saisieCoord();

		if (!tablier.isValidPlay(source, target)) {
			YT.println("Ce coup est interdit.");
			return;
		}

		tablier.play(source, target);
	}

	private Coordonnee saisieCoord() {
		YT.print("Choisissez une ligne: ");
		int y = saisieInt();
		YT.print("Choisissez une colonne: ");
		int x = saisieInt();
		return new Coordonnee(x, y);
	}

	private void print() {
		YT.print(tablier);
		YT.print("Billes restante : " + tablier.getBilles().size() + "   ");
		YT.print("Coup possibles : " + tablier.getPossibilities().size());
		YT.println();
	}

	private int saisieInt() {
		String str = saisie();
		if (!YT.isInt(str)) {
			YT.println("Vous devez saisir un nombre.");
			return saisieInt();
		}
		return Integer.parseInt(str);
	}

	synchronized private String saisie() {
		String result = "";
		try {
			result = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
