package interfaces;

import exceptions.BallWrongDirectionMovemenException;
import exceptions.DisabledDestinationFieldException;
import exceptions.DisabledSourceFieldException;
import exceptions.OutOfPlateauException;
import exceptions.SourceFieldHasNoBallException;
import exceptions.TargetFieldHasBallException;

public interface IPlateau {

	public static final int DISABLED = 0;
	public static final int EMPTY_FIELD = 1;
	public static final int FIELD_WITH_BALL = 2;
	public static final int TEST_FIELD = 3;

	public static final int UP = 0;
	public static final int UP_RIGHT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN_RIGHT = 3;
	public static final int DOWN = 4;
	public static final int DOWN_LEFT = 5;
	public static final int LEFT = 6;
	public static final int UP_LEFT = 7;

	public void setField(int x, int y, int status) throws OutOfPlateauException;

	public void setField(int[] coord, int status) throws OutOfPlateauException;

	public int getBallLeft();

	public void displace(int x1, int y1, int x2, int y2)
			throws DisabledDestinationFieldException,
			DisabledSourceFieldException, SourceFieldHasNoBallException,
			TargetFieldHasBallException, OutOfPlateauException;

	public void move(int x, int y, int direction)
			throws DisabledSourceFieldException, SourceFieldHasNoBallException,
			OutOfPlateauException, BallWrongDirectionMovemenException;

	public void remove(int x, int y) throws SourceFieldHasNoBallException,
			OutOfPlateauException;

	boolean isInsidePlateau(int x, int y);

	boolean hasBall(int x, int y);

	boolean isAvailable(int x, int y);

	public int[][] getMoveTargets(int x, int y, int direction);
}
