package solitaire;

import java.util.ArrayList;
import java.util.HashMap;

import yt.YT;
import actions.MoveBilleAction;
import actions.PlayAction;
import actions.RemoveBilleAction;

/**
 * Cette classe représente le plateau de jeu.
 * 
 * @author Yannis
 * 
 */

public class TablierAnglais {

	protected Dimension dimension;
	private HashMap<Coordonnee, Emplacement> emplacements;
	private boolean cheatMode;
	private Historique historique = new Historique();

	/**
	 * Creation d'un tablier avec une {@link Dimension} donnée.
	 * 
	 * @param dimension
	 *            - la {@link Dimension} du tablier
	 */
	public TablierAnglais(Dimension dimension) {
		setDimension(dimension);
		emplacements = new HashMap<Coordonnee, Emplacement>();
	}

	/**
	 * Change la {@link Dimension} du tablier.
	 * 
	 * @param newDimension
	 *            - la nouvelle {@link Dimension} du tablier
	 */
	public void setDimension(Dimension newDimension) {
		dimension = newDimension;
	}

	/**
	 * Retourne la {@link Dimension} actuel du plateau
	 * 
	 * @return la {@link Dimension} actuel du plateau
	 */
	public Dimension getDimension() {
		return dimension;
	}

	/**
	 * Ajoute/remplace un {@link Emplacement} à une {@link Coordonnee} donnée.
	 * 
	 * @param coord
	 *            - la {@link Coordonnee} pour le nouvel {@link Emplacement}
	 * @param emplacement
	 *            - l'{@link Emplacement} à placer
	 * @return <b>true</b> si tout s'est bien passé<br/>
	 *         <b>false</b> dans le cas contraire
	 */
	public boolean add(Coordonnee coord, Emplacement emplacement) {
		if (!isInside(coord)) {
			return false;
		}
		emplacements.put(coord, emplacement);
		return true;
	}

	/**
	 * Change l'{@link Etat} d'un {@link Emplacement} à une {@link Coordonnee}
	 * donnée. <br/>
	 * Cette méthode est incapable de changer l'{@link Etat} d'un
	 * {@link Emplacement} désactivé.
	 * 
	 * @param coord
	 *            - la {@link Coordonnee} de l'{@link Emplacement} recherché
	 * @param newEtat
	 *            - le nouvel {@link Etat} de l'{@link Emplacement} recherché
	 * @return <b>true</b> si l'{@link Etat} à pu être changé<br/>
	 *         <b>false</b> dans le cas contraire
	 */
	public boolean set(Coordonnee coord, Etat newEtat) {
		Emplacement emplacement = getEmplacement(coord);
		if (emplacement == null || emplacement.getEtat() == Etat.DISABLED) {
			return false;
		}
		emplacement.setEtat(newEtat);
		return true;
	}

	/**
	 * Permet d'obtenir l'{@link Emplacement} à une {@link Coordonnee} donnée.<br/>
	 * Si aucun {@link Emplacement} n'existe, un nouveau est créé et placé à
	 * cette {@link Coordonnee}.
	 * 
	 * @param coord
	 *            - la {@link Coordonnee} de l'{@link Emplacement} recherché
	 * @return l'{@link Emplacement} demandé ou null s'il est hors du plateu
	 */
	public Emplacement getEmplacement(Coordonnee coord) {
		if (!isInside(coord)) {
			return null;
		}

		Emplacement emplacement = emplacements.get(coord);
		if (emplacement == null) {
			emplacement = new Emplacement();
			emplacements.put(coord, emplacement);
		}
		return emplacement;
	}

	/**
	 * Permet de supprimer l'{@link Emplacement} à une {@link Coordonnee}
	 * donnée.<br/>
	 * 
	 * @param coord
	 *            - la {@link Coordonnee} de l'{@link Emplacement} recherché
	 */
	public void remove(Coordonnee coord) {
		emplacements.remove(coord);
	}

	/**
	 * Détermine si la {@link Coordonnee} donnée se situe à l'intérieur du
	 * plateau.
	 * 
	 * @param coord
	 *            - la {@link Coordonnee} à tester
	 * @return<b>true</b> si la {@link Coordonnee} appartient au
	 *                    {@link TablierAnglais}<br/>
	 *                    <b>false</b> dans le cas contraire
	 */
	public boolean isInside(Coordonnee coord) {
		return dimension.isInside(coord);
	}

	/**
	 * Détermine si l'{@link Emplacement} situé à la {@link Coordonnee} donnée
	 * se possède une bille.
	 * 
	 * @param coord
	 *            - la {@link Coordonnee} à tester
	 * @return <b>true</b> si il y a une bille<br/>
	 *         <b>false</b> dans le cas contraire
	 */
	public boolean hasBille(Coordonnee coord) {
		return isInside(coord)
				&& getEmplacement(coord).getEtat() == Etat.AVEC_BILLE;
	}

