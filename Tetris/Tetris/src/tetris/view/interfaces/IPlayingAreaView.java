package tetris.view.interfaces;

import javax.swing.JLabel;
import javax.swing.JPanel;

public interface IPlayingAreaView
{

    JPanel getInfoArea();

    void setInfoArea(JPanel infoArea);

    JPanel getTetrisMatrixArea();

    void setTetrisMatrixArea(JPanel tetrisMatrixArea);

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

}