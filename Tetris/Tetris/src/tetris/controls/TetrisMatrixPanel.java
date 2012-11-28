package tetris.controls;

import java.awt.Graphics;

import javax.swing.JPanel;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
import tetris.model.TetrisMatrixModel;

public class TetrisMatrixPanel extends JPanel {
	private static final long serialVersionUID = -161711089866674186L;
	
	private TetrisMatrixModel m_TetrisMatrixModel;
	private ITetrisMatrixAreaService m_TetrisMatrixAreaService;
	
	public TetrisMatrixPanel() {
		super(null);
		m_TetrisMatrixAreaService = BusinessLogicContainer.getBusinessLogicContainer().getComponent(ITetrisMatrixAreaService.class);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      
	      m_TetrisMatrixAreaService.repaintAllTetrisBlocks(g, m_TetrisMatrixModel);
	}

	public TetrisMatrixModel getTetrisMatrixModel() {
		return m_TetrisMatrixModel;
	}

	public void setTetrisMatrixModel(TetrisMatrixModel tetrisMatrixModel) {
		this.m_TetrisMatrixModel = tetrisMatrixModel;
	}
}
