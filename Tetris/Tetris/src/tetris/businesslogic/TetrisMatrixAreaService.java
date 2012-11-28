package tetris.businesslogic;

import javax.swing.JPanel;

import tetris.model.TetrisBlockModel;
import tetris.model.TetrominoModel;

public class TetrisMatrixAreaService
{
    public void addTetromino(JPanel tetrisMatrixAreaPanel, TetrominoModel tetromino) {
        TetrisBlockModel[][] tetrisBlockComposition = tetromino.getTetrominoBlockComposition();
        
        for (int i = 0; i < tetrisBlockComposition.length; i++) {
            for (int j = 0; j < tetrisBlockComposition[i].length; i++) {
                TetrisBlockModel tetrisBlockModel = tetrisBlockComposition[i][j];
                
                if (tetrisBlockModel != null) {
                    
                }
            }
        }
    }
}
