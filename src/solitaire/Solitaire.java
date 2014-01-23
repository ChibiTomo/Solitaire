package solitaire;

import interfaces.IAction;
import interfaces.IPlateau;
import interfaces.ISolitaire;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import plateau.Plateau;
import yt.YT;

public class Solitaire implements ISolitaire {
	private IPlateau plateau;

	private boolean cheatModeOn = false;

	private List<IAction> actions;
	private int currentIndex = -1;

	public void setPlateau(IPlateau plateau) {
		this.plateau = plateau;
		actions = new ArrayList<IAction>();
		currentIndex = -1;
	}

	public void start() {
		print();
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(
				System.in));

		String s = "";
		try {
			while ((s = bufferRead.readLine()) != null) {
				String[] a = s.split(" ");
				switch (a[0]) {
				case "/move":
					move(a);
					break;
				case "/test":
					test(a);
					break;
				case "/displace":
					if (cheatModeOn) {
						displace(a);
					} else {
						YT.println("Cheat mode is off...");
					}
					break;
				case "/remove":
					if (cheatModeOn) {
						remove(a);
					} else {
						YT.println("Cheat mode is off...");
					}
					break;
				case "/cheat":
					cheatModeOn = !cheatModeOn;
					String on = (cheatModeOn) ? "on" : "off";
					YT.println("Set cheat mode " + on + ".");
					break;
				case "/undo":
					undo();
					break;
				case "/redo":
					redo();
					break;
				}
				print();
				if (plateau.getBallLeft() <= 1) {
					YT.println("You win!!");
					break;
				}
			}
		} catch (Exception e) {
			print();
			e.printStackTrace();
		}
	}

	private void displace(String[] a) throws Exception {
		if (a.length < 5) {
			YT.println("Not enough information to displace something...");
		} else if (!YT.isInt(a[1]) || !YT.isInt(a[2]) || !YT.isInt(a[3])
				|| !YT.isInt(a[4])) {
			YT.println("Uncorrect coordinates...");
		} else {
			addDisplaceAction(Integer.parseInt(a[1]), Integer.parseInt(a[2]),
					Integer.parseInt(a[3]), Integer.parseInt(a[4]));
		}
	}

	private void remove(String[] a) throws Exception {
		if (a.length < 3) {
			YT.println("Not enough information to displace something...");
		} else if (!YT.isInt(a[1]) || !YT.isInt(a[2])) {
			YT.println("Uncorrect coordinates...");
		} else {
			addRemoveAction(Integer.parseInt(a[1]), Integer.parseInt(a[2]));
		}
	}

	private void test(String[] a) throws Exception {
		if (a.length < 3) {
			YT.println("Not enough information to move something...");
		} else if (!YT.isInt(a[1]) || !YT.isInt(a[2])) {
			YT.println("Uncorrect coordinates...");
		} else {
			plateau.setField(Integer.parseInt(a[1]), Integer.parseInt(a[2]),
					Plateau.TEST_FIELD);
		}
	}

	private void move(String[] a) throws Exception {
		if (a.length < 4) {
			YT.println("Not enough information for moving something...");
		} else if (!YT.isInt(a[1]) || !YT.isInt(a[2]) || !YT.isInt(a[3])) {
			YT.println("Uncorrect coordinates...");
		} else {
			addMove(Integer.parseInt(a[1]), Integer.parseInt(a[2]),
					Integer.parseInt(a[3]));
		}
	}

	private void addMove(int x, int y, int direction) {
		addAction(new MoveAction(plateau, x, y, direction));
	}

	private void addDisplaceAction(int x1, int y1, int x2, int y2) {
		addAction(new DisplaceAction(plateau, x1, y1, x2, y2));
	}

	private void addRemoveAction(int x, int y) {
		addAction(new RemoveAction(plateau, x, y));
	}

	private void addAction(IAction action) {
		action.redo();
		deleteOldActions();
		currentIndex++;
		actions.add(action);
	}

	private void deleteOldActions() {
		if (currentIndex > -1) {
			while (currentIndex < actions.size() - 1) {
				actions.remove(currentIndex);
			}
		}
	}

	private void print() {
		YT.println("Ball left: " + plateau.getBallLeft());
		YT.print(toString());
	}

	@Override
	public String toString() {
		return plateau.toString();
	}

	@Override
	public void undo() {
		if (currentIndex < 0) {
			return;
		}
		actions.get(currentIndex).undo();
		currentIndex--;
	}

	@Override
	public void redo() {
		if (currentIndex >= actions.size() - 1) {
			return;
		}
		currentIndex++;
		actions.get(currentIndex).redo();
	}
}
