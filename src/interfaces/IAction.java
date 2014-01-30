package interfaces;

import solitaire.TablierAnglais;

/**
 * Cette interface est � remplir pour toute action sur un {@link TablierAnglais}
 * 
 * @author Yannis
 * 
 */
public interface IAction {
	/**
	 * Cette m�thode execute l'{@link IAction}.
	 */
	public void redo();

	/**
	 * Cette m�thode annule l'{@link IAction}.
	 */
	public void undo();
}
