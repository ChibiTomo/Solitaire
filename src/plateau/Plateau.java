package plateau;

import interfaces.IPlateau;
import yt.YT;
import exceptions.BallWrongDirectionMovemenException;
import exceptions.DisabledDestinationFieldException;
import exceptions.DisabledSourceFieldException;
import exceptions.OutOfPlateauException;
import exceptions.SourceFieldHasNoBallException;
import exceptions.TargetFieldHasBallException;

public class Plateau implements IPlateau {

	protected int[][] plateau;
	private int ballLeft = 0;

	public Plateau(int x, int y) {
		plateau = new int[x][y];
	}

	@Override
	public void setField(int[] coord, int status) throws OutOfPlateauException {
		setField(coord[0], coord[1], status);
	}

	@Override
	public void setField(int x, int y, int status) throws OutOfPlateauException {
		if (!isInsidePlateau(x, y)) {
			throw new OutOfPlateauException();
		}

		int field = plateau[x][y];
		if (field != FIELD_WITH_BALL && status == FIELD_WITH_BALL) {
			ballLeft++;
		}
		if (field == FIELD_WITH_BALL && status != FIELD_WITH_BALL) {
			ballLeft--;
		}
		plateau[x][y] = status;
	}

	@Override
	public int getBallLeft() {
		return ballLeft;
	}

	@Override
	public void displace(int x1, int y1, int x2, int y2)
			throws DisabledDestinationFieldException,
			DisabledSourceFieldException, SourceFieldHasNoBallException,
			TargetFieldHasBallException, OutOfPlateauException {

		if (!isAvailable(x1, y1)) {
			throw new DisabledSourceFieldException();
		}
		if (!isAvailable(x2, y2)) {
			throw new DisabledDestinationFieldException();
		}
		if (!hasBall(x1, y1)) {
			throw new SourceFieldHasNoBallException();
		}
		if (hasBall(x2, y2)) {
			throw new TargetFieldHasBallException();
		}

		setField(x1, y1, EMPTY_FIELD);
		setField(x2, y2, FIELD_WITH_BALL);
	}

	@Override
	public void move(int x, int y, int direction)
			throws DisabledSourceFieldException, SourceFieldHasNoBallException,
			OutOfPlateauException, BallWrongDirectionMovemenException {
		if (!isAvailable(x, y)) {
			throw new DisabledSourceFieldException();
		}
		if (!hasBall(x, y)) {
			throw new SourceFieldHasNoBallException();
		}

		if (!canMove(x, y, direction)) {
			throw new BallWrongDirectionMovemenException();
		}

		int[][] coords = getMoveTargets(x, y, direction);

		setField(x, y, EMPTY_FIELD);
		setField(coords[0], EMPTY_FIELD);
		setField(coords[1], FIELD_WITH_BALL);
	}

	@Override
	public void remove(int x, int y) throws SourceFieldHasNoBallException,
			OutOfPlateauException {
		if (!hasBall(x, y)) {
			throw new SourceFieldHasNoBallException();
		}
		setField(x, y, EMPTY_FIELD);
	}

	@Override
	public boolean isInsidePlateau(int x, int y) {
		if (x >= plateau.length) {
			return false;
		}
		if (y >= plateau[x].length) {
			return false;
		}
		return true;
	}

	private boolean hasBall(int[] coord) {
		return hasBall(coord[0], coord[1]);
	}

	@Override
	public boolean hasBall(int x, int y) {
		if (!isInsidePlateau(x, y)) {
			return false;
		}
		return plateau[x][y] == FIELD_WITH_BALL;
	}

	private boolean isAvalaible(int[] coord) {
		return isAvailable(coord[0], coord[1]);
	}

	@Override
	public boolean isAvailable(int x, int y) {
		if (!isInsidePlateau(x, y)) {
			return false;
		}
		return plateau[x][y] != DISABLED;
	}

	public int[][] getMoveTargets(int x, int y, int direction) {
		int[] coord1 = { x, y };
		int[] coord2 = { x, y };
		switch (direction) {
		case UP:
			coord1[1]++;
			coord2[1] += 2;
			break;
		case UP_RIGHT:
			coord1[1]++;
			coord2[1] += 2;
			coord1[0]++;
			coord2[0] += 2;
			break;
		case UP_LEFT:
			coord1[1]++;
			coord2[1] += 2;
			coord1[0]--;
			coord2[0] -= 2;
			break;
		case RIGHT:
			coord1[0]++;
			coord2[0] += 2;
			break;
		case DOWN:
			coord1[1]--;
			coord2[1] -= 2;
			break;
		case DOWN_RIGHT:
			coord1[1]--;
			coord2[1] -= 2;
			coord1[0]++;
			coord2[0] += 2;
			break;
		case DOWN_LEFT:
			coord1[1]--;
			coord2[1] -= 2;
			coord1[0]--;
			coord2[0] -= 2;
			break;
		case LEFT:
			coord1[0]--;
			coord2[0] -= 2;
			break;
		}
		if ((coord1[0] == x && coord1[1] == y)
				|| (coord2[0] == x && coord2[1] == y)) {
			return null;
		}
		return new int[][] { coord1, coord2 };
	}

	private boolean canMove(int x, int y, int direction) {
		if (!hasBall(x, y)) {
			return false;
		}
		int[][] coords = getMoveTargets(x, y, direction);
		if (coords == null) {
			return false;
		}
		if (!isAvalaible(coords[0]) || !isAvalaible(coords[1])) {
			return false;
		}
		if (!hasBall(coords[0])) {
			YT.println("First ball has no ball");
			try {
				setField(coords[0], TEST_FIELD);
			} catch (OutOfPlateauException e) {
				e.printStackTrace();
			}
			return false;
		}
		if (hasBall(coords[1])) {
			YT.println("Second ball has a ball");
			try {
				setField(coords[1], TEST_FIELD);
			} catch (OutOfPlateauException e) {
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		String result = "";
		String[] str_a = new String[plateau[0].length + 1];

		for (int x = 0; x < plateau.length; x++) {
			for (int y = 0; y < plateau[x].length; y++) {
				if (str_a[y + 1] == null) {
					str_a[y + 1] = " " + y + " ";
				}
				str_a[y + 1] += field2str(plateau[x][y]);
			}
		}
		str_a[0] = "   ";
		for (int i = 0; i < plateau[0].length; i++) {
			str_a[0] += " " + i + " ";
		}

		for (int i = str_a.length - 1; i >= 0; i--) {
			result += str_a[i] + YT.EOL;
		}

		return result;
	}

	private String field2str(int field) {
		String result = "";
		switch (field) {
		case FIELD_WITH_BALL:
			result = "[O]";
			break;
		case EMPTY_FIELD:
			result = "[ ]";
			break;
		case TEST_FIELD:
			result = "[X]";
			break;
		default:
			result = "   ";
			break;
		}
		return result;
	}
}
