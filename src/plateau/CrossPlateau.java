package plateau;

public class CrossPlateau extends Plateau {

	public CrossPlateau() throws Exception {
		super(9, 9);

		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				if (i == 4 && j == 4) {
					setField(i, j, Plateau.EMPTY_FIELD);
				} else if ((3 <= j && j < 6) || (3 <= i && i < 6)) {
					setField(i, j, Plateau.FIELD_WITH_BALL);
				}
			}
		}
	}

}
