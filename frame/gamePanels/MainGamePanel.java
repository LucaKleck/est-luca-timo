package frame.gamePanels;

import java.awt.BorderLayout;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

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
	private static LogBackgroundPanel logBackgroundPanel;
	private MapPanel mapPanel;
	private AbilityPanel abilityPanel;
	private InteractionPanel interactionPanel;
	private InfoPanel infoPanel;
	private GameMenuPanel menuPanel;
	private JPanel resourcesPanel;

	public MainGamePanel() { // x // y
		// 0 1 0 1 2 3
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[70%,grow][30%,grow]", "[25px:n,grow,fill][75%][20%,fill][grow,fill]"));
		this.setDoubleBuffered(true);
		
		resourcesPanel = new JPanel();
		resourcesPanel.setBorder(UIManager.getBorder("MenuBar.border"));
		resourcesPanel.setBackground(UIManager.getColor("MenuBar.background"));
		resourcesPanel.setEnabled(false);
		add(resourcesPanel, "cell 0 0,grow");
		menuPanel = new GameMenuPanel();
		setLayer(menuPanel, 3);
		add(menuPanel, "cell 1 0,grow");

		LogPanel logPanel = new LogPanel();
		
		logBackgroundPanel = new LogBackgroundPanel();
		setLayer(logBackgroundPanel, 1);
		add(logBackgroundPanel, "flowx,cell 0 2,grow");
		logBackgroundPanel.setLayout(new BorderLayout(0, 0));
		logBackgroundPanel.add(logPanel);

		mapPanel = new MapPanel();
		setLayer(mapPanel, 0);
		add(mapPanel, "cell 0 1 1 2,grow");

		abilityPanel = new AbilityPanel();
		setLayer(abilityPanel, 2);
		add(abilityPanel, "flowx,cell 0 3,grow");

		interactionPanel = new InteractionPanel();
		setLayer(interactionPanel, 2);
		add(interactionPanel, "cell 1 1, grow");

		infoPanel = new InfoPanel();
		setLayer(infoPanel, 2);
		add(infoPanel, "cell 1 2 1 2,grow");

	}

	public static LogBackgroundPanel getLogBackgroundPanel() {
		return logBackgroundPanel;
	}
	
}
