package frame.gamePanels;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class MainGamePanel extends JPanel {
	private static final long serialVersionUID = 121L;

	public MainGamePanel() {
		setLayout(new MigLayout("", "[]", "[]"));
		
		MapPanel mapPanel = new MapPanel();
		add(mapPanel, "cell 0 0,grow");
	}

}
