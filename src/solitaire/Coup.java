package solitaire;

public class Coup {

	private Coordonnee source;
	private Coordonnee target;

	public Coup(Coordonnee source, Coordonnee target) {
		this.source = source;
		this.target = target;
	}

	public Coordonnee getSource() {
		return source;
	}

	public Coordonnee getTarget() {
		return target;
	}

}
