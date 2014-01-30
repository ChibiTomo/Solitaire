package interfaces;

import solitaire.TablierAnglais;

/**
 * Cette interface est à remplir pour toute action sur un {@link TablierAnglais}
 * 
 * @author Yannis
 * 
 */
public interface IAction {
	/**
	 * Cette méthode execute l'{@link IAction}.
	 */
	public void redo();

	/**
	 * Cette méthode annule l'{@link IAction}.
	 */
	public void undo();
}
