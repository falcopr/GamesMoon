package tetris.businesslogic.container;

import org.picocontainer.DefaultPicoContainer;

import tetris.businesslogic.PlayingAreaService;
import tetris.businesslogic.TetrisMatrixAreaService;
import tetris.businesslogic.TetrominoService;
import tetris.businesslogic.interfaces.IPlayingAreaService;
import tetris.businesslogic.interfaces.ITetrisMatrixAreaService;
import tetris.businesslogic.interfaces.ITetrominoService;

public class BusinessLogicContainer extends DefaultPicoContainer
{
    private static final long serialVersionUID = 2601902261389279437L;
    
    private static BusinessLogicContainer m_BusinessLogicContainer = new BusinessLogicContainer();
    
    private BusinessLogicContainer() {
        
    }

    public static BusinessLogicContainer getBusinessLogicContainer() {
        return m_BusinessLogicContainer;
    }
    
    public static void initializeToProductiveContainer() {
        m_BusinessLogicContainer.addComponent(IPlayingAreaService.class, PlayingAreaService.class);
        m_BusinessLogicContainer.addComponent(ITetrisMatrixAreaService.class, TetrisMatrixAreaService.class);
        m_BusinessLogicContainer.addComponent(ITetrominoService.class, TetrominoService.class);
    }
}
