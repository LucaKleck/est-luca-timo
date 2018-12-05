package frame.gamePanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import core.GameInfo;
import core.NextRoundActionListener;
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
	private LogBackgroundPanel logBackgroundPanel;
	private MapPanel mapPanel;
	private AbilityPanel abilityPanel;
	private InteractionPanel interactionPanel;
	private InfoPanel infoPanel;
	private JPanel resourcesPanel;
	private GameMenuPanel menuPanel;
	private JLabel lblResourcesLable;
	private JButton btnNextRound;

	public MainGamePanel() { // x // y
		// 0 1 0 1 2 3
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[70%,grow][30%,grow]", "[25px:n,grow,fill][75%][20%,fill][grow,fill]"));
		this.setDoubleBuffered(true);
		
		resourcesPanel = new JPanel();
		resourcesPanel.setBorder(UIManager.getBorder("MenuBar.border"));
		resourcesPanel.setBackground(UIManager.getColor("MenuBar.background"));
		resourcesPanel.setEnabled(false);
		add(resourcesPanel, "cell 0 0,grow");
		resourcesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblResourcesLable = new JLabel("Resources");
		if(GameInfo.getPlayerStats().getPlayerResources() != null) {
			lblResourcesLable.setText(GameInfo.getPlayerStats().getPlayerResources().toString());
		}
		resourcesPanel.add(lblResourcesLable);

		LogPanel logPanel = new LogPanel();
		
		logBackgroundPanel = new LogBackgroundPanel();
		setLayer(logBackgroundPanel, 1);
		add(logBackgroundPanel, "flowx,cell 0 2,grow");
		logBackgroundPanel.setLayout(new BorderLayout(0, 0));
		logBackgroundPanel.add(logPanel);

		menuPanel = new GameMenuPanel();
		setLayer(menuPanel, 3);
		add(menuPanel, "cell 1 0,grow");
		
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
		
		btnNextRound = new JButton("Next Round");
		btnNextRound.setForeground(Color.LIGHT_GRAY);
		btnNextRound.setBackground(Color.DARK_GRAY);
		btnNextRound.addActionListener(new NextRoundActionListener());
		infoPanel.setRowHeaderView(btnNextRound);

	}

	public synchronized LogBackgroundPanel getLogBackgroundPanel() {
		return logBackgroundPanel;
	}

	public synchronized MapPanel getMapPanel() {
		return mapPanel;
	}

	public synchronized AbilityPanel getAbilityPanel() {
		return abilityPanel;
	}

	public synchronized InteractionPanel getInteractionPanel() {
		return interactionPanel;
	}

	public synchronized InfoPanel getInfoPanel() {
		return infoPanel;
	}

	public synchronized JPanel getResourcesPanel() {
		return resourcesPanel;
	}

	public synchronized GameMenuPanel getMenuPanel() {
		return menuPanel;
	}

	public synchronized JLabel getLblResourcesLable() {
		return lblResourcesLable;
	}

	public synchronized JButton getBtnNextRound() {
		return btnNextRound;
	}
	
}
