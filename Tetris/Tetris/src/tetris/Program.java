package tetris;

import java.applet.Applet;

import javax.swing.JFrame;

import tetris.businesslogic.container.BusinessLogicContainer;
import tetris.common.ServerConnector;
import tetris.presenter.container.PresenterContainer;
import tetris.view.container.ViewContainer;
import tetris.view.interfaces.IPlayingAreaView;

public class Program extends Applet {
	private static final long serialVersionUID = 7304999935971765332L;
	
	ServerConnector connector;

	public static void main(String[] args) {
		ViewContainer.initializeToProductiveContainer();
		PresenterContainer.initializeToProductiveContainer();
		BusinessLogicContainer.initializeToProductiveContainer();

		IPlayingAreaView startingView = ViewContainer.getViewContainer().getComponent(IPlayingAreaView.class); 
		
		JFrame f = new JFrame("Test");
		 f.add("Center", startingView.getPlayingAreaPanel());
		 f.setSize(500, 650);
		
		 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 f.pack();
		 f.setResizable(false);
		 f.setVisible(true);
	}

	@Override
	public void init() {
		super.init();
		connector = new ServerConnector(this);
		
		ViewContainer.initializeToProductiveContainer();
		PresenterContainer.initializeToProductiveContainer();
		BusinessLogicContainer.initializeToProductiveContainer();

		IPlayingAreaView startingView = ViewContainer.getViewContainer().getComponent(IPlayingAreaView.class);
		startingView.setServerConnector(connector);
		
		// Frame for testpurposes
		this.add(startingView.getPlayingAreaPanel());
		this.requestFocus();
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
