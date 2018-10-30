package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import core.ControlInput;
import core.CoreController;
import frame.gamePanels.InteractionPanel;
import frame.gamePanels.SelectionPanel;
import frame.menuPanels.MainMenuPanel;
import map.ObjectMap;
import java.awt.Toolkit;

/**
 * Frame that is used to display all the content
 * 
 * @author Luca Kleck
 * @see JFrame
 */
public class MainJFrame extends JFrame implements ComponentListener {
	private static final long serialVersionUID = 110L;

	private static MainJFrame self;

	private Timer recalculateTimer = new Timer(20, new resizeListener());

	public MainJFrame() {
		setTitle("4x Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJFrame.class.getResource("/resources/gameIcon.png")));
		setBackground(Color.DARK_GRAY);
		setName("GameMainFrame");
		self = this;
		this.setDefaultCloseOperation(MainJFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 600));
		MainMenuPanel mainMenuPanel = new MainMenuPanel();
		mainMenuPanel.setBackground(Color.DARK_GRAY);
		getContentPane().add(mainMenuPanel);
		this.addMouseWheelListener(ControlInput.mouseWheeListener);
		this.addComponentListener(this);
		Refresh r = new Refresh();
		r.run();

		this.setVisible(true);
	}

	public static void staticRepaint() {
		try {
			if(InteractionPanel.getSelectionPane() != null) {
				InteractionPanel.setSelectionPane(new SelectionPanel(ObjectMap.getSelected().getSelectedMapTile().getXPos(), ObjectMap.getSelected().getSelectedMapTile().getYPos(), ObjectMap.getSelected().getSelectionMode() ));
			}
		} catch (NullPointerException nl) {
		}
		self.repaint();
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		if (recalculateTimer.isRunning()) {
			recalculateTimer.restart();
		} else {
			recalculateTimer.start();
			recalculateTimer.setRepeats(false);
		}
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	private class resizeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			staticRepaint();
		}

	}

	private class Refresh implements Runnable {
		private Timer timer = new Timer(100, new RefreshTask());

		@Override
		public void run() {
			timer.setRepeats(true);
			timer.start();
		}

		private class RefreshTask implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!CoreController.mainJFrame.isValid()) {
						CoreController.mainJFrame.validate();
					}
				} catch (NullPointerException s) {
				}
			}

		}
	}
}