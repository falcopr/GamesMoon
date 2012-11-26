package tetris.presenter.interfaces;

import tetris.view.interfaces.IPlayingAreaView;

public interface IPlayingAreaPresenter
{
    void setView(IPlayingAreaView view);
    void initializePlayingArea() throws Exception;
}
