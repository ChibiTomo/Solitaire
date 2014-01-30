package solitaire;

/**
 * Cette classe représente la taille d'un {@link TablierAnglais}.
 * 
 * @author Yannis
 * 
 */
public class Dimension {

	private int height;
	private int width;

	public Dimension(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	/**
	 * Permet de définir une nouvelle largeur pour le {@link TablierAnglais}. Si
	 * celle-ci est négative, elle est remise à 0.
	 * 
	 * @param newWidth
	 *            - la nouvelle largeur
	 */
	public void setWidth(int newWidth) {
		if (newWidth < 0) {
			newWidth = 0;
		}
		width = newWidth;
	}

	/**
	 * Retourne la largeur du {@link TablierAnglais}
	 * 
	 * @return la largeur du {@link TablierAnglais}
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Permet de définir une nouvelle hauteur pour le {@link TablierAnglais}. Si
	 * celle-ci est négative, elle est remise à 0.
	 * 
	 * @param newHeight
	 *            - la nouvelle hauteur
	 */
	public void setHeight(int newHeight) {
		if (newHeight < 0) {
			newHeight = 0;
		}
		height = newHeight;
	}

	/**
	 * Retourne la hauteur du {@link TablierAnglais}
	 * 
	 * @return la hauteur du {@link TablierAnglais}
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * 
	 * @param coord
	 *            - la {@link Coordonnee} à tester
	 * @return <b>true</b> - si la {@link Coordonnee} se situe à l'intérieur du
	 *         {@link TablierAnglais} <br/>
	 *         <b>false</b> - si elle est hors de cette {@link Dimension}
	 */
	public boolean isInside(Coordonnee coord) {
		if (coord.getX() > getWidth() - 1) {
			return false;
		}
		if (coord.getY() > getHeight() - 1) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{ " + getWidth() + "w x "
				+ getHeight() + "h }";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Dimension)) {
			return false;
		}

		Dimension o = (Dimension) obj;

		return o.getWidth() == getWidth() && o.getHeight() == getHeight();
	}
}
