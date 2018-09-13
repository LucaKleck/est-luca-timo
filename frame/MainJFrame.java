package frame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainJFrame extends JFrame {
	private static final long serialVersionUID = 111L;
	
	/*private class Refresh implements Runnable {

		@Override
		public void run() {
			Timer timer = new Timer(true);
			timer.schedule(new RefreshTask(), 10000);
		}
		
		private class RefreshTask extends TimerTask {

			@Override
			public void run() {
				CoreController.mainJFrame.repaint();
			}
			
		}
		
	}*/// Test Ignore for now
	
	public MainJFrame() {
		this.setDefaultCloseOperation(MainJFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,600));
		this.add(new MainMenuFrame());
		this.setVisible(true);
	}

}
