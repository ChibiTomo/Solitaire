package gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button extends JButton {
	private static final long serialVersionUID = 1L;

	public Button(String label) {
		super(label);
	}

	public void setLitsener(ActionListener listener) {
		addActionListener(listener);
	}
}
