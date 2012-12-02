package tetris.businesslogic.interfaces;

import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JPanel;

import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

public interface ITetrisMatrixAreaService
{
	boolean addTetromino(TetrominoModel tetrominoModel, TetrisMatrixModel tetrisMatrixModel);

    void repaintAllTetrisBlocks(Graphics g, TetrisMatrixModel tetrisMatrixModel);
    
    boolean moveCurrentTetromino(TetrisMatrixModel tetrisMatrixModel, TetrisBlockMovementDirection movementDirection, AtomicInteger closedRowCount);
    
    void rotateClockwise(TetrisMatrixModel tetrisMatrixModel);
    
    void rotateCounterClockwise(TetrisMatrixModel tetrisMatrixModel);
    
    void restartTetrisMatrixArea(TetrisMatrixModel tetrisMatrixModel);
}