package frame.gamePanels;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLayeredPane;

import frame.MainJFrame;
import net.miginfocom.swing.MigLayout;

/**
 * Contains all JPanels that are used for the game
 * 
 * @author Luca Kleck
 * @see MainJFrame
 */
public class MainGamePanel extends JLayeredPane {
	private static final long serialVersionUID = 120L;
	private static LogBackgroundPanel logBackgroundPanel = new LogBackgroundPanel();

	public MainGamePanel() { // x // y
		// 0 1 0 1 2 3
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[70%,grow][30%,grow]",
				"[25px:n,fill][75%][20%,fill][grow,fill]"));

		this.setDoubleBuffered(true);
		GameMenuPanel menuPanel = new GameMenuPanel();
		setLayer(menuPanel, 3);
		add(menuPanel, "cell 0 0 2 1,grow");

//		LogPanel logPanel = new LogPanel();

		setLayer(logBackgroundPanel, 1);
		add(logBackgroundPanel, "flowx,cell 0 2,grow");
		logBackgroundPanel.setLayout(new BorderLayout(0, 0));
//		logBackgroundPanel.add(logPanel);

		MapPanel mapPanel = new MapPanel();
		setLayer(mapPanel, 0);
		add(mapPanel, "cell 0 1 1 2,grow");

		AbilityPanel abilityPanel = new AbilityPanel();
		setLayer(abilityPanel, 2);
		add(abilityPanel, "flowx,cell 0 3,grow");

		JButton btnTest = new JButton("Test");
		abilityPanel.add(btnTest, "cell 0 0");

		InteractionPanel interactionPanel = new InteractionPanel();
		setLayer(interactionPanel, 2);
		add(interactionPanel, "cell 1 1, grow");

		InfoPanel infoPanel = new InfoPanel();
		setLayer(infoPanel, 2);
		add(infoPanel, "cell 1 2 1 2,grow");

	}

	public static LogBackgroundPanel getLogBackgroundPanel() {
		return logBackgroundPanel;
	}

}
