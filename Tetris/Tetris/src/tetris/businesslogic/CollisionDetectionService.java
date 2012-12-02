package tetris.businesslogic;

import java.awt.Point;
import java.awt.Rectangle;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.businesslogic.interfaces.ICollisionDetectionService;
import tetris.businesslogic.interfaces.ITetrominoService;
import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class CollisionDetectionService implements ICollisionDetectionService {
	
	ITetrominoService m_TetrominoService; 
	
	public CollisionDetectionService () {
		m_TetrominoService = BusinessLogicContainer.getBusinessLogicContainer().getComponent(ITetrominoService.class);
	}
	
	// has according to the direction x = +1 (EAST), x = -1 (WEST), y = +1 (NORTH), y = -1 (SOUTH)
	public Point movementDirectionToVector (TetrisBlockMovementDirection movementDirection) {
		Point movementVector = new Point(0, 0);
		
        switch (movementDirection) {
        case NORTH:
        	movementVector.y = -1;
            break;
        case SOUTH:
        	movementVector.y = +1;
            break;
        case WEST:
        	movementVector.x = -1;
            break;
        case EAST:
        	movementVector.x = +1;
            break;
        default:
        	break;
        }
        
        return movementVector;
	}
	
	public boolean isTetrominoOutOfBordersOnTranslation(TetrominoModel tetromino, TetrisBlockMovementDirection movementDirection) {
    	boolean isOutOfBorder = false;
    	Point movementVector = movementDirectionToVector(movementDirection);
    	
		TetrisBlockModel[][] tetriminoBlockComposition = tetromino.getTetrominoBlockComposition();
    	for (int i = 0; i < tetriminoBlockComposition.length; i++) {
            for (int j = 0; j < tetriminoBlockComposition[i].length; j++) {
            	TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
            	
            	if (tetrisBlockModel != null) {
            		Rectangle tetrisBlockPosition = tetrisBlockModel.getRectangle();
            		
            		int xFutureTetrisBlockPosition = tetrisBlockPosition.x + movementVector.x;
            		int yFutureTetrisBlockPosition = tetrisBlockPosition.y + movementVector.y;
            		
            		if (xFutureTetrisBlockPosition < 0 ||
        				xFutureTetrisBlockPosition >= TETRISBLOCK_WIDTH ||
        				yFutureTetrisBlockPosition >= TETRISBLOCK_HEIGHT) {
            			return true;
            		}
            	}
            }
    	}
    	
    	return isOutOfBorder;
	}
	
	public boolean isTetrominoOutOfBottomBordersOnTranslation(TetrominoModel tetromino, TetrisBlockMovementDirection movementDirection) {
    	boolean isOutOfBorder = false;
    	Point movementVector = movementDirectionToVector(movementDirection);
    	
		TetrisBlockModel[][] tetriminoBlockComposition = tetromino.getTetrominoBlockComposition();
    	for (int i = 0; i < tetriminoBlockComposition.length; i++) {
            for (int j = 0; j < tetriminoBlockComposition[i].length; j++) {
            	TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
            	
            	if (tetrisBlockModel != null) {
            		Rectangle tetrisBlockPosition = tetrisBlockModel.getRectangle();
            		int yFutureTetrisBlockPosition = tetrisBlockPosition.y + movementVector.y;
            		
            		if (yFutureTetrisBlockPosition >= TETRISBLOCK_HEIGHT) {
            			return true;
            		}
            	}
            }
    	}
    	
    	return isOutOfBorder;
	}
	
	public boolean isTetrominoCollidingWithOtherTetrisBlocksOnTranslation(TetrisMatrixModel tetrisMatrixModel, TetrominoModel tetromino, TetrisBlockMovementDirection movementDirection) {
    	boolean isCollidingWithTetrisBlock = false;
		TetrisBlockModel[][] tetriminoBlockComposition = tetromino.getTetrominoBlockComposition();
    	Point movementVector = movementDirectionToVector(movementDirection);
    	
    	TetrisBlockModel[] tempPotentialTetrisBlockCollider = new TetrisBlockModel[TETRISBLOCKMODELCOMPOSITION_MAXLENGTH];
    	
        switch (movementDirection) {
        case NORTH:
        	for (int i = 0; i < tetriminoBlockComposition.length; i++) {
                for (int j = 0; j < tetriminoBlockComposition[i].length; j++) {
                	TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
                	
                	if (tetrisBlockModel != null && tempPotentialTetrisBlockCollider[j] == null) {
                		tempPotentialTetrisBlockCollider[j] = tetrisBlockModel;
                	}
                }
        	}
            break;
        case SOUTH:
        	for (int i = tetriminoBlockComposition.length - 1; i > -1; i--) {
                for (int j = 0; j < tetriminoBlockComposition[i].length; j++) {
                	TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
                	
                	if (tetrisBlockModel != null  && tempPotentialTetrisBlockCollider[j] == null) {
                		tempPotentialTetrisBlockCollider[j] = tetrisBlockModel;
                	}
                }
        	}
            break;
        case WEST:
        	for (int j = 0; j < tetriminoBlockComposition.length; j++) {
                for (int i = 0; i < tetriminoBlockComposition[j].length; i++) {
                	TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
                	
                	if (tetrisBlockModel != null && tempPotentialTetrisBlockCollider[i] == null) {
                		tempPotentialTetrisBlockCollider[i] = tetrisBlockModel;
                	}
                }
        	}
            break;
        case EAST:
        	for (int j = tetriminoBlockComposition.length - 1; j > -1; j--) {
                for (int i = 0; i < tetriminoBlockComposition[j].length; i++) {
                	TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
                	
                	if (tetrisBlockModel != null && tempPotentialTetrisBlockCollider[i] == null) {
                		tempPotentialTetrisBlockCollider[i] = tetrisBlockModel;
                	}
                }
        	}
            break;
        default:
        	return isCollidingWithTetrisBlock;
        }
    	
        for (int tetrisBlockCounter = 0; tetrisBlockCounter < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; tetrisBlockCounter++) {
        	TetrisBlockModel tetrisBlockModel = tempPotentialTetrisBlockCollider[tetrisBlockCounter];
        	
        	if (tetrisBlockModel != null) {
        		Rectangle tetrisBlockPosition = tetrisBlockModel.getRectangle();
        		
        		int xFutureTetrisBlockPosition = tetrisBlockPosition.x + movementVector.x;
        		int yFutureTetrisBlockPosition = tetrisBlockPosition.y + movementVector.y;
        		
        		if (tetrisMatrixModel.getTetrisBlockMatrix()[yFutureTetrisBlockPosition][xFutureTetrisBlockPosition] != null) {
        			return true;
        		}
        	}
        }
    	
    	return isCollidingWithTetrisBlock;
	}

	public boolean isTetrominoOutOfBordersOnClockwiseRotation(TetrisMatrixModel tetrisMatrixModel, TetrominoModel tetromino) {
		TetrisBlockModel[][] clonedTetrisBlockModelComposition = m_TetrominoService.cloneTetrominoBlockModelComposition(tetromino);
		
		Point currentTetrominoPosition = tetromino.getPosition();
		
		TetrisBlockModel[][] rotatedTetrisBlockModelComposition = m_TetrominoService.rotateClockwise(clonedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
    	TetrisBlockModel[][] translatedTetrisBlockModelComposition = m_TetrominoService.translateToOrigin(rotatedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
    	
    	m_TetrominoService.clearCurrentTetriminoFromMatrix(tetrisMatrixModel);
    	tetrisMatrixModel.setCurrentTetromino(null);
    	
		boolean isIntersecting = isTetrominoIntersectingWithTheBorders(translatedTetrisBlockModelComposition);
		
    	tetrisMatrixModel.setCurrentTetromino(tetromino);
		m_TetrominoService.setCurrentTetriminoCompositionToMatrix(tetrisMatrixModel);
		
		return isIntersecting;
	}
	
	public boolean isTetrominoOutOfBordersOnCounterClockwiseRotation(TetrisMatrixModel tetrisMatrixModel, TetrominoModel tetromino) {
		TetrisBlockModel[][] clonedTetrisBlockModelComposition = m_TetrominoService.cloneTetrominoBlockModelComposition(tetromino);
		
		Point currentTetrominoPosition = tetromino.getPosition();
		
		TetrisBlockModel[][] rotatedTetrisBlockModelComposition = m_TetrominoService.rotateCounterClockwise(clonedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
    	TetrisBlockModel[][] translatedTetrisBlockModelComposition = m_TetrominoService.translateToOrigin(rotatedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
    	
    	m_TetrominoService.clearCurrentTetriminoFromMatrix(tetrisMatrixModel);
    	tetrisMatrixModel.setCurrentTetromino(null);
    	
		boolean isIntersecting = isTetrominoIntersectingWithTheBorders(translatedTetrisBlockModelComposition);
		
    	tetrisMatrixModel.setCurrentTetromino(tetromino);
		m_TetrominoService.setCurrentTetriminoCompositionToMatrix(tetrisMatrixModel);
		
		return isIntersecting;
	}
	
	public boolean isTetrominoCollidingWithOtherTetrisBlocksOnClockwiseRotation(TetrisMatrixModel tetrisMatrixModel, TetrominoModel tetromino) {
		TetrisBlockModel[][] clonedTetrisBlockModelComposition = m_TetrominoService.cloneTetrominoBlockModelComposition(tetromino);
		
		Point currentTetrominoPosition = tetromino.getPosition();
		
		TetrisBlockModel[][] rotatedTetrisBlockModelComposition = m_TetrominoService.rotateClockwise(clonedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
    	TetrisBlockModel[][] translatedTetrisBlockModelComposition = m_TetrominoService.translateToOrigin(rotatedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
    	
    	m_TetrominoService.clearCurrentTetriminoFromMatrix(tetrisMatrixModel);
    	tetrisMatrixModel.setCurrentTetromino(null);
    	
		boolean isIntersecting = isTetrominoIntersectingWithOtherTetrisBlocksOnTetrisMatrixModel(tetrisMatrixModel, translatedTetrisBlockModelComposition);
		
    	tetrisMatrixModel.setCurrentTetromino(tetromino);
		m_TetrominoService.setCurrentTetriminoCompositionToMatrix(tetrisMatrixModel);
		
		return isIntersecting;
	}
	
	public boolean isTetrominoCollidingWithOtherTetrisBlocksOnCounterClockwiseRotation(TetrisMatrixModel tetrisMatrixModel, TetrominoModel tetromino) {
		TetrisBlockModel[][] clonedTetrisBlockModelComposition = m_TetrominoService.cloneTetrominoBlockModelComposition(tetromino);
		
		Point currentTetrominoPosition = tetromino.getPosition();
		
		TetrisBlockModel[][] rotatedTetrisBlockModelComposition = m_TetrominoService.rotateCounterClockwise(clonedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
    	TetrisBlockModel[][] translatedTetrisBlockModelComposition = m_TetrominoService.translateToOrigin(rotatedTetrisBlockModelComposition, currentTetrominoPosition.x, currentTetrominoPosition.y);
    	
    	m_TetrominoService.clearCurrentTetriminoFromMatrix(tetrisMatrixModel);
    	tetrisMatrixModel.setCurrentTetromino(null);
    	
		boolean isIntersecting = isTetrominoIntersectingWithOtherTetrisBlocksOnTetrisMatrixModel(tetrisMatrixModel, translatedTetrisBlockModelComposition);
		
    	tetrisMatrixModel.setCurrentTetromino(tetromino);
		m_TetrominoService.setCurrentTetriminoCompositionToMatrix(tetrisMatrixModel);
		
		return isIntersecting;
	}
	
	public boolean isTetrominoIntersectingWithOtherTetrisBlocksOnTetrisMatrixModel(TetrisMatrixModel tetrisMatrixModel, TetrisBlockModel[][] tetrisBlockModelComposition) {
		TetrisBlockModel[][] tetrisBlockMatrix = tetrisMatrixModel.getTetrisBlockMatrix();
		
		for (int i = 0; i < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; i++) {
			for (int j = 0; j < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; j++) {
				TetrisBlockModel tetrisBlockModel = tetrisBlockModelComposition[i][j];
				
				if (tetrisBlockModel != null) {
					Rectangle tetrisBlockModelPosition = tetrisBlockModel.getRectangle();
					
					if (tetrisBlockMatrix[tetrisBlockModelPosition.y][tetrisBlockModelPosition.x] != null) {
						return true;
					}
				}
			}
		}
    	
    	return false;
	}
	
	private boolean isTetrominoIntersectingWithTheBorders(TetrisBlockModel[][] tetrisBlockModelComposition) {
		for (int i = 0; i < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; i++) {
			for (int j = 0; j < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; j++) {
				TetrisBlockModel tetrisBlockModel = tetrisBlockModelComposition[i][j];
				
				if (tetrisBlockModel != null) {
					Rectangle tetrisBlockModelPosition = tetrisBlockModel.getRectangle();
					
					if (tetrisBlockModelPosition.y >= TETRISBLOCK_HEIGHT ||
						tetrisBlockModelPosition.x >= TETRISBLOCK_WIDTH ||
						tetrisBlockModelPosition.x < 0){
						return true;
					}
				}
			}
		}
		
		return false;
	}
}
