package solitaire;

public class TriangleTablier extends TablierAnglais {

	public TriangleTablier(Dimension dimention) {
		super(dimention);

		float width = dimension.getWidth();
		float height = dimension.getHeight();

		for (int x = 0; x < width; x++) {
			// float max = (height / width) * x; // Coin inférieur droit
			float max = height * ((-x / height) + 1); // Coin inférieur gauche

			for (int y = 0; y < height; y++) {
				Coordonnee coord = new Coordonnee(x, y);
				Emplacement emplacement = new Emplacement();
				if (x == Math.floor(width / 2) && y == Math.floor(height / 2)) {
					emplacement.setEtat(Etat.SANS_BILLE);
				} else if (y < max) {
					emplacement.setEtat(Etat.AVEC_BILLE);
				}
				add(coord, emplacement);
			}
		}
	}
}
