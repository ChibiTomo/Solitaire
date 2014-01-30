package tests;

import yt.YT;
import exceptions.TestFailException;

/**
 * Effectue une série de test sur la classe... {@link Test}... Ben oui. Faut la
 * tester aussi...
 * 
 * @author Yannis
 * 
 */
public class TestTest extends Test {

	@Override
	public void run() throws TestFailException {
		runFailureWithoutMessage();
		runFailureWithMessage();
	}

	private void runFailureWithMessage() throws TestFailException {
		Test test = new Test() {
			@Override
			public void run() throws TestFailException {
				failure();
			}
		};
		try {
			test.run();
		} catch (TestFailException e) {
			if (e.getErrorMessage() != Test.DEFAULT_ERROR_MESSAGE) {
				throw new TestFailException(this,
						"Mauvais message par defaut.",
						YT.getErrorLocation(new Exception()));
			}
		}
	}

	private void runFailureWithoutMessage() throws TestFailException {
		final String errorMessage = "Test de message d'erreur.";
		Test test = new Test() {
			@Override
			public void run() throws TestFailException {
				failure(errorMessage);
			}
		};
		try {
			test.run();
		} catch (TestFailException e) {
			if (e.getErrorMessage() != errorMessage) {
				throw new TestFailException(this,
						"Mauvais message personalisé.",
						YT.getErrorLocation(new Exception()));
			}
		}
	}

}
