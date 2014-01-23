package plateau;

public class SimplePlateau extends Plateau {

	public SimplePlateau() throws Exception {
		super(3, 1);
		setField(0, 0, Plateau.EMPTY_FIELD);
		setField(1, 0, Plateau.FIELD_WITH_BALL);
		setField(2, 0, Plateau.FIELD_WITH_BALL);
	}

}
