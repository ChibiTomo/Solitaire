package solitaire;

public class Emplacement {

	private Etat etat;

	public Emplacement() {
		this.etat = Etat.DISABLED;
	}

	public Emplacement(Etat etat) {
		this.etat = etat;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat newEtat) {
		etat = newEtat;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "{ " + getEtat() + " }";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Emplacement)) {
			return false;
		}

		Emplacement o = (Emplacement) obj;

		return o.getEtat() == getEtat();
	}
}
