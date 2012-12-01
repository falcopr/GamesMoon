package tetris.businesslogic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.businesslogic.interfaces.ITetrisBlockService;
import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
import tetris.businesslogic.interfaces.ITetrominoService;
import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class TetrisMatrixAreaService implements ITetrisMatrixAreaService
{
	private ITetrisBlockService m_TetrisBlockService;
	private ITetrominoService m_TetrominoService;
	
	public TetrisMatrixAreaService () {
		m_TetrisBlockService = BusinessLogicContainer.getBusinessLogicContainer().getComponent(ITetrisBlockService.class);
		m_TetrominoService = BusinessLogicContainer.getBusinessLogicContainer().getComponent(ITetrominoService.class);
	}
	
    public void addTetromino(TetrominoModel tetrominoModel, TetrisMatrixModel tetrisMatrixModel) {
        tetrisMatrixModel.setCurrentTetromino(tetrominoModel);
        // Blockcomposition of tetromino
        TetrisBlockModel[][] tetrisBlockComposition = tetrominoModel.getTetrominoBlockComposition();
        int centerOfTetrisMatrixModel = (tetrisMatrixModel.getWidth() / 2) - 1;
        
        // tetromino position is the future offset and the origin of the tetrimino composition
        tetrominoModel.setPosition(centerOfTetrisMatrixModel, 0);
        
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
    
    public void moveCurrentTetromino(TetrisMatrixModel tetrisMatrixModel, TetrisBlockMovementDirection movementDirection) {
    	TetrominoModel currentTetrominoModel = tetrisMatrixModel.getCurrentTetromino();
    	
    	if (currentTetrominoModel != null) {
        	Point currentTetriminoPosition = currentTetrominoModel.getPosition();
        	
            switch (movementDirection) {
            case NORTH:
            	currentTetriminoPosition.y--;
                break;
            case SOUTH:
            	currentTetriminoPosition.y++;
                break;
            case WEST:
            	currentTetriminoPosition.x--;
                break;
            case EAST:
            	currentTetriminoPosition.x++;
                break;
            default:
                return;
            }
    		
        	// cleanup current tetromino position from matrix
        	clearCurrentTetriminoFromMatrix(tetrisMatrixModel);
        	
            // move tetromino and set blocks to the matrix
        	TetrisBlockModel[][] tetriminoBlockComposition = currentTetrominoModel.getTetrominoBlockComposition();
        	for (int i = 0; i < tetriminoBlockComposition.length; i++) {
                for (int j = 0; j < tetriminoBlockComposition[i].length; j++) {
                    TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
                	
                    if (tetrisBlockModel != null) {
                    	m_TetrisBlockService.moveTetrisBlock(tetrisMatrixModel, tetrisBlockModel, movementDirection);
                        tetrisMatrixModel.addTetrisBlockToMatrix(tetrisBlockModel);
                    }
                }
            }
    	}
    }
    
    public void rotateClockwise(TetrisMatrixModel tetrisMatrixModel) {
    	TetrominoModel currentTetrominoModel = tetrisMatrixModel.getCurrentTetromino();
    	if (currentTetrominoModel != null) {
    		TetrisBlockModel[][] tetrisBlockModelComposition = currentTetrominoModel.getTetrominoBlockComposition();
    		
    		clearCurrentTetriminoFromMatrix(tetrisMatrixModel);
	    	
	    	Point currentTetrominoPosition = currentTetrominoModel.getPosition();
	    	TetrisBlockModel[][] rotatedTetrisBlockModelComposition = m_TetrominoService.rotateClockwise(tetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
	    	TetrisBlockModel[][] translatedTetrisBlockModelComposition = m_TetrominoService.translateToOrigin(rotatedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
	    	
	    	currentTetrominoModel.setTetrominoBlockComposition(translatedTetrisBlockModelComposition);
	    	setCurrentTetriminoCompositionToMatrix(tetrisMatrixModel);
    	}
    }
    
    public void rotateCounterClockwise(TetrisMatrixModel tetrisMatrixModel) {
    	TetrominoModel currentTetrominoModel = tetrisMatrixModel.getCurrentTetromino();
    	if (currentTetrominoModel != null) {
    		TetrisBlockModel[][] tetrisBlockModelComposition = currentTetrominoModel.getTetrominoBlockComposition();
    		
    		clearCurrentTetriminoFromMatrix(tetrisMatrixModel);
	    	
	    	Point currentTetrominoPosition = currentTetrominoModel.getPosition();
	    	TetrisBlockModel[][] rotatedTetrisBlockModelComposition = m_TetrominoService.rotateCounterClockwise(tetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
	    	TetrisBlockModel[][] translatedTetrisBlockModelComposition = m_TetrominoService.translateToOrigin(rotatedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
	    	
	    	currentTetrominoModel.setTetrominoBlockComposition(translatedTetrisBlockModelComposition);
	    	setCurrentTetriminoCompositionToMatrix(tetrisMatrixModel);
    	}
    }
    
    private void clearCurrentTetriminoFromMatrix(TetrisMatrixModel tetrisMatrixModel) {
    	TetrominoModel currentTetrominoModel = tetrisMatrixModel.getCurrentTetromino();
		
    	TetrisBlockModel[][] tetriminoBlockComposition = currentTetrominoModel.getTetrominoBlockComposition();
		
    	// clears current rotation on the tetrisMatrixModel
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
    }
    
    private void setCurrentTetriminoCompositionToMatrix(TetrisMatrixModel tetrisMatrixModel) {
    	TetrisBlockModel[][] tetriminoBlockComposition = tetrisMatrixModel.getCurrentTetromino().getTetrominoBlockComposition();
    	for (int i = 0; i < tetriminoBlockComposition.length; i++) {
            for (int j = 0; j < tetriminoBlockComposition[i].length; j++) {
                TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
            	
                if (tetrisBlockModel != null) {
                	System.out.println("Set Current Tetrimino Composition To Matrix x: " + tetrisBlockModel.getRectangle().x + " y: " + tetrisBlockModel.getRectangle().y);
                    tetrisMatrixModel.addTetrisBlockToMatrix(tetrisBlockModel);
                }
            }
        }
    }
}
