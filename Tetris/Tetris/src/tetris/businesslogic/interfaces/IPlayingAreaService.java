package tetris.businesslogic.interfaces;

import tetris.model.TetrisPlayingAreaModel;
import tetris.view.interfaces.IPlayingAreaView;

public interface IPlayingAreaService
{
    void configuratePlayingAreaView(IPlayingAreaView view, TetrisPlayingAreaModel model) throws Exception;
    
    void restartPlayingArea(IPlayingAreaView view, TetrisPlayingAreaModel playingAreaModel);
    
    int calculatePointsOnRowEliminationAndLevel(int rowsEliminated, int level);
}
