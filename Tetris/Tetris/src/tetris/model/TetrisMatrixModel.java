package tetris.model;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

// holds the data of the tetrominos placed on the playing field
public class TetrisMatrixModel {
	// Width and height should be calculated relatively to the blocksize
	private int m_Width = TETRISBLOCK_WIDTH;
	private int m_Height = TETRISBLOCK_HEIGHT;
	private int m_TetrisBlockLength = TETRISBLOCK_LENGTH;
	private Color m_BackgroundColor;
	private TetrisBlockModel[][] m_TetrisBlockMatrix;
	private Point m_Position;
	private TetrominoModel m_CurrentTetromino;
	
	public TetrisMatrixModel() {
		m_TetrisBlockMatrix = new TetrisBlockModel[getHeight()][getWidth()];
		m_BackgroundColor = Color.GRAY;
		m_Position = new Point(0,0);
	}
	
	public TetrisMatrixModel(int tetrisBlockLength, int width, int heigth, Color backgroundColor) {
		this.m_TetrisBlockLength = tetrisBlockLength;
		this.m_Width = width;
		this.m_Height = heigth;
		
		m_TetrisBlockMatrix = new TetrisBlockModel[getHeight()][getWidth()];
		
		// initialize matrix empty
		for (int i = 0; i < getWidth(); i++) {
			m_TetrisBlockMatrix[i] = new TetrisBlockModel[] {};
			
			for (int j = 0; j < getHeight(); j++) {
				m_TetrisBlockMatrix[i][j] = null;
			}
		}
		
		m_BackgroundColor = backgroundColor;
	}

	public TetrisBlockModel[][] getTetrisBlockMatrix() {
		return m_TetrisBlockMatrix;
	}

	public void addTetrisBlockToMatrix(TetrisBlockModel tetrisBlockModel) {
	    Rectangle rectangle = tetrisBlockModel.getRectangle();
	    
	    // TetrisBlockModel contains Positioning in Matrix instead of absolute Position
	    int i = rectangle.y;
	    int j = rectangle.x;
	    
	    m_TetrisBlockMatrix[i][j] = tetrisBlockModel;
	}
	
	public Color getBackgroundColor() {
		return m_BackgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.m_BackgroundColor = backgroundColor;
	}

    public Point getPosition()
    {
        return m_Position;
    }

    public void setPosition(Point position)
    {
        this.m_Position = position;
    }
    
    public TetrominoModel getCurrentTetromino() {
        return m_CurrentTetromino;
    }
    
    public void setCurrentTetromino(TetrominoModel tetromino) {
        this.m_CurrentTetromino = tetromino;
    }

	public int getHeight() {
		return m_Height;
	}

	public int getWidth() {
		return m_Width;
	}
}