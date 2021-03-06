package frame.gamePanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import core.Core;
import core.GameInfo;
import core.XMLSaveAndLoad;
import core.actions.ControlInput;
import core.actions.FullscreenActionListener;
import frame.customPresets.JPanelCustomBg;
import frame.graphics.ResourceManager;

public class GameMenuPanel extends JPanelCustomBg {
	private static final long serialVersionUID = 122L;
	private JCheckBoxMenuItem chckbxmntmEnableLogSelection;
	private JCheckBoxMenuItem chckbxmntmFullscreen;
	private JCheckBoxMenuItem chckbxmntmShowLog;
	private JMenuBar menuBar;
	private JMenu mnDev;
	private JMenu mnOptions;
	private JMenuItem mntmExitGame;
	private JMenuItem mntmExitToMain;
	private JMenuItem mntmAddResources;
	private JMenuItem mntmSave;

	public GameMenuPanel() {
		super(ResourceManager.getFrame_10_03());
		setLayout(new GridLayout(0, 1, 0, 0));
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0,0,0,0));
		menuBar.setBorder(BorderFactory.createEmptyBorder(0, 6, 3, 20));
		menuBar.setOpaque(false);
		menuBar.setLayout(new FlowLayout(FlowLayout.LEADING, 3, 0));
		add(menuBar);

		mnOptions = createMenu("Options");
		mnOptions.addMouseListener(new MouseOverMenu(mnOptions));
		menuBar.add(mnOptions);

		mntmSave = new JMenuItem("Save");
		mntmSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnOptions.add(mntmSave);

		chckbxmntmShowLog = new JCheckBoxMenuItem("show Log");
		chckbxmntmShowLog.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxmntmShowLog.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		chckbxmntmShowLog.setSelected(Boolean.parseBoolean(Core.loadSetting(Core.SETTING_SHOW_LOG)));
		mnOptions.add(chckbxmntmShowLog);

		chckbxmntmEnableLogSelection = new JCheckBoxMenuItem("Enable Log selection (disables key-shortcuts)");
		chckbxmntmEnableLogSelection.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxmntmEnableLogSelection.setSelected(!LogPanel.getLog().isEnabled());
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

		mnDev = createMenu("dev");
		mnDev.setFocusTraversalKeysEnabled(false);
		if(new Boolean(Core.loadSetting(Core.SETTING_DEV))) menuBar.add(mnDev);

		mntmAddResources = new JMenuItem("Add 1000 Resources");
		mntmAddResources.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmAddResources
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmAddResources.addActionListener(a -> {
			GameInfo.getPlayerStats().getPlayerResources().addAll(100000);
		});
		mnDev.add(mntmAddResources);

		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getBtnNextRound().isEnabled()) {
					LogPanel.appendNewLine(XMLSaveAndLoad.saveGame(XMLSaveAndLoad.xmlFilePath));
				}
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

		chckbxmntmFullscreen.addActionListener(new FullscreenActionListener());

		chckbxmntmShowLog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Core.getMainJFrame().getCurrentComponent() != null && Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel) {
					Component c = ((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getLogBackgroundPanel();
					c.setVisible(!c.isVisible());
					Core.saveSetting(Core.SETTING_SHOW_LOG, new Boolean(c.isVisible()).toString());
				}
			}
		});
	}

	private JMenu createMenu(String name) {
		JMenu menu = new JMenu(name);
		menu.setForeground(Color.BLACK);
		menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menu.addMouseListener(new MouseOverMenu(menu));
		return menu;
	}
	
	private class MouseOverMenu implements MouseListener {
		private JMenu menu;
		public MouseOverMenu(JMenu menu) {
			this.menu = menu;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			menu.setBackground(Color.DARK_GRAY);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			menu.setBackground(Color.LIGHT_GRAY);
		}
	}
	
}
