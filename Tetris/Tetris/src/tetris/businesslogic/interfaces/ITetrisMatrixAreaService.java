package tetris.businesslogic.interfaces;

import java.awt.Graphics;

import javax.swing.JPanel;

import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

public interface ITetrisMatrixAreaService
{
	void addTetromino(TetrominoModel tetrominoModel, TetrisMatrixModel tetrisMatrixModel);

    void repaintAllTetrisBlocks(Graphics g, TetrisMatrixModel tetrisMatrixModel);
    
    void moveTetrisBlock(TetrisMatrixModel tetrisMatrixModel, TetrisBlockModel tetrisBlockModel, TetrisBlockMovementDirection movementDirection);
}