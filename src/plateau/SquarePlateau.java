package plateau;


public class SquarePlateau extends Plateau {

	public SquarePlateau() throws Exception {
		super(3, 3);
		for (int i = 0; i < plateau.length; i++) {
			for (int j = 0; j < plateau[i].length; j++) {
				setField(i, j, Plateau.FIELD_WITH_BALL);
			}
		}
		setField(0, 0, Plateau.EMPTY_FIELD);
	}

}
