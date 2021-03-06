package frame.gamePanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import core.Core;
import core.GameInfo;
import core.PlayerStats.PlayerResources;
import core.actions.NextRoundActionListener;
import cost.AvailableResources;
import frame.MainJFrame;
import frame.customPresets.JButtonCustomBg;
import frame.customPresets.JButton_01;
import frame.customPresets.JPanelCustomBg;
import frame.graphics.ResourceManager;
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
	private JPanel resourcesPanel;
	private GameMenuPanel menuPanel;
	private JButton btnNextRound;
	private static final MigLayout layout = new MigLayout("insets 0 0 0 0, gap 0px 0px", "[0px][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%]", "[:20px:20px][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%][:10%:10%]");
	private JLayeredPane uiPanel;
	private EventlessSelectionQueue eventlessSelectionQueue;
	
	// Lables
	JLabel lblFood = new JLabel("", new ImageIcon(ResourceManager.getFood()) , JLabel.LEADING);
	JLabel lblWood = new JLabel("", new ImageIcon(ResourceManager.getWood()) , JLabel.LEADING);
	JLabel lblStone = new JLabel("", new ImageIcon(ResourceManager.getStone()) , JLabel.LEADING);
	JLabel lblMetal = new JLabel("", new ImageIcon(ResourceManager.getMetal()) , JLabel.LEADING);
	JLabel lblGold = new JLabel("", new ImageIcon(ResourceManager.getGold()) , JLabel.LEADING);
	JLabel lblManaStone = new JLabel("", new ImageIcon(ResourceManager.getManaStone()) , JLabel.LEADING);
	JLabel lblWorkers = new JLabel("", new ImageIcon(ResourceManager.getBuilderImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH)) , JLabel.LEADING);
	
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
		uiPanel.add(menuPanel, "cell 0 0 12 1,grow");		
		
		resourcesPanel = new JPanelCustomBg(ResourceManager.getText_bg_02());
		resourcesPanel.setLayout(new FlowLayout(FlowLayout.LEADING,  10, 3));
		resourcesPanel.add(lblFood);
		resourcesPanel.add(lblWood);
		resourcesPanel.add(lblStone);
		resourcesPanel.add(lblMetal);
		resourcesPanel.add(lblGold);
		resourcesPanel.add(lblManaStone);
		resourcesPanel.add(lblWorkers);
		setLayer(resourcesPanel, 1);
		
		uiPanel.add(resourcesPanel, "cell 1 1,alignx left, gapleft 4, gaptop 4, newline, flowy");
		
		eventlessSelectionQueue = new EventlessSelectionQueue();
		uiPanel.add(eventlessSelectionQueue, "cell 1 1,grow, newline, flowy");
		
		JButton zoomInBtn = new JButton_01("+");
		zoomInBtn.setMinimumSize(new Dimension(30, 30));
		zoomInBtn.addActionListener(e->MapPanel.addDisplacementMultiplier(0.2));
		uiPanel.add(zoomInBtn, "cell 1 1, ax left, ay top, gapleft 4");
		
		JButton zoomOutBtn = new JButton_01("-");
		zoomOutBtn.setMinimumSize(new Dimension(30, 30));
		zoomOutBtn.addActionListener(e->MapPanel.addDisplacementMultiplier(-0.2));
		uiPanel.add(zoomOutBtn, "cell 1 1, ax left, ay top, gapleft 4");
		
		// log (bottom left)	
		logBackgroundPanel = new LogBackgroundPanel();
		setLayer(logBackgroundPanel, 1);
		uiPanel.add(logBackgroundPanel, "cell 0 9 8 2,grow");
		logBackgroundPanel.setLayout(new BorderLayout(0, 0));
		
		LogPanel logPanel = new LogPanel();
		logBackgroundPanel.add(logPanel, BorderLayout.CENTER);
		interactionPanel = new InteractionPanel();
		uiPanel.setLayer(interactionPanel, 1);
		
		// right side
		setLayer(interactionPanel, 2);
		uiPanel.add(interactionPanel, "cell 8 1 3 9,grow");
		
		// bottom right
		
		btnNextRound = new JButtonCustomBg(ResourceManager.getButton_04_bg(), true, "Next Round");
		uiPanel.setLayer(btnNextRound, 3);
		setLayer(btnNextRound, 3);
		btnNextRound.addActionListener(new NextRoundActionListener());
		uiPanel.add(btnNextRound, "cell 8 10 3,grow");

		mapPanel = new MapPanel();
		mapPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		setLayer(mapPanel, 0);
		add(mapPanel, "cell 0 0 2 1,grow");
		
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
		eventlessSelectionQueue.updateList();
	}
	
	public static void refresh() {
		if ((Core.getMainJFrame().getCurrentComponent() instanceof MainGamePanel)) {
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).updateUI();
			((MainGamePanel) Core.getMainJFrame().getCurrentComponent()).getMapPanel().getMapImage().update();
		}
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
		lblWorkers.setText( GameInfo.getObjectMap().getBuilderAmount()+"/"+ (GameInfo.getObjectMap().getTownHall().getLevel()+1) ); 
		resourcesPanel.revalidate();
	}
	
}
