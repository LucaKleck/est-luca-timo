/**  
* MainJFrame.java - The frame that is used to display all the content  
* @author Luca Kleck
* @version 0.01
* @since 0.01 
* @see JFrame
*/ 
package frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import core.CoreController;
import frame.menuPanels.MainMenuPanel;

public class MainJFrame extends JFrame implements ComponentListener {
	private static final long serialVersionUID = 110L;
	
	private class resizeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//			redraw the map so it fits the size again
		}
		
	}
	
	private class Refresh implements Runnable {
		
		@Override
		public void run() {
			Timer timer = new Timer(100, new RefreshTask());
			timer.setRepeats(true);
			timer.start();
		}
		
		private class RefreshTask implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!CoreController.mainJFrame.isValid()) {					
					CoreController.mainJFrame.validate();
				}
			}

		}
	}
	private Timer recalculateTimer = new Timer( 20, new resizeListener() );
	
	public MainJFrame() {
		this.setDefaultCloseOperation(MainJFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,600));
		
		this.add(new MainMenuPanel());
		
		
		Refresh r = new Refresh();
		r.run();

		this.setVisible(true);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}


	@Override
	public void componentMoved(ComponentEvent e) {	
	}


	@Override
	public void componentResized(ComponentEvent e) {
		if ( recalculateTimer.isRunning() ){
		    recalculateTimer.restart();
		  } else {
		    recalculateTimer.start();
		  }
	}


	@Override
	public void componentShown(ComponentEvent e) {
	}
}