package solitaire;

/**
 * C'est une liste de constante représentant l'état d'un {@link Emplacement}
 * 
 * @author Yannis
 * 
 */
public enum Etat {
	/**
	 * L'emplacement est totalement innaccessible en cours de partie.
	 */
	DISABLED,
	/**
	 * L'emplacement possède une bille.
	 */
	AVEC_BILLE,
	/**
	 * L'emplacement n'a pas de bille.
	 */
	SANS_BILLE

}
