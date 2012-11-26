package tetris;

import java.applet.Applet;

import tetris.presenter.container.PresenterContainer;
import tetris.view.container.ViewContainer;

public class Program extends Applet
{
    private static final long serialVersionUID = 7304999935971765332L;

    public static void main(String[] args)
    {
        ViewContainer.initializeToProductiveContainer();
        //framework should handle the right time to create GUI
        //javax.swing.SwingUtilities.invokeLater(null);
    }
    
	@Override
	public void init() {
		super.init();
        ViewContainer.initializeToProductiveContainer();
        PresenterContainer.initializeToProductiveContainer();
	}
	
	@Override
	public void start() {
		super.start();
		try {
			//add(t.getContentPane());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void stop() {
		super.stop();
	}
	
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
