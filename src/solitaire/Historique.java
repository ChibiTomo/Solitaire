package solitaire;

import interfaces.IAction;

import java.util.ArrayList;
import java.util.List;

public class Historique {
	private List<IAction> actions = new ArrayList<IAction>();
	private int currentIndex = 0;

	public void add(IAction action) {
		for (int i = actions.size() - 1; i >= currentIndex; i--) {
			actions.remove(i);
		}
		actions.add(currentIndex, action);
		currentIndex++;
		action.redo();
	}

	public IAction getPrevious() {
		if (currentIndex == 0) {
			return null;
		}
		return actions.get(currentIndex - 1);
	}

	public IAction getNext() {
		if (currentIndex >= actions.size()) {
			return null;
		}
		return actions.get(currentIndex);
	}

	public void undo() {
		IAction action = getPrevious();
		if (action == null) {
			return;
		}
		action.undo();
		currentIndex--;
	}

	public void redo() {
		IAction action = getNext();
		if (action == null) {
			return;
		}
		action.redo();
		currentIndex++;
	}
}
