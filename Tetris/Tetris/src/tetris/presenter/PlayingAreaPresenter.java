package tetris.presenter;

import java.util.concurrent.atomic.AtomicInteger;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.businesslogic.interfaces.IPlayingAreaService;
import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
import tetris.businesslogic.interfaces.ITetrominoService;
import tetris.enums.TetrisBlockMovementDirection;
import tetris.model.TetrisMatrixModel;
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
        m_TetrisMatrixAreaService.addTetromino(m_TetrominoService.getNext(), m_Model.getTetrisMatrixModel());
    }
    
    public void updatePlayingArea() {
    	mainTetrominoTranslationUpdater(TetrisBlockMovementDirection.SOUTH);
    }
    
    public void shiftTetrominoLeft()
    {
    	mainTetrominoTranslationUpdater(TetrisBlockMovementDirection.WEST);
    }

    public void shiftTetrominoRight()
    {
    	mainTetrominoTranslationUpdater(TetrisBlockMovementDirection.EAST);
    }

    public void softDropTetromino()
    {
    	mainTetrominoTranslationUpdater(TetrisBlockMovementDirection.SOUTH);
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
    
    private void mainTetrominoTranslationUpdater(TetrisBlockMovementDirection movementDirection) {
    	TetrisMatrixModel tetrisMatrixModel = m_Model.getTetrisMatrixModel();
    	
    	AtomicInteger closedRowCount = new AtomicInteger(0);
    	boolean isIntersected = m_TetrisMatrixAreaService.moveCurrentTetromino(tetrisMatrixModel, movementDirection, closedRowCount);
    	m_View.getTetrisMatrixArea().repaint();
    	
    	if (isIntersected && movementDirection == TetrisBlockMovementDirection.SOUTH) {
    		try {
    			int oldScore = m_Model.getScore();
    			int newScorePlacingTetromino = oldScore + TETRISBLOCKROW_PLACINGBASEPOINTS;
    			m_Model.setScore(newScorePlacingTetromino);
    			m_View.setLabelScoreText(newScorePlacingTetromino);
    			
    			int rowsEliminated = closedRowCount.get();
    			int oldLineCount = m_Model.getLines();
    			int newLineCount = oldLineCount + rowsEliminated;
    			
    			if (rowsEliminated > 0) {
    				int rowsEliminatedPoints = m_PlayingAreaService.calculatePointsOnRowEliminationAndLevel(rowsEliminated, m_Model.getLevel());
        			newScorePlacingTetromino += rowsEliminatedPoints;
    				m_Model.setScore(newScorePlacingTetromino);
        			m_View.setLabelScoreText(newScorePlacingTetromino);
    			}
    			
    			int oldLevelThroughLineCount = oldLineCount / TETRISBLOCKROWLEVEL_THRESHOLD;
    			int newLevelThroughLineCount = newLineCount / TETRISBLOCKROWLEVEL_THRESHOLD;
    			
    			// when new level is reached
    			if (oldLevelThroughLineCount < newLevelThroughLineCount) {
    				int oldLevel = m_Model.getLevel();
    				int newLevel = oldLevel + 1;
    				
    				m_Model.setLevel(newLevel);
    				m_View.setLabelLevelText(newLevel);
    				
    				int oldSpeed = m_Model.getSpeed();
    				int newSpeed = (int) Math.round(oldSpeed * TIMERINCREASINGCONSTANT);
    				
        			System.out.println("Increased Speed: " + newSpeed);
    				
    				m_Model.setSpeed(newSpeed);
    				m_View.setTimerSpeed(newSpeed);
    			}
    			
    			m_Model.setLines(newLineCount);
    			
    			System.out.println("Lines eliminated: " + m_Model.getLines());
				
    			boolean isGameOver = m_TetrisMatrixAreaService.addTetromino(m_TetrominoService.getNext(), tetrisMatrixModel);
				
				if (isGameOver) {
					m_View.getServerConnector().sendScore(m_Model.getScore());
					m_PlayingAreaService.restartPlayingArea(m_View, m_Model);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
