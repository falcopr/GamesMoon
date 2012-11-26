package tetris.view;

import javax.swing.JLabel;
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
    
    private JLabel m_ScoreLabel;
    private JLabel m_UserNameLabel;
    private JLabel m_LevelLabel;
    
    private JLabel m_HeaderText;
    
    private IPlayingAreaPresenter m_Presenter;
    
    public PlayingAreaView() {
        m_Presenter = PresenterContainer.getPresenterContainer().getComponent(IPlayingAreaPresenter.class);
        m_Presenter.setView(this);
        
        try
        {
            m_Presenter.initializePlayingArea();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
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

    public JLabel getScoreLabel()
    {
        return m_ScoreLabel;
    }

    public void setScoreLabel(JLabel scoreLabel)
    {
        this.m_ScoreLabel = scoreLabel;
    }

    public JLabel getUserNameLabel()
    {
        return m_UserNameLabel;
    }

    public void setUserNameLabel(JLabel userNameLabel)
    {
        this.m_UserNameLabel = userNameLabel;
    }

    public JLabel getLevelLabel()
    {
        return m_LevelLabel;
    }

    public void setLevelLabel(JLabel levelLabel)
    {
        this.m_LevelLabel = levelLabel;
    }

    public JLabel getHeaderText()
    {
        return m_HeaderText;
    }

    public void setHeaderText(JLabel headerText)
    {
        this.m_HeaderText = headerText;
    }
}
