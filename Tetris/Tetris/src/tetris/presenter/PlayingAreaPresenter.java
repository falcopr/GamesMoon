package tetris.presenter;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.businesslogic.interfaces.IPlayingAreaService;
import tetris.model.TetrisPlayingAreaModel;
import tetris.presenter.interfaces.IPlayingAreaPresenter;
import tetris.view.interfaces.IPlayingAreaView;

public class PlayingAreaPresenter implements IPlayingAreaPresenter
{
    private IPlayingAreaView m_View;
    private IPlayingAreaService m_Service;
    private TetrisPlayingAreaModel m_Model;
    
    public PlayingAreaPresenter() {
        m_Service = BusinessLogicContainer.getBusinessLogicContainer().getComponent(IPlayingAreaService.class);
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
        m_Service.ConfiguratePlayingAreaView(m_View, m_Model);
    }

    @Override
    public void shiftTetrominoLeft()
    {
        // TODO Auto-generated method stub
        
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
