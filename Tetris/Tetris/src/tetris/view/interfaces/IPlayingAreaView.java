package tetris.view.interfaces;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tetris.controls.TetrisMatrixPanel;

public interface IPlayingAreaView
{
    JPanel getPlayingAreaPanel();
    
    void setPlayingAreaPanel(JPanel playingAreaPanel);

    JPanel getInfoArea();

    void setInfoArea(JPanel infoArea);

    TetrisMatrixPanel getTetrisMatrixArea();

    void setTetrisMatrixArea(TetrisMatrixPanel tetrisMatrixArea);

    JPanel getHeaderArea();

    void setHeaderArea(JPanel headerArea);

    JLabel getScoreLabel();

    void setScoreLabel(JLabel scoreLabel);

    JLabel getUserNameLabel();

    void setUserNameLabel(JLabel userNameLabel);

    JLabel getLevelLabel();

    void setLevelLabel(JLabel levelLabel);

    JLabel getHeaderText();

    void setHeaderText(JLabel headerText);
    
    JPanel getInfoGameInfoArea();
    
    void setInfoGameInfoArea(JPanel infoGameInfoArea);
    
    Timer getTimer();
    
    void setTimer(Timer timer);
}