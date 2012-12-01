package tetris.businesslogic;

import java.awt.Rectangle;

import tetris.businesslogic.interfaces.ITetrisBlockService;
import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;

public class TetrisBlockService implements ITetrisBlockService {
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
    }
}
