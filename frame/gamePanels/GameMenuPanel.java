package frame.gamePanels;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.GridLayout;

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
	}

}
