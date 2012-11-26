package tetris.view.interfaces;

import javax.swing.JPanel;

public interface IPlayingAreaView
{
    JPanel getInfoArea();
    void setInfoArea(JPanel infoArea);
    JPanel getTetrisMatrixArea();
    void setTetrisMatrixArea(JPanel tetrisMatrixArea);
    JPanel getHeaderArea();
    void setHeaderArea(JPanel headerArea);
}
