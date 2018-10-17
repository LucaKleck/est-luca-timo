package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * @author Luca Kleck
 */
public class GraphicsController {
	
	private class Refresh implements Runnable {

		@Override
		public void run() {
			Timer timer = new Timer(1000, new RefreshTask());
			timer.setRepeats(true);
			timer.start();
		}
		
		private class RefreshTask implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
//				This will refresh all the graphics as needed by checking for changes
			}

		}
	}
	
	private Thread thread = new Thread(new Refresh());
	
	public GraphicsController() {
		thread.run();
		thread.start();
	}

}
