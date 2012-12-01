package tetris.businesslogic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class TetrisMatrixAreaService implements ITetrisMatrixAreaService
{
    public void addTetromino(TetrominoModel tetrominoModel, TetrisMatrixModel tetrisMatrixModel) {
        tetrisMatrixModel.setCurrentTetromino(tetrominoModel);
        // Blockcomposition of tetromino
        TetrisBlockModel[][] tetrisBlockComposition = tetrominoModel.getTetrominoBlockComposition();
        int centerOfTetrisMatrixModel = (tetrisMatrixModel.getWidth() / 2) - 1;
        
        for (int i = 0; i < tetrisBlockComposition.length; i++) {
            for (int j = 0; j < tetrisBlockComposition[i].length; j++) {
                TetrisBlockModel tetrisBlockModel = tetrisBlockComposition[i][j];
                
                if (tetrisBlockModel != null) {
                    tetrisBlockModel.setPosition(i, centerOfTetrisMatrixModel + j);
                    tetrisMatrixModel.addTetrisBlockToMatrix(tetrisBlockModel);
                }
            }
        }
    }
    
    public void repaintAllTetrisBlocks(Graphics g, TetrisMatrixModel tetrisMatrixModel) {
        TetrisBlockModel[][] tetrisBlockMatrix = tetrisMatrixModel.getTetrisBlockMatrix();
        for (int i = 0; i < tetrisBlockMatrix.length; i++) {
            for (int j = 0; j < tetrisBlockMatrix[i].length; j++) {
                TetrisBlockModel tetrisBlockModel = tetrisBlockMatrix[i][j];
                if (tetrisBlockModel != null) {
                    Rectangle tetrisBlockRectangle = tetrisBlockModel.getRectangle();
                    g.setColor(tetrisBlockModel.getColor());
                    g.fillRect(tetrisBlockRectangle.width * j, tetrisBlockRectangle.height * i, tetrisBlockRectangle.width, tetrisBlockRectangle.height);
                    //g.drawRect(x, y, width, height) --> drawing outline
                }
                g.setColor(Color.BLACK);
                g.drawRect(TETRISBLOCK_LENGTH * j, TETRISBLOCK_LENGTH * i, TETRISBLOCK_LENGTH, TETRISBLOCK_LENGTH);
            }
        }
    }
    
    public void moveTetrisBlock(TetrisMatrixModel tetrisMatrixModel, TetrisBlockModel tetrisBlockModel, TetrisBlockMovementDirection movementDirection) {
        Rectangle currentTetrisBlockRectangle = tetrisBlockModel.getRectangle();

        int currentJ = currentTetrisBlockRectangle.x;
        int currentI = currentTetrisBlockRectangle.y;

        int i = 0; 
        int j = 0;
                
        switch (movementDirection) {
        case NORTH:
            i = currentI - 1;
            j = currentJ;
            break;
        case SOUTH:
            i = currentI + 1;
            j = currentJ;
            break;
        case WEST:
            i = currentI;
            j = currentJ - 1;
            break;
        case EAST:
            i = currentI;
            j = currentJ + 1;
            break;
        default:
            return;
        }
        
        tetrisBlockModel.setPosition(i, j);
        System.out.println(i + " " + j);
    }
    
    public void moveCurrentTetromino(TetrisMatrixModel tetrisMatrixModel, TetrisBlockMovementDirection movementDirection) {
    	TetrominoModel currentTetrominoModel = tetrisMatrixModel.getCurrentTetromino();
    	
    	if (currentTetrominoModel != null) {
        	TetrisBlockModel[][] tetriminoBlockComposition = currentTetrominoModel.getTetrominoBlockComposition();
        	
            for (int i = 0; i < tetriminoBlockComposition.length; i++) {
                for (int j = 0; j < tetriminoBlockComposition[i].length; j++) {
                    TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
                    
                    if (tetrisBlockModel != null) {
                	    int j1 = tetrisBlockModel.getRectangle().x;
                	    int i1 = tetrisBlockModel.getRectangle().y;
                	    
                    	tetrisMatrixModel.getTetrisBlockMatrix()[i1][j1] = null;
                    }
                	

                }
            }
        	
            for (int i = 0; i < tetriminoBlockComposition.length; i++) {
                for (int j = 0; j < tetriminoBlockComposition[i].length; j++) {
                    TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
                	
                    if (tetrisBlockModel != null) {
                    	moveTetrisBlock(tetrisMatrixModel, tetrisBlockModel, movementDirection);
                        tetrisMatrixModel.addTetrisBlockToMatrix(tetrisBlockModel);
                    }
                }
            }
    	}
    }
}
