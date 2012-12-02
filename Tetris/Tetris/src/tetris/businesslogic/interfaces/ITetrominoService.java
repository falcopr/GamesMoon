package tetris.businesslogic.interfaces;

import java.awt.Color;

import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

public interface ITetrominoService {

	public TetrominoModel getNext() throws Exception;

	public TetrominoModel getLetterShapedTetromino(char letter)
			throws Exception;

	public Color getColorAccordingToLetterShapedTetromino(char letter);

	public TetrisBlockModel[][] getTetrisBlockModelCompositionAccordingToLetterShapedTetromino(
			char letter);

	public void setTetrominoBlockColor(
			TetrisBlockModel[][] tetrisBlockModelComposition, Color color);

	public TetrisBlockModel[][] rotateClockwise(TetrisBlockModel[][] tetrisBlockModelComposition, int xOffset, int yOffset);

	public TetrisBlockModel[][] rotateCounterClockwise(TetrisBlockModel[][] tetrisBlockModelComposition, int xOffset, int yOffset);
	
	public TetrisBlockModel[][] translateToOrigin(TetrisBlockModel[][] tetrisBlockModelComposition, int xOffset, int yOffset);
	
	public TetrisBlockModel[][] cloneTetrominoBlockModelComposition(TetrominoModel tetromino);
	
    public void clearCurrentTetriminoFromMatrix(TetrisMatrixModel tetrisMatrixModel);
    
    public void setCurrentTetriminoCompositionToMatrix(TetrisMatrixModel tetrisMatrixModel);
}