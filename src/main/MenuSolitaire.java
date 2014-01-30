package main;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuSolitaire extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MenuSolitaire() {
		JMenu partie = new JMenu("Partie");
		add(partie);
	}

}
