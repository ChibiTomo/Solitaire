package exceptions;

public class OutOfPlateauException extends Exception {
	private static final long serialVersionUID = 1L;

	public OutOfPlateauException() {
		super("Given coordinates are not inside the plateau.");
	}
}
