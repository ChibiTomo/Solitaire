package main;

import javax.swing.JFrame;

public class GUISolitaire extends JFrame {
	private static final long serialVersionUID = 1L;

	public GUISolitaire() {
		setTitle("Yannis Solitaire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(500, 400);
		setLocationRelativeTo(null);

		setAutoRequestFocus(true);
		setResizable(false);

		setJMenuBar(new MenuSolitaire());
	}

	public void start() {
		setAlwaysOnTop(true);
		setVisible(true);
		toFront();
		setAlwaysOnTop(false);
	}

}
