package tetris.presenter;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.businesslogic.interfaces.IPlayingAreaService;
import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
import tetris.businesslogic.interfaces.ITetrominoService;
import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisPlayingAreaModel;
import tetris.presenter.interfaces.IPlayingAreaPresenter;
import tetris.view.interfaces.IPlayingAreaView;

public class PlayingAreaPresenter implements IPlayingAreaPresenter
{
    private IPlayingAreaView m_View;
    private IPlayingAreaService m_PlayingAreaService;
    private ITetrisMatrixAreaService m_TetrisMatrixAreaService;
    private ITetrominoService m_TetrominoService;
    private TetrisPlayingAreaModel m_Model;
    
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
    }
    
    public void updatePlayingArea() {
    	m_TetrisMatrixAreaService.moveCurrentTetromino(m_Model.getTetrisMatrixModel(), TetrisBlockMovementDirection.SOUTH);
    	m_View.getTetrisMatrixArea().repaint();
    }
    
    public void shiftTetrominoLeft()
    {
    	m_TetrisMatrixAreaService.moveCurrentTetromino(m_Model.getTetrisMatrixModel(), TetrisBlockMovementDirection.WEST);
        m_View.getTetrisMatrixArea().repaint();
    }

    public void shiftTetrominoRight()
    {
    	m_TetrisMatrixAreaService.moveCurrentTetromino(m_Model.getTetrisMatrixModel(), TetrisBlockMovementDirection.EAST);
        m_View.getTetrisMatrixArea().repaint();
    }

    public void softDropTetromino()
    {
    	m_TetrisMatrixAreaService.moveCurrentTetromino(m_Model.getTetrisMatrixModel(), TetrisBlockMovementDirection.SOUTH);
        m_View.getTetrisMatrixArea().repaint();
    }

    public void rotateLeftTetromino()
    {
    	m_TetrisMatrixAreaService.rotateClockwise(m_Model.getTetrisMatrixModel());
        m_View.getTetrisMatrixArea().repaint();
    }

    public void rotateRightTetromino()
    {
    	m_TetrisMatrixAreaService.rotateCounterClockwise(m_Model.getTetrisMatrixModel());
        m_View.getTetrisMatrixArea().repaint();
    }
    
    public void addTetromino() {
        // Test adding Tetrisblock
    	try {
			m_TetrisMatrixAreaService.addTetromino(m_TetrominoService.getNext(), m_Model.getTetrisMatrixModel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        m_View.getTetrisMatrixArea().repaint();
    }
    
    public void clearCurrentTetrominoFromMatrix() {
    	m_TetrominoService.clearCurrentTetriminoFromMatrix(m_Model.getTetrisMatrixModel());
    	m_Model.getTetrisMatrixModel().setCurrentTetromino(null);
    }
    
    public void setCurrentTetrominoInMatrix() {
    	m_TetrominoService.setCurrentTetriminoCompositionToMatrix(m_Model.getTetrisMatrixModel());
    	
    }
}
