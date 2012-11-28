package tetris.businesslogic.interfaces;

import javax.swing.JPanel;

import tetris.model.TetrisMatrixModel;

public interface ITetrisMatrixAreaService
{

    void addTetromino(JPanel tetrisMatrixAreaPanel,
            TetrisMatrixModel tetrisMatrixModel);

    void repaintAllTetrisBlocks(JPanel tetrisMatrixAreaPanel,
            TetrisMatrixModel tetrisMatrixModel);

}