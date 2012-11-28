package tetris.presenter;

import java.awt.Color;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.businesslogic.interfaces.IPlayingAreaService;
import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
import tetris.model.TetrisBlockModel;
import tetris.model.TetrisPlayingAreaModel;
import tetris.presenter.interfaces.IPlayingAreaPresenter;
import tetris.view.interfaces.IPlayingAreaView;

public class PlayingAreaPresenter implements IPlayingAreaPresenter
{
    private IPlayingAreaView m_View;
    private IPlayingAreaService m_PlayingAreaService;
    private ITetrisMatrixAreaService m_TetrisMatrixAreaService;
    private TetrisPlayingAreaModel m_Model;
    
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
        m_PlayingAreaService.ConfiguratePlayingAreaView(m_View, m_Model);
    }

    @Override
    public void shiftTetrominoLeft()
    {
    	TetrisBlockModel someTestBlockModel = new TetrisBlockModel();
    	someTestBlockModel.setColor(Color.RED);
    	someTestBlockModel.setLength(25);
    	someTestBlockModel.setPosition(25, 25);
    	m_Model.getTetrisMatrixModel().getTetrisBlockMatrix()[2][3] = someTestBlockModel;
    	m_View.getTetrisMatrixArea().repaint();
        System.out.println("Shift Left");
    }

    @Override
    public void shiftTetrominoRight()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void softDropTetromino()
    {
        // TODO Auto-generated method stub
        
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
