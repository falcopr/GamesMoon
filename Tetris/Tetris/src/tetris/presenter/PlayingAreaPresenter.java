package tetris.presenter;

import java.awt.Color;
import java.util.Date;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.businesslogic.interfaces.IPlayingAreaService;
import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
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
    private TetrisPlayingAreaModel m_Model;
    
    // TestBlock
    private TetrisBlockModel m_TetrisBlockModel;
    
    public PlayingAreaPresenter() {
        m_PlayingAreaService = BusinessLogicContainer.getBusinessLogicContainer().getComponent(IPlayingAreaService.class);
        m_TetrisMatrixAreaService = BusinessLogicContainer.getBusinessLogicContainer().getComponent(ITetrisMatrixAreaService.class);
        
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
    
    @Override
    public void shiftTetrominoLeft()
    {
        m_TetrisMatrixAreaService.moveTetrisBlock(m_Model.getTetrisMatrixModel(), m_TetrisBlockModel, TetrisBlockMovementDirection.WEST);
        m_View.getTetrisMatrixArea().repaint();
        
//    	Thread thread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//		    	TetrisBlockModel someTestBlockModel = new TetrisBlockModel();
//		    	long lastClockTime = System.currentTimeMillis();
//		    	int i = 0;
//				
//		    	while(true) {
//		    		if (lastClockTime + 100 < System.currentTimeMillis()) {
//		        		someTestBlockModel.setColor(Color.RED);
//		        		someTestBlockModel.setLength(25);
//		        		someTestBlockModel.setPosition(25, 25 * i);
//		        		m_Model.getTetrisMatrixModel().getTetrisBlockMatrix()[2][3] = someTestBlockModel;
//		        		m_View.getTetrisMatrixArea().repaint();
//		        		lastClockTime = System.currentTimeMillis();
//		        		i++;
//		    		}
//		    	}
//			}
//		});
//        
//    	thread.start();
    	//System.out.println("Shift Left");
    }

    @Override
    public void shiftTetrominoRight()
    {
        // TODO Auto-generated method stub
        m_TetrisMatrixAreaService.moveTetrisBlock(m_Model.getTetrisMatrixModel(), m_TetrisBlockModel, TetrisBlockMovementDirection.EAST);
        m_View.getTetrisMatrixArea().repaint();
    }

    @Override
    public void softDropTetromino()
    {
        // TODO Auto-generated method stub
        m_TetrisMatrixAreaService.moveTetrisBlock(m_Model.getTetrisMatrixModel(), m_TetrisBlockModel, TetrisBlockMovementDirection.SOUTH);
        m_View.getTetrisMatrixArea().repaint();
    }

    @Override
    public void rotateLeftTetromino()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void rotateRightTetromino()
    {
        // TODO Auto-generated method stub
        
    }
}
