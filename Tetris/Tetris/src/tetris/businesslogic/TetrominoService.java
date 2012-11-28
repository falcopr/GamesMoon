package tetris.businesslogic;

import java.awt.Color;

import tetris.model.TetrisBlockModel;
import tetris.model.TetrominoModel;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class TetrominoService
{
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
    
    public TetrisBlockModel[][] rotateClockwise(TetrisBlockModel[][] tetrisBlockModelComposition) {
        TetrisBlockModel[][] resultTetrisBlockModelComposition = new TetrisBlockModel[TETRISBLOCKMODELCOMPOSITION_MAXLENGTH][TETRISBLOCKMODELCOMPOSITION_MAXLENGTH];
        
        for (int x = 0; x < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; ++x) {
            for (int y = 0; y < TETRISBLOCKMODELCOMPOSITION_MAXLENGTH; y++) {
                resultTetrisBlockModelComposition[x][y] = tetrisBlockModelComposition[TETRISBLOCKMODELCOMPOSITION_MAXLENGTH - y - 1][x]; 
            }
        }
        
        return resultTetrisBlockModelComposition;
    }
    
    public TetrisBlockModel[][] rotateCounterClockwise(TetrisBlockModel[][] tetrisBlockModelComposition) {
        // tripple clockwise rotation
        return rotateClockwise(rotateClockwise(rotateClockwise(tetrisBlockModelComposition)));
    }
}
