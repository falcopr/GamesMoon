package tetris.model;

import java.awt.Color;
import java.awt.Rectangle;

// holds data --> essential part of which the tetromino consists of
public class TetrisBlockModel {
	// rectangle should be a square
	private int m_Length;
	private Rectangle m_Rectangle;
	private Color m_Color;

	public TetrisBlockModel() {
		m_Rectangle = new Rectangle();
	}
	
	public int getLength() {
		return m_Length;
	}

	public void setLength(int length) {
		this.m_Length = length;
		m_Rectangle.height = length;
		m_Rectangle.width = length;
	}

	public void setPosition(int i, int j) {
		m_Rectangle.x = j;
		m_Rectangle.y = i;
	}
	
	public Rectangle getRectangle() {
		return m_Rectangle;
	}
	
	public Color getColor() {
		return m_Color;
	}

	public void setColor(Color color) {
		this.m_Color = color;
	}
}
