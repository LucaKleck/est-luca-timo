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
import core.XMLSaveAndLoad;
import core.start;
import entity.Ability;
import map.ObjectMap;

public class GameMenuPanel extends JPanel {
	private static final long serialVersionUID = 122L;
	private JMenu mnOptions;
	private JMenuItem mntmSave;
	private JCheckBoxMenuItem chckbxmntmShowLog;
	private JCheckBoxMenuItem chckbxmntmFullscreen;
	private JMenuItem mntmExitToMain;
	private JMenuItem mntmExitGame;
	private JMenu mnDev;
	private JMenuItem mntmRemakeMap;
	private JMenuItem mntmSendLogLine;
	private JMenuItem mntmGenerateDefaultUnit;
	private JMenuBar menuBar;
	private JCheckBoxMenuItem chckbxmntmEnableLogSelection;

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
		chckbxmntmShowLog.setSelected(true);
		mnOptions.add(chckbxmntmShowLog);
		
		chckbxmntmEnableLogSelection = new JCheckBoxMenuItem("Enable Log selection (disables key-shortcuts)");
		chckbxmntmEnableLogSelection.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxmntmEnableLogSelection.setSelected(false);
		chckbxmntmEnableLogSelection.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					LogPanel.getLog().setEnabled(!LogPanel.getLog().isEnabled());
					if(LogPanel.getLog().isEnabled()) {
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
		chckbxmntmFullscreen.setSelected(start.getMainJFrame().isUndecorated());
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
		menuBar.add(mnDev);

		mntmRemakeMap = new JMenuItem("Remake Map");
		mntmRemakeMap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmRemakeMap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnDev.add(mntmRemakeMap);

		mntmSendLogLine = new JMenuItem("Send log line");
		mntmSendLogLine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSendLogLine.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mnDev.add(mntmSendLogLine);
		
		mntmGenerateDefaultUnit = new JMenuItem("generate default unit ability");
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
				start.getMainJFrame().dispose();
				// put it in the left uppermost corner
				start.getMainJFrame().setUndecorated(!start.getMainJFrame().isUndecorated());
				start.getMainJFrame().setBounds(0, 0, 0, 0);
				if(start.getMainJFrame().isUndecorated()) {
					start.getMainJFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
				} else {
					start.getMainJFrame().setExtendedState(JFrame.NORMAL);
				}
				start.getMainJFrame().pack();
				if(!start.getMainJFrame().isUndecorated()) {
					start.getMainJFrame().setSize(800, 600);
				}
				start.getMainJFrame().validate();
				
				start.getMainJFrame().setVisible(true);
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
