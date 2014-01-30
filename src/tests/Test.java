package tests;

import java.util.Arrays;

import yt.YT;
import exceptions.TestFailException;

/**
 * Cette classe est celle de base pour tout mes tests.
 * 
 * @author Yannis
 * 
 */
public abstract class Test {
	/**
	 * Il s'agit du message d'erreur par défaut d'un test.
	 */
	protected static final String DEFAULT_ERROR_MESSAGE = "Unknown error...";

	/**
	 * C'est la méthode appelée pour exécuter le test.
	 * 
	 * @throws TestFailException
	 */
	public abstract void run() throws TestFailException;

	/**
	 * Cette méthode est appelée pour mettre le test en echec avec un message
	 * d'erreur.
	 * 
	 * @param msg
	 *            Le message d'erreur à transmettre à la
	 *            {@link TestFailException}
	 * @throws TestFailException
	 */
	protected void failure(String msg) throws TestFailException {
		Exception e = new Exception();
		StackTraceElement[] oldStack = e.getStackTrace();
		StackTraceElement[] newStack = Arrays.copyOfRange(oldStack, 2,
				oldStack.length);
		e.setStackTrace(newStack);
		throw new TestFailException(this, msg, YT.getErrorLocation(e));
	}

	/**
	 * Cette méthode est appelée pour mettre le test en echec sans un message
	 * d'erreur par défaut.
	 * 
	 * @throws TestFailException
	 */
	protected void failure() throws TestFailException {
		failure(DEFAULT_ERROR_MESSAGE);
	}

	/**
	 * Cette méthode teste si les objets transmit sont différent. Le test est
	 * mit en echec le cas échéant.
	 * 
	 * @param given
	 *            est l'objet à tester
	 * @param awaited
	 *            est l'objet attendue
	 * @param errorMessage
	 *            est le message d'erreur à afficher en cas d'echec du test
	 * @throws TestFailException
	 *             L'exception est jetée si <b>given</b> et <b>awaited</b> sont
	 *             différent.
	 */
	protected void checkDiff(Object given, Object awaited, String errorMessage)
			throws TestFailException {
		if (given != awaited && (given != null && !(given.equals(awaited)))) {
			failure(errorMessage + YT.EOL + "Donné: " + given + YT.EOL
					+ "Attendue: " + awaited);
		}
	}

	/**
	 * Cette méthode teste si les objets transmit sont égaux. Le test est mit en
	 * echec si ce n'est pas le cas.
	 * 
	 * Malgré leur ressemblance, {@link Test.checkEquals()} et
	 * 
	 * {@link Test.checkDiff()} sont différente. {@link Test.checkDiff()}
	 * utilise le comparateur {@link !=}, alors que {@link Test.checkEquals()}
	 * utilise la méthode {@link Object.equals()}. <br/>
	 * <br/>
	 * Il n'est donc pas possible de tester la nullité d'une variable avec
	 * {@link Object.equals()}, mais il est possible de tester l'égalité de deux
	 * objets.
	 * 
	 * @param given
	 *            est l'objet à tester
	 * @param awaited
	 *            est l'objet attendue
	 * @param errorMessage
	 *            est le message d'erreur à afficher en cas d'echec du test
	 * @throws TestFailException
	 *             L'exception est jetée si <b>given</b> et <b>awaited</b> sont
	 *             différent.
	 */
	protected void checkEquals(Object given, Object awaited, String errorMessage)
			throws TestFailException {
		if (given == null || !given.equals(awaited)) {
			failure(errorMessage + YT.EOL + "Donné: " + given + YT.EOL
					+ "Attendue: " + awaited);
		}
	}

	/**
	 * Cette méthode teste si le nombre transmit est strictement inférieur à
	 * celui attendue. Le test est mit en echec le cas échéant.
	 * 
	 * @param given
	 *            est le nombre à tester
	 * @param ref
	 *            est le nombre de refférence
	 * @param errorMessage
	 *            est le message d'erreur à afficher en cas d'echec du test
	 * @throws TestFailException
	 *             L'exception est jetée si <b>given</b> < <b>awaited</b>.
	 */
	protected void checkInf(double given, double ref, String errorMessage)
			throws TestFailException {
		if (given < ref) {
			failure(errorMessage + YT.EOL + "Donné: " + given + YT.EOL
					+ "Attendue: " + ref);
		}
	}

	/**
	 * Cette méthode teste si le nombre transmit est strictement suppérieur à
	 * celui attendue. Le test est mit en echec le cas échéant.
	 * 
	 * @param given
	 *            est le nombre à tester
	 * @param ref
	 *            est le nombre de refférence
	 * @param errorMessage
	 *            est le message d'erreur à afficher en cas d'echec du test
	 * @throws TestFailException
	 *             L'exception est jetée si <b>given</b> > <b>awaited</b>.
	 */
	protected void checkSup(double given, double awaited, String errorMessage)
			throws TestFailException {
		if (given > awaited) {
			failure(errorMessage + YT.EOL + "Donné: " + given + YT.EOL
					+ "Attendue: " + awaited);
		}
	}
}
