package tetris.businesslogic.interfaces;

import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;

public interface ITetrisBlockService {

	public void moveTetrisBlock(TetrisMatrixModel tetrisMatrixModel,
			TetrisBlockModel tetrisBlockModel,
			TetrisBlockMovementDirection movementDirection);

}