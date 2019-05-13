package frame.menuPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import core.ControlInput;
import core.GameInfo;
import core.ResourceManager;
import frame.JButton_01;
import frame.JPanelCustomBg;
import frame.StatisticLabel;
import net.miginfocom.swing.MigLayout;

public class EndScreenPanel extends JPanelCustomBg {
	private static final long serialVersionUID = 111L;
	private String endScreenTitle;
	
	public EndScreenPanel(boolean gameIsWon) {
		super(ResourceManager.getBackground_05());
		setLayout(new MigLayout("insets 0 0 0 0", "[33%][33%][33%]", "[100%,fill]"));
		
		JPanelCustomBg container = new JPanelCustomBg(ResourceManager.getBackground_03(), true);
		container.setLayout(new MigLayout("insets 0 0 0 0", "[10%][80%][10%]", "[10%][3%][3%][3%][3%][3%][3%][3%][3%][3%][3%][3%][6%][10%][10%]"));
		
		add(container, "cell 1 0,grow");
		
		if(gameIsWon) {
			endScreenTitle = "Congratulations! You Won!";
		} else {
			endScreenTitle = "You lost! Better luck next time!";
		}
		
		JLabel endScreenTitleLbl = new StatisticLabel(endScreenTitle);
		container.add(endScreenTitleLbl, "cell 1 1,grow");
		
		JLabel totalWoodLbl = new StatisticLabel("Total Wood: " + GameInfo.getPlayerStats().getTotalWoodCollected());
		container.add(totalWoodLbl, "cell 1 2,grow");
		
		JLabel totalFoodLbl = new StatisticLabel("Total Food: " + GameInfo.getPlayerStats().getTotalFoodCollected());
		container.add(totalFoodLbl, "cell 1 3,grow");
		
		JLabel totalStoneLbl = new StatisticLabel("Total Stone: " + GameInfo.getPlayerStats().getTotalStoneCollected());
		container.add(totalStoneLbl, "cell 1 4,grow");
		
		JLabel totalMetalLbl = new StatisticLabel("Total Metal: " + GameInfo.getPlayerStats().getTotalMetalCollected());
		container.add(totalMetalLbl, "cell 1 5,grow");
		
		JLabel totalGoldLbl = new StatisticLabel("Total Gold: " + GameInfo.getPlayerStats().getTotalGoldCollected());
		container.add(totalGoldLbl, "cell 1 6,grow");
		
		JLabel totalManaStoneLbl = new StatisticLabel("Total ManaStone: " + GameInfo.getPlayerStats().getTotalManaStoneCollected());
		container.add(totalManaStoneLbl, "cell 1 7,grow");
		
		JLabel unitsKilled = new StatisticLabel("Units Killed: " + GameInfo.getPlayerStats().getUnitsKilled());
		container.add(unitsKilled, "cell 1 8,grow");
		
		JLabel unitsCreated = new StatisticLabel("Units Created: " + GameInfo.getPlayerStats().getUnitsCreated());
		container.add(unitsCreated, "cell 1 9,grow");
		
		JLabel buildingsDestroyed = new StatisticLabel("Buildings Destroyed: " + GameInfo.getPlayerStats().getBuildingsDestroyed());
		container.add(buildingsDestroyed, "cell 1 10,grow");
		
		JLabel buildingsBuilt = new StatisticLabel("Buildings Built: " + GameInfo.getPlayerStats().getBuildingsBuilt());
		container.add(buildingsBuilt, "cell 1 11,grow");
		
		JButton btnMainMenu = new JButton_01("Exit to Main Menu");
		btnMainMenu.setActionCommand("frame.menuPanels.MainMenuPanel");
		btnMainMenu.addActionListener(ControlInput.menuChanger);
		container.add(btnMainMenu, "cell 1 13,grow");
		
		JButton btnExit = new JButton_01("Exit Game");
		btnExit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		container.add(btnExit, "cell 1 14,grow");

	}

}