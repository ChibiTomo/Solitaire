package tests;

import solitaire.Coordonnee;
import solitaire.CrossTablier;
import solitaire.Dimension;
import solitaire.TablierAnglais;
import exceptions.TestFailException;

public class TestJeu extends Test {

	@Override
	public void run() throws TestFailException {
		TablierAnglais tablier = new CrossTablier(new Dimension(7, 7));

		tablier.cheatOn();

		checkEquals(
				tablier.isValidPlay(new Coordonnee(0, 0), new Coordonnee(0, 0)),
				false, "Impossible de jouer au même endroit.");

		checkEquals(
				tablier.isValidPlay(new Coordonnee(0, 0), new Coordonnee(4, 2)),
				false, "Impossible de jouer d'un emplacement désactivé.");
		checkEquals(
				tablier.isValidPlay(new Coordonnee(4, 2), new Coordonnee(0, 0)),
				false, "Impossible de jouer vers un emplacement désactivé.");

		checkEquals(
				tablier.isValidPlay(new Coordonnee(4, 2), new Coordonnee(3, 8)),
				false, "Les Coordonee doivent être distante de 2 Emplacement.");

		checkEquals(
				tablier.isValidPlay(new Coordonnee(3, 3), new Coordonnee(3, 5)),
				false, "L'Emplacement source doit avoir une bille.");
		checkEquals(
				tablier.isValidPlay(new Coordonnee(0, 2), new Coordonnee(2, 2)),
				false, "L'Emplacement d'arrivé ne doit pas avoir de bille.");

		tablier.removeBille(new Coordonnee(3, 2));
		checkEquals(
				tablier.isValidPlay(new Coordonnee(3, 1), new Coordonnee(3, 3)),
				false, "L'Emplacement du milieu doit avoir une bille.");

		tablier.undo();

		checkEquals(tablier.play(new Coordonnee(3, 1), new Coordonnee(3, 3)),
				true, "Ce coup est valide.");

		checkEquals(tablier.hasBille(new Coordonnee(3, 1)), false,
				"Le coup n'a pas été joué: il reste la bille à la source.");
		checkEquals(tablier.hasBille(new Coordonnee(3, 2)), false,
				"Le coup n'a pas été joué: il reste la bille au milieu.");
		checkEquals(tablier.hasBille(new Coordonnee(3, 3)), true,
				"Le coup n'a pas été joué: il n'y a pas de bille à l'arrivé.");

		checkEquals(tablier.play(new Coordonnee(5, 4), new Coordonnee(3, 2)),
				true, "Les coups en diagonale sont valide.");
	}
}
