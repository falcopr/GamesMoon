package tetris.businesslogic;

import java.awt.Point;
import java.awt.Rectangle;

import tetris.businesslogic.interfaces.ICollisionDetectionService;
import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class CollisionDetectionService implements ICollisionDetectionService {
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
	
	public boolean isTetrominoCollidingWithOtherTetrisBlocks(TetrisMatrixModel tetrisMatrixModel, TetrominoModel tetromino, TetrisBlockMovementDirection movementDirection) {
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
}
