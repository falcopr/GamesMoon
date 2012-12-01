package tetris;

import java.applet.Applet;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.presenter.container.PresenterContainer;
import tetris.view.container.ViewContainer;
import tetris.view.interfaces.IPlayingAreaView;

public class Program extends Applet {
	private static final long serialVersionUID = 7304999935971765332L;
	
	ServerConnector connector;

	public static void main(String[] args) {
		// framework should handle the right time to create GUI
		// javax.swing.SwingUtilities.invokeLater(null);
	}

	@Override
	public void init() {
		super.init();
		connector = new ServerConnector(this, getParameter("path"));
		
		
		ViewContainer.initializeToProductiveContainer();
		PresenterContainer.initializeToProductiveContainer();
		BusinessLogicContainer.initializeToProductiveContainer();

		IPlayingAreaView startingView = ViewContainer.getViewContainer()
				.getComponent(IPlayingAreaView.class);

		// Frame for testpurposes
		this.add(startingView.getPlayingAreaPanel());
		this.setSize(500, 650);
		this.setVisible(true);
		// JFrame f = new JFrame("Test");
		// f.add("Center", );
		// f.setSize(800, 600);
		//
		// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// f.pack();
		// f.setResizable(false);
		// f.setVisible(true);

	}

	@Override
	public void start() {
		super.start();

		try {
			// add(t.getContentPane());
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
