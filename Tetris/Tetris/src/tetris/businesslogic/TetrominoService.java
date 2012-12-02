package tetris.businesslogic;

import java.awt.Color;

import tetris.businesslogic.interfaces.ITetrominoService;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisMatrixModel;
import tetris.model.TetrominoModel;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class TetrominoService implements ITetrominoService
{
    /* (non-Javadoc)
	 * @see tetris.businesslogic.ITetrominoService#getNext()
	 */
    public TetrominoModel getNext() throws Exception {
        char letter = 'o';
        
        switch ((int) (Math.random() * 7)) {
        case 0:
            letter = 'i';
            break;
        case 1:
            letter ='j';
            break;
        case 2:
            letter ='l';
            break;
        case 3:
            letter ='o';
            break;
        case 4:
            letter ='s';
            break;
        case 5:
            letter ='t';
            break;
        default:
            letter ='z';
            break;
        }
        
        return getLetterShapedTetromino(letter);
    }
    
    /* (non-Javadoc)
	 * @see tetris.businesslogic.ITetrominoService#getLetterShapedTetromino(char)
	 */
    public TetrominoModel getLetterShapedTetromino(char letter) throws Exception {
        TetrisBlockModel[][] tetrisBlockModelComposition = getTetrisBlockModelCompositionAccordingToLetterShapedTetromino(letter);
        
        if (tetrisBlockModelComposition == null) {
            throw new Exception("Zu dem ausgewählten Buchstaben gibt es kein Tetromino");
        }
        
        Color colorOfTetromino = getColorAccordingToLetterShapedTetromino(letter);
        setTetrominoBlockColor(tetrisBlockModelComposition, colorOfTetromino);
        
        TetrominoModel tetrominoModel = new TetrominoModel();
        tetrominoModel.setTetrominoBlockComposition(tetrisBlockModelComposition);
        
        return tetrominoModel;
    }
    
    /* (non-Javadoc)
	 * @see tetris.businesslogic.ITetrominoService#getColorAccordingToLetterShapedTetromino(char)
	 */
    public Color getColorAccordingToLetterShapedTetromino(char letter) {
        switch (letter) {
        case 'i':
            return Color.CYAN;
        case 'j':
            return Color.MAGENTA;
        case 'l':
            return Color.ORANGE;
        case 'o':
            return Color.RED;
        case 's':
            return Color.BLUE;
        case 't':
            return Color.YELLOW;
        case 'z':
            return Color.GREEN;
        default:
            return Color.LIGHT_GRAY;
        }
    }
    
    /* (non-Javadoc)
	 * @see tetris.businesslogic.ITetrominoService#getTetrisBlockModelCompositionAccordingToLetterShapedTetromino(char)
	 */
    public TetrisBlockModel[][] getTetrisBlockModelCompositionAccordingToLetterShapedTetromino(char letter) {
        TetrisBlockModel[][] resultComposition = new TetrisBlockModel[TETRISBLOCKMODELCOMPOSITION_MAXLENGTH][TETRISBLOCKMODELCOMPOSITION_MAXLENGTH];
        
        switch (letter) {
        case 'i':
            resultComposition[0][0] = new TetrisBlockModel();
            resultComposition[1][0] = new TetrisBlockModel();
            resultComposition[2][0] = new TetrisBlockModel();
            resultComposition[3][0] = new TetrisBlockModel();
            break;
        case 'j':
            resultComposition[0][1] = new TetrisBlockModel();
            resultComposition[1][1] = new TetrisBlockModel();
            resultComposition[2][0] = new TetrisBlockModel();
            resultComposition[2][1] = new TetrisBlockModel();
            break;
        case 'l':
            resultComposition[0][0] = new TetrisBlockModel();
            resultComposition[1][0] = new TetrisBlockModel();
            resultComposition[2][0] = new TetrisBlockModel();
            resultComposition[2][1] = new TetrisBlockModel();
            break;
        case 'o':
            resultComposition[0][0] = new TetrisBlockModel();
            resultComposition[0][1] = new TetrisBlockModel();
            resultComposition[1][0] = new TetrisBlockModel();
            resultComposition[1][1] = new TetrisBlockModel();
            break;
        case 's':
            resultComposition[0][1] = new TetrisBlockModel();
            resultComposition[0][2] = new TetrisBlockModel();
            resultComposition[1][0] = new TetrisBlockModel();
            resultComposition[1][1] = new TetrisBlockModel();
            break;
        case 't':
            resultComposition[0][0] = new TetrisBlockModel();
            resultComposition[1][0] = new TetrisBlockModel();
            resultComposition[1][1] = new TetrisBlockModel();
            resultComposition[2][0] = new TetrisBlockModel();
            break;
        case 'z':
            resultComposition[0][0] = new TetrisBlockModel();
            resultComposition[0][1] = new TetrisBlockModel();
            resultComposition[1][1] = new TetrisBlockModel();
            resultComposition[1][2] = new TetrisBlockModel();
            break;
        default:
            return null;
        }
        
        return resultComposition;
    }
    
    /* (non-Javadoc)
	 * @see tetris.businesslogic.ITetrominoService#setTetrominoBlockColor(tetris.model.TetrisBlockModel[][], java.awt.Color)
	 */
    public void setTetrominoBlockColor(TetrisBlockModel[][] tetrisBlockModelComposition, Color color) {
        for (int x = 0; x < tetrisBlockModelComposition.length; x++) {
            for (int y = 0; y < tetrisBlockModelComposition[x].length; y++) {
                TetrisBlockModel tetrisBlockModel = tetrisBlockModelComposition[x][y];
                
                if (tetrisBlockModel != null) {
                    tetrisBlockModel.setColor(color);
                }
            }
        }
    }
    
    /* (non-Javadoc)
	 * @see tetris.businesslogic.ITetrominoService#rotateClockwise(tetris.model.TetrisBlockModel[][])
	 */
    public TetrisBlockModel[][] rotateClockwise(TetrisBlockModel[][] tetrisBlockModelComposition, int xOffset, int yOffset) {
        TetrisBlockModel[][] resultTetrisBlockModelComposition = new TetrisBlockModel[TETRISBLOCKMODELCOMPOSITION_MAXLENGTH][TETRISBLOCKMODELCOMPOSITION_MAXLENGTH];
        
        // rorates matrix clockwise
        for (int i = 0; i < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; ++i) {
            for (int j = 0; j < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; ++j) {
                resultTetrisBlockModelComposition[i][j] = tetrisBlockModelComposition[TETRISBLOCKMODELCOMPOSITION_MAXLENGTH - j - 1][i]; 
                
                if (resultTetrisBlockModelComposition[i][j] != null) {
                    resultTetrisBlockModelComposition[i][j].setPosition(i + yOffset, j + xOffset);
                    System.out.println("Rotation x: " + (j + xOffset) + " y: " + (i + yOffset));
                }
            }
        }
        
        
        return resultTetrisBlockModelComposition;
    }
    
    /* (non-Javadoc)
	 * @see tetris.businesslogic.ITetrominoService#rotateCounterClockwise(tetris.model.TetrisBlockModel[][])
	 */
    public TetrisBlockModel[][] rotateCounterClockwise(TetrisBlockModel[][] tetrisBlockModelComposition, int xOffset, int yOffset) {
        // tripple clockwise rotation
    	TetrisBlockModel[][] tempTetrisBlockModelCompositionTrippleRotated = rotateClockwise(rotateClockwise(rotateClockwise(tetrisBlockModelComposition, xOffset, yOffset), xOffset, yOffset), xOffset, yOffset);  
        return tempTetrisBlockModelCompositionTrippleRotated;
    }
    
    public TetrisBlockModel[][] translateToOrigin(TetrisBlockModel[][] tetrisBlockModelComposition, int xOffset, int yOffset) {
        TetrisBlockModel[][] resultTetrisBlockModelComposition = new TetrisBlockModel[TETRISBLOCKMODELCOMPOSITION_MAXLENGTH][TETRISBLOCKMODELCOMPOSITION_MAXLENGTH];
        
    	int possibleXTranslation = 0;
        for (int x = 0; x < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; x++) {
        	boolean isNullBlockColumn = true;
        	
            for (int y = 0; y < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; y++) {
            	isNullBlockColumn &= tetrisBlockModelComposition[y][x] == null;
            }
            
            if (!isNullBlockColumn) {
            	break;
            }
            
            possibleXTranslation++;
        }
        
        System.out.println("Possible X Translation: " + possibleXTranslation);
        
    	int possibleYTranslation = 0;
        for (int y = 0; y < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; y++) {
        	boolean isNullBlockRow = true;
        	
            for (int x = 0; x < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; x++) {
            	isNullBlockRow &= tetrisBlockModelComposition[y][x] == null;
            }
            
            if (!isNullBlockRow) {
            	break;
            }
            
            possibleYTranslation++;
        }
        
        System.out.println("Possible Y Translation: " + possibleYTranslation);
        
        if (possibleXTranslation != 0 || possibleYTranslation != 0) {
        	for (int yTranslation = 0; yTranslation < possibleYTranslation; yTranslation++) {
            	for (int x = 0; x < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; x++) {
            		for (int y = 0; y < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; y++) {
            			TetrisBlockModel tempTetrisBlockModel = tetrisBlockModelComposition[y][x];
            			
            			if (tempTetrisBlockModel != null) {
                			tetrisBlockModelComposition[y][x] = null;
                			resultTetrisBlockModelComposition[y - 1][x] = tempTetrisBlockModel;
                			tempTetrisBlockModel.setPosition(yOffset + y - 1, xOffset + x);
                            System.out.println("Tranlation x: " + (x + xOffset) + " y: " + (y + yOffset));
            			}
            		}
            	}
        	}
        	
        	for (int xTranslation = 0; xTranslation < possibleXTranslation; xTranslation++) {
	        	for (int y = 0; y < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; y++) {
	        		for (int x = 0; x < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; x++) {
	        			TetrisBlockModel tempTetrisBlockModel = tetrisBlockModelComposition[y][x];
	        			
	        			if (tempTetrisBlockModel != null) {
	            			tetrisBlockModelComposition[y][x] = null;
	            			resultTetrisBlockModelComposition[y][x - 1] = tempTetrisBlockModel;
                			tempTetrisBlockModel.setPosition(yOffset + y, xOffset + x - 1);
                            System.out.println("Tranlation x: " + (x + xOffset) + " y: " + (y + yOffset));
	        			}
	        		}
	        	}
        	}
        	
            return resultTetrisBlockModelComposition;
        }
        
        return tetrisBlockModelComposition;
    }
    
    public TetrisBlockModel[][] cloneTetrominoBlockModelComposition(TetrominoModel tetromino) {
		TetrisBlockModel[][] originalTetrisBlockModelComposition = tetromino.getTetrominoBlockComposition();
		TetrisBlockModel[][] clonedTetrisBlockModelComposition = new TetrisBlockModel[TETRISBLOCKMODELCOMPOSITION_MAXLENGTH][TETRISBLOCKMODELCOMPOSITION_MAXLENGTH];
		
		for (int i = 0; i < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; i++) {
			clonedTetrisBlockModelComposition[i] = new TetrisBlockModel[TETRISBLOCKMODELCOMPOSITION_MAXLENGTH];
			
			for (int j = 0; j < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; j++) {
				TetrisBlockModel tetrisBlockModel = originalTetrisBlockModelComposition[i][j];
				
				if (tetrisBlockModel != null) {
					clonedTetrisBlockModelComposition[i][j] = (TetrisBlockModel) tetrisBlockModel.deepClone();
				}
			}
		}
		
		return clonedTetrisBlockModelComposition;
    }
    
    public void clearCurrentTetriminoFromMatrix(TetrisMatrixModel tetrisMatrixModel) {
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
    
    public void setCurrentTetriminoCompositionToMatrix(TetrisMatrixModel tetrisMatrixModel) {
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
