package solitaire;

import java.util.ArrayList;

/**
 * Cette classe représente l'addresse d'un {@link Emplacement} à l'interrieur
 * d'un {@link TablierAnglais}.
 * 
 * @author Yannis
 * 
 */
public class Coordonnee {

	private int x;
	private int y;

	public Coordonnee(int x, int y) {
		setX(x);
		setY(y);
	}

	/**
	 * Permet de définir une nouvelle colonne. Si celle-ci est négative, elle
	 * est remise à 0.
	 * 
	 * @param newX
	 *            - la nouvelle colonne
	 */
	public void setX(int newX) {
		if (newX < 0) {
			newX = 0;
		}
		x = newX;
	}

	/**
	 * Retourne la colonne de l'{@link Emplacement}
	 * 
	 * @return la colonne de l'{@link Emplacement}
	 */
	public int getX() {
		return x;
	}

	/**
	 * Permet de définir une nouvelle ligne. Si celle-ci est négative, elle est
	 * remise à 0.
	 * 
	 * @param newY
	 *            - la nouvelle ligne
	 */
	public void setY(int newY) {
		if (newY < 0) {
			newY = 0;
		}
		y = newY;
	}

	/**
	 * Retourne la ligne de l'{@link Emplacement}
	 * 
	 * @return la ligne de l'{@link Emplacement}
	 */
	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{ " + getX() + ", " + getY()
				+ " }";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordonnee)) {
			return false;
		}

		Coordonnee o = (Coordonnee) obj;

		return o.getX() == getX() && o.getY() == getY();
	}

	@Override
	public int hashCode() {
		return new Integer(getX()).hashCode() ^ new Integer(getY()).hashCode();
	}

	public ArrayList<Coup> getCoups() {
		ArrayList<Coup> result = new ArrayList<Coup>();

		result.add(new Coup(this, new Coordonnee(getX(), getY() + 2)));
		result.add(new Coup(this, new Coordonnee(getX(), getY() - 2)));
		result.add(new Coup(this, new Coordonnee(getX() + 2, getY())));
		result.add(new Coup(this, new Coordonnee(getX() + 2, getY() + 2)));
		result.add(new Coup(this, new Coordonnee(getX() + 2, getY() - 2)));
		result.add(new Coup(this, new Coordonnee(getX() - 2, getY())));
		result.add(new Coup(this, new Coordonnee(getX() - 2, getY() + 2)));
		result.add(new Coup(this, new Coordonnee(getX() - 2, getY() - 2)));

		return result;
	}
}
