package tetris.presenter.interfaces;

import tetris.view.interfaces.IPlayingAreaView;

public interface IPlayingAreaPresenter
{
    void setView(IPlayingAreaView view);
    void initializePlayingArea() throws Exception;
    void shiftTetrominoLeft();
    void shiftTetrominoRight();
    void softDropTetromino();
    void rotateLeftTetromino();
    void rotateRightTetromino();
}
