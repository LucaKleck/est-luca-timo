package frame.gamePanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

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
	private static final MigLayout layout = new MigLayout("insets 0 0 0 0, gap 0px 0px", "[0px][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%]", "[:20px:20px][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%]");
	private JLayeredPane uiPanel;
	
	public MainGamePanel() { // x // y
		// Contains the map and the UI. has 2x cells, 1 of them is just for enabling overlaying the panels
		setLayout(new MigLayout("insets 0 0 0 0, gap 0px 0px", "[0px][100%]", "[100%]"));
		this.setDoubleBuffered(true);
		
		uiPanel = new JLayeredPane();
		uiPanel.setOpaque(false);
		uiPanel.setLayout(layout);
		add(uiPanel, "cell 1 0,grow");
		
		menuPanel = new GameMenuPanel();
		setLayer(menuPanel, 3);
		uiPanel.add(menuPanel, "cell 8 0 3 1,grow");
		
		abilityPanel = new AbilityPanel();
		setLayer(abilityPanel, 2);
		uiPanel.add(abilityPanel, "flowx,cell 3 8 5 1,grow");
		
		
		resourcesPanel = new JPanel();
		setLayer(resourcesPanel, 1);
		resourcesPanel.setBorder(UIManager.getBorder("MenuBar.border"));
		resourcesPanel.setBackground(UIManager.getColor("MenuBar.background"));
		resourcesPanel.setEnabled(false);
		uiPanel.add(resourcesPanel, "cell 1 0 7 1,alignx left,aligny center");
		
		lblResourcesLable = new JLabel("Resources");
		resourcesPanel.add(lblResourcesLable);
		
		logBackgroundPanel = new LogBackgroundPanel();
		setLayer(logBackgroundPanel, 1);
		uiPanel.add(logBackgroundPanel, "cell 0 9 8 2,grow");
		logBackgroundPanel.setLayout(new BorderLayout(0, 0));

		LogPanel logPanel = new LogPanel();
		logBackgroundPanel.add(logPanel, BorderLayout.CENTER);
		interactionPanel = new InteractionPanel();
		
		setLayer(interactionPanel, 2);
		uiPanel.add(interactionPanel, "cell 8 1 3 10,grow");

		infoPanel = new InfoPanel();
		
		btnNextRound = new JButton("Next Round");
		setLayer(btnNextRound, 1);
		btnNextRound.setForeground(Color.LIGHT_GRAY);
		btnNextRound.setBackground(Color.DARK_GRAY);
		btnNextRound.addActionListener(new NextRoundActionListener());
		uiPanel.add(btnNextRound, "cell 1 8 2 1");

		mapPanel = new MapPanel();
		mapPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		setLayer(mapPanel, 0);
		add(mapPanel, "cell 0 0 2 1,grow");
		
		if(GameInfo.getPlayerStats().getPlayerResources() != null) {
			lblResourcesLable.setText(GameInfo.getPlayerStats().getPlayerResources().toString());
		}
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
