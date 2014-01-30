package solitaire;

public class CrossTablier extends TablierAnglais {

	public CrossTablier(Dimension dimention) {
		super(dimention);

		float width = dimension.getWidth();
		float height = dimension.getHeight();

		double tierWidth = width / 3;
		double tierHeight = height / 3;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Coordonnee coord = new Coordonnee(x, y);
				Emplacement emplacement = new Emplacement();
				if (x == Math.floor(width / 2) && y == Math.floor(height / 2)) {
					emplacement.setEtat(Etat.SANS_BILLE);
				} else if ((Math.floor(tierHeight) <= y && y < Math
						.ceil(tierHeight * 2))
						|| (Math.floor(tierWidth) <= x && x < Math
								.ceil(tierWidth * 2))) {
					emplacement.setEtat(Etat.AVEC_BILLE);
				}
				add(coord, emplacement);
			}
		}
	}
}
