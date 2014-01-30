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
	 * Il s'agit du message d'erreur par d�faut d'un test.
	 */
	protected static final String DEFAULT_ERROR_MESSAGE = "Unknown error...";

	/**
	 * C'est la m�thode appel�e pour ex�cuter le test.
	 * 
	 * @throws TestFailException
	 */
	public abstract void run() throws TestFailException;

	/**
	 * Cette m�thode est appel�e pour mettre le test en echec avec un message
	 * d'erreur.
	 * 
	 * @param msg
	 *            Le message d'erreur � transmettre � la
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
	 * Cette m�thode est appel�e pour mettre le test en echec sans un message
	 * d'erreur par d�faut.
	 * 
	 * @throws TestFailException
	 */
	protected void failure() throws TestFailException {
		failure(DEFAULT_ERROR_MESSAGE);
	}

	/**
	 * Cette m�thode teste si les objets transmit sont diff�rent. Le test est
	 * mit en echec le cas �ch�ant.
	 * 
	 * @param given
	 *            est l'objet � tester
	 * @param awaited
	 *            est l'objet attendue
	 * @param errorMessage
	 *            est le message d'erreur � afficher en cas d'echec du test
	 * @throws TestFailException
	 *             L'exception est jet�e si <b>given</b> et <b>awaited</b> sont
	 *             diff�rent.
	 */
	protected void checkDiff(Object given, Object awaited, String errorMessage)
			throws TestFailException {
		if (given != awaited && (given != null && !(given.equals(awaited)))) {
			failure(errorMessage + YT.EOL + "Donn�: " + given + YT.EOL
					+ "Attendue: " + awaited);
		}
	}

	/**
	 * Cette m�thode teste si les objets transmit sont �gaux. Le test est mit en
	 * echec si ce n'est pas le cas.
	 * 
	 * Malgr� leur ressemblance, {@link Test.checkEquals()} et
	 * 
	 * {@link Test.checkDiff()} sont diff�rente. {@link Test.checkDiff()}
	 * utilise le comparateur {@link !=}, alors que {@link Test.checkEquals()}
	 * utilise la m�thode {@link Object.equals()}. <br/>
	 * <br/>
	 * Il n'est donc pas possible de tester la nullit� d'une variable avec
	 * {@link Object.equals()}, mais il est possible de tester l'�galit� de deux
	 * objets.
	 * 
	 * @param given
	 *            est l'objet � tester
	 * @param awaited
	 *            est l'objet attendue
	 * @param errorMessage
	 *            est le message d'erreur � afficher en cas d'echec du test
	 * @throws TestFailException
	 *             L'exception est jet�e si <b>given</b> et <b>awaited</b> sont
	 *             diff�rent.
	 */
	protected void checkEquals(Object given, Object awaited, String errorMessage)
			throws TestFailException {
		if (given == null || !given.equals(awaited)) {
			failure(errorMessage + YT.EOL + "Donn�: " + given + YT.EOL
					+ "Attendue: " + awaited);
		}
	}

	/**
	 * Cette m�thode teste si le nombre transmit est strictement inf�rieur �
	 * celui attendue. Le test est mit en echec le cas �ch�ant.
	 * 
	 * @param given
	 *            est le nombre � tester
	 * @param ref
	 *            est le nombre de reff�rence
	 * @param errorMessage
	 *            est le message d'erreur � afficher en cas d'echec du test
	 * @throws TestFailException
	 *             L'exception est jet�e si <b>given</b> < <b>awaited</b>.
	 */
	protected void checkInf(double given, double ref, String errorMessage)
			throws TestFailException {
		if (given < ref) {
			failure(errorMessage + YT.EOL + "Donn�: " + given + YT.EOL
					+ "Attendue: " + ref);
		}
	}

	/**
	 * Cette m�thode teste si le nombre transmit est strictement supp�rieur �
	 * celui attendue. Le test est mit en echec le cas �ch�ant.
	 * 
	 * @param given
	 *            est le nombre � tester
	 * @param ref
	 *            est le nombre de reff�rence
	 * @param errorMessage
	 *            est le message d'erreur � afficher en cas d'echec du test
	 * @throws TestFailException
	 *             L'exception est jet�e si <b>given</b> > <b>awaited</b>.
	 */
	protected void checkSup(double given, double awaited, String errorMessage)
			throws TestFailException {
		if (given > awaited) {
			failure(errorMessage + YT.EOL + "Donn�: " + given + YT.EOL
					+ "Attendue: " + awaited);
		}
	}
}
