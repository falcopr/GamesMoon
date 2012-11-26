package tetris.model;

import java.awt.Color;
import java.awt.Point;

// holds the data of the tetrominos placed on the playing field
public class TetrisMatrixModel {
	// Width and height should be calculated relatively to the blocksize
	private int m_Width = 10;
	private int m_Height = 23;
	private int m_TetrisBlockLength = 25;
	private Color m_BackgroundColor;
	private TetrisBlockModel[][] m_TetrisBlockMatrix;
	private Point m_Position;
	
	public TetrisMatrixModel() {
		m_TetrisBlockMatrix = new TetrisBlockModel[m_Width][m_Height];
		m_BackgroundColor = Color.GRAY;
		m_Position = new Point(0,0);
	}
	
	public TetrisMatrixModel(int tetrisBlockLength, int width, int heigth, Color backgroundColor) {
		this.m_TetrisBlockLength = tetrisBlockLength;
		this.m_Width = width;
		this.m_Height = heigth;
		
		m_TetrisBlockMatrix = new TetrisBlockModel[m_Width][m_Height];
		m_BackgroundColor = backgroundColor;
	}

	public TetrisBlockModel[][] getTetrisBlockMatrix() {
		return m_TetrisBlockMatrix;
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
}