package tetris.businesslogic;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

public class TetrisMatrixAreaService implements ITetrisMatrixAreaService
{
    public void addTetromino(JPanel tetrisMatrixAreaPanel, TetrisMatrixModel tetrisMatrixModel) {
        TetrominoModel currentTetromino = tetrisMatrixModel.getCurrentTetromino();
        TetrisBlockModel[][] tetrisBlockComposition = currentTetromino.getTetrominoBlockComposition();
        
        for (int i = 0; i < tetrisBlockComposition.length; i++) {
            for (int j = 0; j < tetrisBlockComposition[i].length; i++) {
                TetrisBlockModel tetrisBlockModel = tetrisBlockComposition[i][j];
                
                if (tetrisBlockModel != null) {
                    
                }
            }
        }
        
        tetrisMatrixAreaPanel.repaint();
    }
    
    public void repaintAllTetrisBlocks(Graphics g, TetrisMatrixModel tetrisMatrixModel) {
        TetrisBlockModel[][] tetrisBlockMatrix = tetrisMatrixModel.getTetrisBlockMatrix();
        for (int i = 0; i < tetrisBlockMatrix.length; i++) {
            for (int j = 0; j < tetrisBlockMatrix[i].length; j++) {
                TetrisBlockModel tetrisBlockModel = tetrisBlockMatrix[i][j];
                if (tetrisBlockModel != null) {
                    Rectangle tetrisBlockRectangle = tetrisBlockModel.getRectangle();
                    g.setColor(tetrisBlockModel.getColor());
                    g.fillRect(tetrisBlockRectangle.x, tetrisBlockRectangle.y, tetrisBlockRectangle.width, tetrisBlockRectangle.height);
                    //g.drawRect(x, y, width, height) --> drawing outline
                }
            }
        }
    }
}