	/**
	 * Détermine si l'{@link Emplacement} situé à la {@link Coordonnee} donnée
	 * est désactivé ou hors du plateau.
	 * 
	 * @param coord
	 *            - la {@link Coordonnee} à tester
	 * @return <b>true</b> si l'{@link Emplacement} est désactivé ou hors du
	 *         plateau<br/>
	 *         <b>false</b> dans le cas contraire
	 */
	public boolean isDesactivated(Coordonnee coord) {
		return !isInside(coord)
				|| getEmplacement(coord).getEtat() == Etat.DISABLED;
	}

	public void cheatOn() {
		cheatMode = true;
	}

	public void cheatOff() {
		cheatMode = false;
	}

	public boolean isCheatOn() {
		return cheatMode;
	}

	public boolean play(Coordonnee source, Coordonnee target) {
		if (!isValidPlay(source, target)) {
			return false;
		}
		Coordonnee middle = getMiddleCoord(source, target);
		historique.add(new PlayAction(this, source, middle, target));
		return true;
	}

	public boolean addBille(Coordonnee coord) {
		if (!isCheatOn() || hasBille(coord)) {
			return false;
		}
		historique.add(new RemoveBilleAction(this, coord));
		return true;
	}

	public boolean removeBille(Coordonnee coord) {
		if (!isCheatOn() || !hasBille(coord)) {
			return false;
		}
		historique.add(new RemoveBilleAction(this, coord));
		return true;
	}

	public boolean moveBille(Coordonnee source, Coordonnee target) {
		if (!isCheatOn() || !hasBille(source) || hasBille(target)) {
			return false;
		}
		historique.add(new MoveBilleAction(this, source, target));
		return true;
	}

	public void undo() {
		historique.undo();
	}

	public void redo() {
		historique.redo();
	}

	public boolean canUndo() {
		return historique.getPrevious() != null;
	}

	public boolean canRedo() {
		return historique.getNext() != null;
	}

	public boolean isValidPlay(Coordonnee source, Coordonnee target) {
		if (isDesactivated(source) || isDesactivated(target)) {
			return false;
		}

		if (isDistanceOk(source, target)) {
			return false;
		}

		Coordonnee middle = getMiddleCoord(source, target);

		if (!hasBille(source) || !hasBille(middle) || hasBille(target)) {
			return false;
		}
		return true;
	}

	private Coordonnee getMiddleCoord(Coordonnee coord1, Coordonnee coord2) {
		int x = (coord1.getX() + coord2.getX()) / 2;
		int y = (coord1.getY() + coord2.getY()) / 2;
		return new Coordonnee(x, y);
	}

	private boolean isDistanceOk(Coordonnee coord1, Coordonnee coord2) {
		int x = Math.abs(coord1.getX() - coord2.getX());
		int y = Math.abs(coord1.getY() - coord2.getY());
		return x != 2 && y != 2;
	}

	@Override
	public String toString() {
		String result = "";
		Dimension dim = getDimension();
		for (int y = dim.getHeight(); y >= 0; y--) {
			for (int x = 0; x <= dim.getWidth(); x++) {
				if (x == 0 && y == 0) {
					result += "   ";
				} else if (x == 0) {
					result += " " + (y - 1) + " ";
				} else if (y == 0) {
					result += " " + (x - 1) + " ";
				} else {
					Emplacement emp = getEmplacement(new Coordonnee(x - 1,
							y - 1));
					result += emp2str(emp);
				}
			}
			result += YT.EOL;
		}
		return result;
	}

	private String emp2str(Emplacement emp) {
		Etat etat = emp.getEtat();
		if (etat == Etat.AVEC_BILLE) {
			return "[O]";
		} else if (etat == Etat.SANS_BILLE) {
			return "[ ]";
		} else {
			return "   ";
		}
	}

	public ArrayList<Emplacement> getBilles() {
		ArrayList<Emplacement> result = new ArrayList<Emplacement>();
		for (Emplacement emp : emplacements.values()) {
			if (emp.getEtat() == Etat.AVEC_BILLE) {
				result.add(emp);
			}
		}
		return result;
	}

	public ArrayList<Coup> getPossibilities() {
		ArrayList<Coup> result = new ArrayList<Coup>();
		for (Coordonnee coord : emplacements.keySet()) {
			ArrayList<Coup> coups = coord.getCoups();
			for (Coup coup : coups) {
				Coordonnee source = coup.getSource();
				Coordonnee target = coup.getTarget();
				if (isValidPlay(source, target)) {
					result.add(coup);
				}
			}
		}
		return result;
	}
}
