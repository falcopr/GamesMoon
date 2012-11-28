package tetris.model;

import java.awt.Point;

// defines the data of the geometric shape composed by tetrisblocks
public class TetrominoModel {
	private boolean m_Active = false;
	private Point m_Position;
	private TetrisBlockModel[][] m_TetrominoBlockComposition;

	public TetrominoModel() {
		m_Position = new Point(0, 0);
	}
	
	public boolean isActive() {
		return m_Active;
	}

	public void setActivStatus(boolean activeStatus) {
		this.m_Active = activeStatus;
	}

	public Point getPosition() {
		return m_Position;
	}

	public void setPosition(int x, int y) {
		this.m_Position.x = x;
		this.m_Position.y = y;
	}

    public TetrisBlockModel[][] getTetrominoBlockComposition()
    {
        return m_TetrominoBlockComposition;
    }

    public void setTetrominoBlockComposition(
            TetrisBlockModel[][] tetrominoBlockComposition)
    {
        this.m_TetrominoBlockComposition = tetrominoBlockComposition;
    }
}
