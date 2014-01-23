package gui;

import interfaces.ISolitaire;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;

	private ISolitaire solitaire;

	public Window(ISolitaire solitaire) {
		super();
		this.solitaire = solitaire;
		init();
	}

	private void init() {
		setTitle("Chibitomo's Solitaire");
		setSize(500, 500);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		GridLayout mainGrid = new GridLayout(1, 2);
		getContentPane().setLayout(mainGrid);

		getContentPane().add(new StartButton(solitaire));
	}

	public void open() {
		setVisible(true);
	}

	public void close() {
		setVisible(false);
	}

}
