package tetris.presenter;

import java.awt.Color;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.businesslogic.interfaces.IPlayingAreaService;
import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
import tetris.businesslogic.interfaces.ITetrominoService;
import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisPlayingAreaModel;
import tetris.presenter.interfaces.IPlayingAreaPresenter;
import tetris.view.interfaces.IPlayingAreaView;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class PlayingAreaPresenter implements IPlayingAreaPresenter
{
    private IPlayingAreaView m_View;
    private IPlayingAreaService m_PlayingAreaService;
    private ITetrisMatrixAreaService m_TetrisMatrixAreaService;
    private ITetrominoService m_TetrominoService;
    private TetrisPlayingAreaModel m_Model;
    
    // TestBlock
    private TetrisBlockModel m_TetrisBlockModel;
    
    public PlayingAreaPresenter() {
    	BusinessLogicContainer businessLogicContainer = BusinessLogicContainer.getBusinessLogicContainer();
    	
        m_PlayingAreaService = businessLogicContainer.getComponent(IPlayingAreaService.class);
        m_TetrisMatrixAreaService = businessLogicContainer.getComponent(ITetrisMatrixAreaService.class);
        m_TetrominoService = businessLogicContainer.getComponent(ITetrominoService.class);
        
        m_Model = new TetrisPlayingAreaModel();
    }

    public void setView(IPlayingAreaView view)
    {
        this.m_View = view;
    }

    public void setModel(TetrisPlayingAreaModel model) {
        this.m_Model = model;
    }
    
    public void initializePlayingArea() throws Exception
    {
        m_PlayingAreaService.configuratePlayingAreaView(m_View, m_Model);
        
        // testblock
        m_TetrisBlockModel = new TetrisBlockModel();
        m_TetrisBlockModel.setColor(Color.RED);
        m_TetrisBlockModel.setLength(TETRISBLOCK_LENGTH);
        m_TetrisBlockModel.setPosition(2, 3);
        m_Model.getTetrisMatrixModel().addTetrisBlockToMatrix(m_TetrisBlockModel);
        m_View.getTetrisMatrixArea().repaint();
    }
    
    public void updatePlayingArea() {
    	m_TetrisMatrixAreaService.moveTetrisBlock(m_Model.getTetrisMatrixModel(), m_TetrisBlockModel, TetrisBlockMovementDirection.SOUTH);
        m_View.getTetrisMatrixArea().repaint();
        System.out.println(m_View.getHeaderArea().getPreferredSize());
        System.out.println(m_View.getTetrisMatrixArea().getPreferredSize());
    }
    
    public void shiftTetrominoLeft()
    {
        m_TetrisMatrixAreaService.moveTetrisBlock(m_Model.getTetrisMatrixModel(), m_TetrisBlockModel, TetrisBlockMovementDirection.WEST);
        m_View.getTetrisMatrixArea().repaint();
    }

    public void shiftTetrominoRight()
    {
        m_TetrisMatrixAreaService.moveTetrisBlock(m_Model.getTetrisMatrixModel(), m_TetrisBlockModel, TetrisBlockMovementDirection.EAST);
        m_View.getTetrisMatrixArea().repaint();
    }

    public void softDropTetromino()
    {
        m_TetrisMatrixAreaService.moveTetrisBlock(m_Model.getTetrisMatrixModel(), m_TetrisBlockModel, TetrisBlockMovementDirection.SOUTH);
        m_View.getTetrisMatrixArea().repaint();
    }

    public void rotateLeftTetromino()
    {
        // Test adding Tetrisblock
    	try {
			m_TetrisMatrixAreaService.addTetromino(m_TetrominoService.getNext(), m_Model.getTetrisMatrixModel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void rotateRightTetromino()
    {
        
    }
}
