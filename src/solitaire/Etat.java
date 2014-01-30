package solitaire;

/**
 * C'est une liste de constante repr�sentant l'�tat d'un {@link Emplacement}
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
	 * L'emplacement poss�de une bille.
	 */
	AVEC_BILLE,
	/**
	 * L'emplacement n'a pas de bille.
	 */
	SANS_BILLE

}
