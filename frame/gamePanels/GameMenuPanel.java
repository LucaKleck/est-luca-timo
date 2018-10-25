package frame.gamePanels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import map.ObjectMap;

public class GameMenuPanel extends JPanel {
	private static final long serialVersionUID = 122L;

	public GameMenuPanel() {
		setLayout(new GridLayout(0, 1, 0, 0));
		JMenuBar menuBar = new JMenuBar();
		add(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnOptions.add(mntmSave);
		
		JCheckBoxMenuItem chckbxmntmShowLog = new JCheckBoxMenuItem("show Log");
		chckbxmntmShowLog.setSelected(true);
		mnOptions.add(chckbxmntmShowLog);
		
		JMenu mnDev = new JMenu("dev");
		menuBar.add(mnDev);
		
		JMenuItem mntmRemakeMap = new JMenuItem("Remake Map");
		mnDev.add(mntmRemakeMap);
		
		JMenuItem mntmSendLogLine = new JMenuItem("Send log line");
		mnDev.add(mntmSendLogLine);
		
		mntmSendLogLine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
