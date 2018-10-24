package frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import core.ControlInput;
import core.CoreController;
import frame.menuPanels.MainMenuPanel;

/**  
* Frame that is used to display all the content  
* @author Luca Kleck
* @see JFrame
*/ 
public class MainJFrame extends JFrame implements ComponentListener {
	private static final long serialVersionUID = 110L;
	
	private static MainJFrame self;
	
	private Timer recalculateTimer = new Timer( 20, new resizeListener() );
	
	public MainJFrame() {
		self = this;
		this.setDefaultCloseOperation(MainJFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800,600));
		this.add(new MainMenuPanel());
		this.addMouseWheelListener(ControlInput.mouseWheeListener);
		this.addMouseListener(ControlInput.mouseListener);
		
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
	
	private class resizeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			self.repaint();
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
				try {
					if(!CoreController.mainJFrame.isValid()) {					
						CoreController.mainJFrame.validate();
					}
				} catch (NullPointerException s) {
				}
			}

		}
	}
}