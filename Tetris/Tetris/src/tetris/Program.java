package tetris;

import tetris.view.container.ViewContainer;

public class Program
{
    public static void main(String[] args)
    {
        ViewContainer.initializeToProductiveContainer();
        //framework should handle the right time to create GUI
        //javax.swing.SwingUtilities.invokeLater(null);
    }
}
