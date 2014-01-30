package exceptions;

import tests.Test;
import yt.YT;

public class TestFailException extends Exception {
	private static final long serialVersionUID = 1L;

	private Test test;

	private String errorMessage;

	private String location;

	public TestFailException(Test test, String errorMessage, String location) {
		this.test = test;
		this.errorMessage = errorMessage;
		this.location = location;
	}

	public void printReport() {
		YT.println("ERREUR");
		YT.println(location);
		YT.println(errorMessage);
		YT.println();
	}

	public String getTestName() {
		return test.getClass().getSimpleName();
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
