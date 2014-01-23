package gui;

import interfaces.ISolitaire;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends Button implements ActionListener {
	private static final long serialVersionUID = 1L;

	public StartButton(ISolitaire solitaire) {
		super("Start Game");
		setLitsener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
