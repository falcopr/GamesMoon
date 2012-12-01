package tetris.businesslogic;

import java.awt.Point;
import java.awt.Rectangle;

import tetris.businesslogic.interfaces.ICollisionDetectionService;
import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrominoModel;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class CollisionDetectionService implements ICollisionDetectionService {
	public boolean isTetrominoOutOfBordersOnTranslation(TetrominoModel tetromino, TetrisBlockMovementDirection movementDirection) {
    	boolean isOutOfBorder = false;
		
    	int xDirectionIndicator = 0;
    	int yDirectionIndicator = 0;
    	
        switch (movementDirection) {
        case NORTH:
        	yDirectionIndicator--;
            break;
        case SOUTH:
        	yDirectionIndicator++;
            break;
        case WEST:
        	xDirectionIndicator--;
            break;
        case EAST:
        	xDirectionIndicator++;
            break;
        default:
            return isOutOfBorder;
        }
    	
		TetrisBlockModel[][] tetriminoBlockComposition = tetromino.getTetrominoBlockComposition();
    	for (int i = 0; i < tetriminoBlockComposition.length; i++) {
            for (int j = 0; j < tetriminoBlockComposition[i].length; j++) {
            	TetrisBlockModel tetrisBlockModel = tetriminoBlockComposition[i][j];
            	
            	if (tetrisBlockModel != null) {
            		Rectangle tetrisBlockPosition = tetrisBlockModel.getRectangle();
            		
            		int xFutureTetrisBlockPosition = tetrisBlockPosition.x + xDirectionIndicator;
            		int yFutureTetrisBlockPosition = tetrisBlockPosition.y + yDirectionIndicator;
            		
            		if (xFutureTetrisBlockPosition < 0 || xFutureTetrisBlockPosition >= TETRISBLOCK_WIDTH || yFutureTetrisBlockPosition >= TETRISBLOCK_HEIGHT) {
            			return true;
            		}
            	}
            }
    	}
    	
    	return isOutOfBorder;
	}
}
