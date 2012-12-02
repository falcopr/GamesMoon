package tetris.model;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serializable;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

// holds data --> essential part of which the tetromino consists of
public class TetrisBlockModel implements Cloneable, Serializable {
	private static final long serialVersionUID = 4804266154297042493L;
	// rectangle should be a square
	private int m_Length;
	private Rectangle m_Rectangle;
	private Color m_Color;

	public TetrisBlockModel() {
		m_Rectangle = new Rectangle();
		this.setLength(TETRISBLOCK_LENGTH); 
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
	
	public TetrisBlockModel deepClone() {
		TetrisBlockModel tetrisBlockModel = new TetrisBlockModel();
		
		tetrisBlockModel.setLength(m_Length);
		tetrisBlockModel.setPosition(m_Rectangle.y, m_Rectangle.x);
		tetrisBlockModel.setColor(m_Color);
		
		return tetrisBlockModel;
	}
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Some Cloning Errors");
			e.printStackTrace();
		}
		
		return null;
	}
}
