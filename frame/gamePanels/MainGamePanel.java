package frame.gamePanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import core.GameInfo;
import core.NextRoundActionListener;
import core.PlayerStats.PlayerResources;
import core.ResourceManager;
import cost.AvailableResources;
import frame.MainJFrame;
import frame.customPresets.JButton_01;
import frame.customPresets.JPanelCustomBg;
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
	private InteractionPanel interactionPanel;
	private InfoPanel infoPanel;
	private JPanel resourcesPanel;
	private GameMenuPanel menuPanel;
	private JButton btnNextRound;
	private static final MigLayout layout = new MigLayout("insets 0 0 0 0, gap 0px 0px", "[0px][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%]", "[:20px:20px][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%]");
	private JLayeredPane uiPanel;
	private EventlessSelectionQueue eventlessSelectionQueue;
	
	// Lables
	JLabel lblFood = new JLabel("Food", new ImageIcon(ResourceManager.getFood()) , JLabel.LEADING);
	JLabel lblWood = new JLabel("wood", new ImageIcon(ResourceManager.getWood()) , JLabel.LEADING);
	JLabel lblStone = new JLabel("Stone", new ImageIcon(ResourceManager.getStone()) , JLabel.LEADING);
	JLabel lblMetal = new JLabel("Metal", new ImageIcon(ResourceManager.getMetal()) , JLabel.LEADING);
	JLabel lblGold = new JLabel("Gold", new ImageIcon(ResourceManager.getGold()) , JLabel.LEADING);
	JLabel lblManaStone = new JLabel("ManaStone", new ImageIcon(ResourceManager.getManaStone()) , JLabel.LEADING);
	
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
		
		resourcesPanel = new JPanelCustomBg(ResourceManager.getText_bg_02());
		resourcesPanel.setLayout(new FlowLayout(FlowLayout.LEADING,  10, 3));
		resourcesPanel.add(lblFood);
		resourcesPanel.add(lblWood);
		resourcesPanel.add(lblStone);
		resourcesPanel.add(lblMetal);
		resourcesPanel.add(lblGold);
		resourcesPanel.add(lblManaStone);
		setLayer(resourcesPanel, 1);
		
		uiPanel.add(resourcesPanel, "cell 1 0 7 1,alignx left,aligny center");
		
		logBackgroundPanel = new LogBackgroundPanel();
		setLayer(logBackgroundPanel, 1);
		uiPanel.add(logBackgroundPanel, "cell 0 9 8 2,grow");
		logBackgroundPanel.setLayout(new BorderLayout(0, 0));

		LogPanel logPanel = new LogPanel();
		logBackgroundPanel.add(logPanel, BorderLayout.CENTER);
		interactionPanel = new InteractionPanel();
		uiPanel.setLayer(interactionPanel, 1);
		
		setLayer(interactionPanel, 2);
		uiPanel.add(interactionPanel, "cell 8 1 3 8,grow");

		infoPanel = new InfoPanel();
		uiPanel.add(infoPanel, "cell 8 9 3 2,grow");
		
		btnNextRound = new JButton_01("Next Round");
		uiPanel.setLayer(btnNextRound, 3);
		setLayer(btnNextRound, 3);
		btnNextRound.addActionListener(new NextRoundActionListener());
		uiPanel.add(btnNextRound, "cell 8 8 3 1,alignx left,aligny bottom");

		mapPanel = new MapPanel();
		mapPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		setLayer(mapPanel, 0);
		add(mapPanel, "cell 0 0 2 1,grow");
		
		JButton zoomInBtn = new JButton_01("+");
		zoomInBtn.setMinimumSize(new Dimension(30, 30));
		zoomInBtn.addActionListener(e->MapPanel.addDisplacementMultiplier(0.1));
		uiPanel.add(zoomInBtn, "cell 7 0");
		
		JButton zoomOutBtn = new JButton_01("-");
		zoomOutBtn.setMinimumSize(new Dimension(30, 30));
		zoomOutBtn.addActionListener(e->MapPanel.addDisplacementMultiplier(-0.1));
		uiPanel.add(zoomOutBtn, "cell 7 0");
		
		eventlessSelectionQueue = new EventlessSelectionQueue();
		uiPanel.add(eventlessSelectionQueue, "cell 1 1,grow");
		
		if(GameInfo.getPlayerStats().getPlayerResources() != null) {
			updatePlayerResources();
		}
	}

	public EventlessSelectionQueue getEventlessSelectionQueue() {
		return eventlessSelectionQueue;
	}
	
	public synchronized LogBackgroundPanel getLogBackgroundPanel() {
		return logBackgroundPanel;
	}

	public synchronized MapPanel getMapPanel() {
		return mapPanel;
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

	public synchronized JButton getBtnNextRound() {
		return btnNextRound;
	}
	
	public void updateUI() {
		if(GameInfo.getPlayerStats().getPlayerResources() != null) {
			updatePlayerResources();
		}
		infoPanel.update();
		eventlessSelectionQueue.updateList();
	}

	private void updatePlayerResources() {
		PlayerResources playerResources = GameInfo.getPlayerStats().getPlayerResources();
		AvailableResources availableResources = GameInfo.getPlayerStats().getCostManager().getAvailableResources();
		lblFood.setText(playerResources.getFood()+" ("+availableResources.getAvailableFood()+")");
		lblWood.setText(playerResources.getWood()+" ("+availableResources.getAvailableWood()+")");
		lblStone.setText(playerResources.getStone()+" ("+availableResources.getAvailableStone()+")");
		lblMetal.setText(playerResources.getMetal()+" ("+availableResources.getAvailableMetal()+")");
		lblGold.setText(playerResources.getGold()+" ("+availableResources.getAvailableGold()+" )");
		lblManaStone.setText(playerResources.getManaStone()+" ("+availableResources.getAvailableManaStone()+")");
		resourcesPanel.revalidate();
	}
	
}
