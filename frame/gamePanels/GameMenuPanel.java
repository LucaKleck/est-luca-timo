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

import core.ControlInput;
import core.Core;
import core.XMLSaveAndLoad;
import map.ObjectMap;

public class GameMenuPanel extends JPanel {
	private static final long serialVersionUID = 122L;
	private JCheckBoxMenuItem chckbxmntmEnableLogSelection;
	private JCheckBoxMenuItem chckbxmntmFullscreen;
	private JCheckBoxMenuItem chckbxmntmShowLog;
	private JMenuBar menuBar;
	private JMenu mnDev;
	private JMenu mnOptions;
	private JMenuItem mntmExitGame;
	private JMenuItem mntmExitToMain;
	private JMenuItem mntmGenerateDefaultUnit;
	private JMenuItem mntmRemakeMap;
	private JMenuItem mntmSave;
	private JMenuItem mntmSendLogLine;

	public GameMenuPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		menuBar = new JMenuBar();
		add(menuBar);

		mnOptions = new JMenu("Options");
		mnOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.add(mnOptions);

		mntmSave = new JMenuItem("Save");
		mntmSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnOptions.add(mntmSave);

		chckbxmntmShowLog = new JCheckBoxMenuItem("show Log");
		chckbxmntmShowLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxmntmShowLog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		chckbxmntmShowLog.setSelected(MainGamePanel.getLogBackgroundPanel().isVisible());
		mnOptions.add(chckbxmntmShowLog);

		chckbxmntmEnableLogSelection = new JCheckBoxMenuItem("Enable Log selection (disables key-shortcuts)");
		chckbxmntmEnableLogSelection.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxmntmEnableLogSelection.setSelected(LogPanel.getLog().isEnabled());
		chckbxmntmEnableLogSelection.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LogPanel.getLog().setEnabled(!LogPanel.getLog().isEnabled());
					Core.saveSetting(Core.SETTING_ENABLE_LOG, new Boolean(LogPanel.getLog().isEnabled()).toString() );
					
					if (LogPanel.getLog().isEnabled()) {
						LogPanel.getLog().setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
					} else {
						LogPanel.getLog().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					}
				} catch (NullPointerException nl) {
					chckbxmntmEnableLogSelection.setSelected(!chckbxmntmEnableLogSelection.isSelected());
				}
				
			}
		});
		mnOptions.add(chckbxmntmEnableLogSelection);

		chckbxmntmFullscreen = new JCheckBoxMenuItem("Fullscreen");
		chckbxmntmFullscreen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxmntmFullscreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_MASK));
		chckbxmntmFullscreen.setSelected(Core.getMainJFrame().isUndecorated());
		mnOptions.add(chckbxmntmFullscreen);

		mntmExitToMain = new JMenuItem("Exit to Main Menu");
		mntmExitToMain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOptions.add(mntmExitToMain);

		mntmExitGame = new JMenuItem("Exit Game");
		mntmExitGame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnOptions.add(mntmExitGame);

		mnDev = new JMenu("dev");
		mnDev.setFocusTraversalKeysEnabled(false);
		mnDev.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		if(new Boolean(Core.getSetting(Core.SETTING_DEV)))	menuBar.add(mnDev);

		mntmRemakeMap = new JMenuItem("Remake Map");
		mntmRemakeMap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmRemakeMap
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnDev.add(mntmRemakeMap);

		mntmSendLogLine = new JMenuItem("Send log line");
		mntmSendLogLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSendLogLine
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnDev.add(mntmSendLogLine);

		mntmGenerateDefaultUnit = new JMenuItem("generate default unit ability (not working)");
		mntmGenerateDefaultUnit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmGenerateDefaultUnit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ObjectMap.getSelected().setSelectedAbility(new Ability("dev_create_unit"));
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
				Core.getMainJFrame().dispose();
				// put it in the left uppermost corner
				Core.getMainJFrame().setUndecorated(!Core.getMainJFrame().isUndecorated());
				Core.getMainJFrame().setBounds(0, 0, 0, 0);
				if (Core.getMainJFrame().isUndecorated()) {
					Core.getMainJFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					Core.getMainJFrame().setExtendedState(JFrame.NORMAL);
				}
				Core.getMainJFrame().pack();
				if (!Core.getMainJFrame().isUndecorated()) {
					Core.getMainJFrame().setSize(800, 600);
				}
				Core.getMainJFrame().validate();

				Core.getMainJFrame().setVisible(true);
				
				Core.saveSetting(Core.SETTING_FULLSCREEN, new Boolean(Core.getMainJFrame().isUndecorated()).toString() );
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
					MainGamePanel.getLogBackgroundPanel()
							.setVisible(!MainGamePanel.getLogBackgroundPanel().isVisible());
					Core.saveSetting(Core.SETTING_SHOW_LOG, new Boolean(MainGamePanel.getLogBackgroundPanel().isVisible()).toString() );
				} catch (NullPointerException nl) {
				}
			}
		});
	}

}
