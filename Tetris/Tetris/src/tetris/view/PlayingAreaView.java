package tetris.view;

import javax.swing.JPanel;

import tetris.presenter.interfaces.IPlayingAreaPresenter;
import tetris.view.interfaces.IPlayingAreaView;

import tetris.presenter.container.PresenterContainer;

public class PlayingAreaView extends JPanel implements IPlayingAreaView
{
    private static final long serialVersionUID = 70105825892525223L;
    
    private JPanel m_InfoArea;
    private JPanel m_TetrisMatrixArea;
    private JPanel m_HeaderArea;
    
    private IPlayingAreaPresenter m_Presenter;
    
    public PlayingAreaView() {
        m_Presenter = PresenterContainer.getPresenterContainer().getComponent(IPlayingAreaPresenter.class);
        m_Presenter.setView(this);
        m_Presenter.initializePlayingArea();
    }

    public JPanel getInfoArea()
    {
        return m_InfoArea;
    }

    public void setInfoArea(JPanel infoArea)
    {
        this.m_InfoArea = infoArea;
    }

    public JPanel getTetrisMatrixArea()
    {
        return m_TetrisMatrixArea;
    }

    public void setTetrisMatrixArea(JPanel tetrisMatrixArea)
    {
        this.m_TetrisMatrixArea = tetrisMatrixArea;
    }

    public JPanel getHeaderArea()
    {
        return m_HeaderArea;
    }

    public void setHeaderArea(JPanel headerArea)
    {
        this.m_HeaderArea = headerArea;
    }
}
