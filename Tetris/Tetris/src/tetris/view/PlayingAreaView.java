package tetris.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.common.ServerConnector;
import tetris.controls.TetrisMatrixPanel;
import tetris.presenter.interfaces.IPlayingAreaPresenter;
import tetris.view.interfaces.IPlayingAreaView;

import tetris.presenter.container.PresenterContainer;

import static tetris.common.TetrisPlayingAreaConfiguration.*;

public class PlayingAreaView implements IPlayingAreaView
{
    private static final long serialVersionUID = 70105825892525223L;
    
    private ServerConnector m_ServerConnector;
    private JPanel m_PlayingAreaPanel;
    
    private JPanel m_InfoArea;
    private TetrisMatrixPanel m_TetrisMatrixArea;
    private JPanel m_HeaderArea;
    private JPanel m_InfoGameInfoArea;
    
    private JLabel m_ScoreLabel;
    private JLabel m_UserNameLabel;
    private JLabel m_LevelLabel;
    
    private JLabel m_HeaderText;
    private Timer m_Timer;
    
    private IPlayingAreaPresenter m_Presenter;
    
    public PlayingAreaView() {
        m_PlayingAreaPanel = new JPanel();
        
        m_InfoArea = new JPanel();
        m_TetrisMatrixArea = new TetrisMatrixPanel();
        m_HeaderArea = new JPanel();
        m_InfoGameInfoArea = new JPanel();
        
        m_ScoreLabel = new JLabel();
        m_UserNameLabel = new JLabel();
        m_LevelLabel = new JLabel();
        
        m_HeaderText = new JLabel();
        m_Timer = new Timer(TIMERDELAY_DEFAULT, getActionListener());
        
        m_Presenter = PresenterContainer.getPresenterContainer().getComponent(IPlayingAreaPresenter.class);
        m_Presenter.setView(this);
        
        try {
            m_Presenter.initializePlayingArea();
            m_Timer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        m_PlayingAreaPanel.addKeyListener(getKeyListener());
    }

    public KeyListener getKeyListener()
    {
        return new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                System.out.println(evt.getKeyCode());
                switch (evt.getKeyCode()) {
                case SHIFTLEFT:
                    m_Presenter.shiftTetrominoLeft();
                    break;
                case SHIFTRIGHT:
                    m_Presenter.shiftTetrominoRight();
                    break;
                case SOFTDROP:
                    m_Presenter.softDropTetromino();
                    break;
                case ROTATELEFT:
                    m_Presenter.rotateLeftTetromino();
                    break;
                case ROTATERIGHT:
                    m_Presenter.rotateRightTetromino();
                    break;
//                case 87: // w
//                	// add Tetrismino test
//                	m_Presenter.addTetromino();
//                	break;
                case 83: // s
                	// Punktesendung
                	try {
						m_ServerConnector.sendScore(9000);
					} catch (Exception e) {
						e.printStackTrace();
					}
                	break;
//                case 81: // q
//                	m_Presenter.clearCurrentTetrominoFromMatrix();
//                	break;
//                case 69: // e
//                	m_Presenter.setCurrentTetrominoInMatrix();
//                	break;
                default:
                    evt.consume();
                    return;
                }
            }

            @Override
            public void keyReleased(KeyEvent evt)
            {
            }

            @Override
            public void keyTyped(KeyEvent evt)
            {
            }
        };
    }
    
    public ActionListener getActionListener() {
    	return new ActionListener() {
    		
			@Override
			public void actionPerformed(ActionEvent e) {
				m_Presenter.updatePlayingArea();
			}
    	};
    }
    
    public JPanel getInfoArea()
    {
        return m_InfoArea;
    }

    public void setInfoArea(JPanel infoArea)
    {
        this.m_InfoArea = infoArea;
    }

    public TetrisMatrixPanel getTetrisMatrixArea()
    {
        return m_TetrisMatrixArea;
    }

    public void setTetrisMatrixArea(TetrisMatrixPanel tetrisMatrixArea)
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

    public JPanel getPlayingAreaPanel()
    {
        return m_PlayingAreaPanel;
    }

    public void setPlayingAreaPanel(JPanel playingAreaPanel)
    {
        this.m_PlayingAreaPanel = playingAreaPanel;
    }

    public JPanel getInfoGameInfoArea()
    {
        return m_InfoGameInfoArea;
    }

    public void setInfoGameInfoArea(JPanel infoGameInfoArea)
    {
        this.m_InfoGameInfoArea = infoGameInfoArea;
    }

	public Timer getTimer() {
		return m_Timer;
	}

	public void setTimer(Timer timer) {
		this.m_Timer = timer;
	}

	public void setServerConnector(ServerConnector serverConnector) {
		this.m_ServerConnector = serverConnector;  
	}
}
