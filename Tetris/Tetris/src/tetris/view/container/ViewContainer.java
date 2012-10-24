package tetris.view.container;

import org.picocontainer.DefaultPicoContainer;
import tetris.view.*;
import tetris.view.interfaces.*;

public class ViewContainer extends DefaultPicoContainer
{
    private static final long serialVersionUID = 2897443246428233457L;
    
    private static ViewContainer m_ViewContainer = new ViewContainer();
    
    private ViewContainer()
    {
    }
    
    public static ViewContainer getViewContainer()
    {
        return m_ViewContainer;
    }
    
    public static void initializeToProductiveContainer()
    {
        m_ViewContainer.addComponent(IMainMenuView.class, MainMenuView.class);
        m_ViewContainer.addComponent(IPlayingAreaView.class, PlayingAreaView.class);
    }
}
