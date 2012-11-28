package tetris.businesslogic.interfaces;

import java.awt.Graphics;

import javax.swing.JPanel;

import tetris.model.TetrisMatrixModel;

public interface ITetrisMatrixAreaService
{

    void addTetromino(JPanel tetrisMatrixAreaPanel,
            TetrisMatrixModel tetrisMatrixModel);

    void repaintAllTetrisBlocks(Graphics g, TetrisMatrixModel tetrisMatrixModel);

}