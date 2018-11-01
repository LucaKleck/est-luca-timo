package frame.gamePanels;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import core.ControlInput;
import core.CoreController;
import core.XMLSaveAndLoad;
import entity.Ability;
import map.ObjectMap;

public class GameMenuPanel extends JPanel {
	private static final long serialVersionUID = 122L;
	private JMenuBar menuBar;

	public GameMenuPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("MenuBar.background"));
		add(menuBar);

		JMenu mnOptions = new JMenu("Options");
		mnOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnOptions);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnOptions.add(mntmSave);

		JCheckBoxMenuItem chckbxmntmShowLog = new JCheckBoxMenuItem("show Log");
		chckbxmntmShowLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxmntmShowLog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		chckbxmntmShowLog.setSelected(true);
		mnOptions.add(chckbxmntmShowLog);
		
		JCheckBoxMenuItem chckbxmntmFullscreen = new JCheckBoxMenuItem("Fullscreen");
		chckbxmntmFullscreen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxmntmFullscreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_MASK));
		chckbxmntmFullscreen.setSelected(CoreController.getMainJFrame().isUndecorated());
		mnOptions.add(chckbxmntmFullscreen);
		
		JMenuItem mntmExitToMain = new JMenuItem("Exit to Main Menu");
		mntmExitToMain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOptions.add(mntmExitToMain);
		
		JMenuItem mntmExitGame = new JMenuItem("Exit Game");
		mntmExitGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOptions.add(mntmExitGame);

		JMenu mnDev = new JMenu("dev");
		mnDev.setFocusTraversalKeysEnabled(false);
		mnDev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnDev);

		JMenuItem mntmRemakeMap = new JMenuItem("Remake Map");
		mntmRemakeMap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmRemakeMap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnDev.add(mntmRemakeMap);

		JMenuItem mntmSendLogLine = new JMenuItem("Send log line");
		mntmSendLogLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSendLogLine.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnDev.add(mntmSendLogLine);
		
		JMenuItem mntmGenerateDefaultUnit = new JMenuItem("generate default unit ability");
		mntmGenerateDefaultUnit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmGenerateDefaultUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjectMap.getSelected().setSelectedAbility(new Ability("dev_create_unit"));
			}
		});
		mnDev.add(mntmGenerateDefaultUnit);
		
		
		mntmSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LogPanel.appendNewLine(XMLSaveAndLoad.saveGame());
			}
		});
		
		mntmExitToMain.setActionCommand("frame.menuPanels.MainMenuPanel");
		mntmExitToMain.addActionListener(ControlInput.menuChanger);
		
		mntmExitGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		chckbxmntmFullscreen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				CoreController.getMainJFrame().dispose();
				// put it in the left uppermost corner
				CoreController.getMainJFrame().setBounds(0, 0, 0, 0);
				CoreController.getMainJFrame().setUndecorated(!CoreController.getMainJFrame().isUndecorated());
				if(CoreController.getMainJFrame().isUndecorated()) {
					CoreController.getMainJFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					CoreController.getMainJFrame().setExtendedState(JFrame.NORMAL);
				}
				CoreController.getMainJFrame().pack();
				if(!CoreController.getMainJFrame().isUndecorated()) {
					CoreController.getMainJFrame().setSize(800, 600);
				}
				CoreController.getMainJFrame().validate();
				
				CoreController.getMainJFrame().setVisible(true);
			}
		});
		
		mntmSendLogLine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogPanel.appendNewLine("dev line");
				LogPanel.appendNewLine("dev line");
				LogPanel.appendNewLine("dev line");
				LogPanel.appendNewLine("dev line");
			}
		});

		mntmRemakeMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ObjectMap.remakeMap();
			}

		});

		chckbxmntmShowLog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MainGamePanel.getLogBackgroundPanel().setVisible(!MainGamePanel.getLogBackgroundPanel().isVisible());
				} catch (NullPointerException nl) {
				}
			}
		});
	}

}
