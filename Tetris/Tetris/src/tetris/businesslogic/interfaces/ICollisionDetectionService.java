package tetris.businesslogic.interfaces;

import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

public interface ICollisionDetectionService {
	boolean isTetrominoOutOfBordersOnTranslation(TetrominoModel tetromino, TetrisBlockMovementDirection movementDirection);
	boolean isTetrominoCollidingWithOtherTetrisBlocks(TetrisMatrixModel tetrisMatrixModel, TetrominoModel tetromino, TetrisBlockMovementDirection movementDirection);
}