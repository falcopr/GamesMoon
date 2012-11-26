package tetris.presenter.container;

import org.picocontainer.DefaultPicoContainer;
import tetris.presenter.*;
import tetris.presenter.interfaces.*;

public class PresenterContainer extends DefaultPicoContainer
{
    private static final long serialVersionUID = 2944516552452492930L;
    
    private static PresenterContainer m_PresenterContainer = new PresenterContainer();
    
    private PresenterContainer() {
        
    }
    
    public static PresenterContainer getPresenterContainer() {
        return m_PresenterContainer;
    }
    
    public static void initializeToProductiveContainer() {
        m_PresenterContainer.addComponent(IPlayingAreaPresenter.class, PlayingAreaPresenter.class);
    }
}
