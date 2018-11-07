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
import core.Core;
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

	private Timer recalculateTimer = new Timer(20, new resizeListener());

	public MainJFrame() {
		setTitle("4x Game");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainJFrame.class.getResource("/resources/gameIcon.png")));
		setBackground(Color.DARK_GRAY);
		setName("GameMainFrame");
		this.setDefaultCloseOperation(MainJFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2));
		MainMenuPanel mainMenuPanel = new MainMenuPanel();
		mainMenuPanel.setBackground(Color.DARK_GRAY);
		getContentPane().add(mainMenuPanel);
		this.addMouseWheelListener(ControlInput.mouseWheeListener);
		this.addComponentListener(this);
		Refresh r = new Refresh();
		r.run();

		checkSettings();
		
		this.setVisible(true);
	}

	private void checkSettings() {
		if(new Boolean(Core.getSetting(Core.SETTING_FULLSCREEN))) {
			setUndecorated(true);
			setBounds(0, 0, 0, 0);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		this.setSize(new Dimension(Integer.parseInt(Core.getSetting(Core.SETTING_DEFAULT_WIDTH)), Integer.parseInt(Core.getSetting(Core.SETTING_DEFAULT_HEIGHT)) ));
	}

	public static void staticRepaint() {
		try {
			if(InteractionPanel.getSelectionPane() != null) {
				InteractionPanel.setSelectionPane(new SelectionPanel(ObjectMap.getSelected().getSelectedMapTile().getXPos(), ObjectMap.getSelected().getSelectedMapTile().getYPos(), ObjectMap.getSelected().getSelectionMode() ));
			}
		} catch (NullPointerException nl) {
		}
		Core.getMainJFrame().repaint();
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
					if (!Core.getMainJFrame().isValid()) {
						Core.getMainJFrame().validate();
					}
				} catch (NullPointerException s) {
				}
			}

		}
	}
}